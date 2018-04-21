;  MIT License

;  Copyright guenchi (c) 2018 
         
;  Permission is hereby granted, free of charge, to any person obtaining a copy
;  of this software and associated documentation files (the "Software"), to deal
;  in the Software without restriction, including without limitation the rights
;  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;  copies of the Software, and to permit persons to whom the Software is
;  furnished to do so, subject to the following conditions:
         
;  The above copyright notice and this permission notice shall be included in all
;  copies or substantial portions of the Software.
         
;  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
;  SOFTWARE.





(library (ballista ballista)
  (export
    get
    post
    res
    send
    staticpath
    listen-on
    server-on
    host?
    user-agent?
    accept-language?
    accept-encoding?
    cookie?
    connection?
    query-parser
  )
  (import
    (scheme)
    (igropyr http)
    (igropyr igropyr)
  )

(define route-get (list '()))
(define route-post (list '()))
(define server-setup (list (cons 'init '()))) 
 

(define ref*
    (lambda (str x)
      (if (null? str)
        '()
        (if (par (caar str) x)
          (cdar str)
          (ref* (cdr str) x)))))

(define match
    (lambda (str x)
        (let ((y (ref str x)))
            (if (null? y)
                (ref* str x)
                y))))


(define push
	(lambda (lst x y)
		(if (null? (cdr lst))
			(if (null? (car lst))
				(set-car! lst (cons x y))
				(set-cdr! lst (cons (cons x y) '())))
			(push (cdr lst) x y))))


    (define-syntax get
        (lambda (x)
            (syntax-case x ()
                ((_ p f)#'(push route-get p f))
                ((_ p v1 f)#'(push route-get p 
                                (lambda (x y z)
                                    (if (and (v1 x y z))
                                        (f x y z)
                                        (handle403 x)))))
                ((_ p v1 v2 ... f)#'(push route-get p 
                                (lambda (x y z)
                                    (if (and (v1 x y z) (v2 x y z) ...)
                                        (f x y z)
                                        (handle403 x))))))))

    (define-syntax post
        (lambda (x)
            (syntax-case x ()
                ((_ p f)#'(push route-post p f))
                ((_ p v1 f)#'(push route-post p 
                                (lambda (x y z)
                                    (if (and (v1 x y z))
                                        (f x y z)
                                        (handle403 x)))))
                ((_ p v1 v2 ... f)#'(push route-post p 
                                (lambda (x y z)
                                    (if (and (v1 x y z) (v2 x y z) ...)
                                        (f x y z)
                                        (handle403 x))))))))




(define handle_res
    (lambda (x)
        (let ((status (ref x 'status))
                (type (ref x 'type))
                (content (ref x 'content)))
            (response
                (if (integer? status)
                    status
                    200)
                (if (null? type)
                    "text/html"
                    type)
                (if (null? content)
                    ""
                    content)))))

(define-syntax res
  (lambda (x)
      (syntax-case x ()
          ((_) #'(handle_res '()))
          ((_ e1) #'(handle_res (list (cons 'content e1))))
          ((_ e1 e2) #'(handle_res (list (cons (cond 
                                                  ((integer? e1) 'status)
                                                  ((string? e1) 'type)
                                                  (else '())) 
                                              e1)
                                          (cons 'content e2))))
          ((_ e1 e2 e3) #'(handle_res (list (cons (if (integer? e1)
                                                      'status
                                                      '())
                                                  e1)
                                          (cons (if (string? e2)
                                                      'type
                                                      '())
                                                  e2)
                                          (cons 'content e3))))
          ((_ e1 e2 e3 e4) #'(handle_res (list (cons (if (integer? e1)
                                                      'status
                                                      '())
                                                  e1)
                                          (cons (if (string? e2)
                                                      'type
                                                      '())
                                                  e2)
                                          (cons 'content (list e3 e4))))))))

(define handle_send
    (lambda (x)
        (let ((type (ref x 'type))
              (file (ref x 'file)))
            (sendfile
                (if (null? type)
                    ""
                    type)
                (if (null? file)
                    ""
                    file)))))

(define-syntax send
  (lambda (x)
      (syntax-case x ()
          ((_) #'(handle_send '()))
          ((_ e1) #'(handle_send (list (cons 'file e1))))
          ((_ e1 e2) #'(handle_send (list (cons 'type e1)
                                          (cons 'file e2)))))))


(define router
    (lambda (router path_info)
        (let ((x (match router path_info)))
            (if (null? x)
                handle404
                x))))

 
(define handle-get
    (request
        (lambda (header path query)
            ((router route-get path) header path query))))

(define handle-post
    (request
        (lambda (header path payload)
            ((router route-post path) header path payload))))

(define staticpath
    (lambda (x)
        (push server-setup 'staticpath x)))


    (define-syntax listen-on
        (lambda (x)
            (syntax-case x ()
                ((_ e) #'(cond 
                    ((string? e) (push server-setup 'ip e))
                    ((integer? e) (push server-setup 'port e))))
                ((_ e1 e2) #'(begin
                                (push server-setup 'ip e1)
                                (push server-setup 'port e2))))))


(define server-on
    (lambda ()
        (server handle-get handle-post server-setup server-setup))) 
 

(define handle404
    (lambda (x . y)
      (errorpage 404 "<center><h5>Powered by Trabutium</h5></center>")))
 
 
(define host?
    (lambda (x)
      (header-parser x "Host")))

  (define user-agent?
    (lambda (x)
      (header-parser x "User-Agent")))

  (define accept-language?
    (lambda (x)
      (header-parser x "Accept-Language")))

  (define accept-encoding?
    (lambda (x)
      (header-parser x "Accept-Encoding")))

  (define cookie?
    (lambda (x)
      (header-parser x "Cookie")))

  (define connection?
    (lambda (x)
      (header-parser x "Connection")))
 
    
    (define query-parser
        (lambda (str x y)
            (let loop ((str (split str y)))
                (define f 
                    (lambda (str)
                        (let ((str (split (car str) x)))
                            (cons (car str) (cadr str)))))
                (if (null? (cdr str))
                    (cons (f str) '())
                    (cons (f str) (loop (cdr str)))))))
    

)





