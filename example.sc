(import  (ballista ballista))

(printf "server is start, listen on port..~a\n" 8080)

(define index
    (lambda x
        (res "this is index")))

(define user
    (lambda (header path payload)
        (res 200 "text/html" payload)))

(define page
    (lambda (header path query)
        (res 200 query)))

(define note
    (lambda x
        (res "text/html" "<h1>NOTE</h1>")))

(define erro
    (lambda x
        (res 200 "text/html" "<h1>erro</h1>")))



(define pass
    (lambda x
        #t))

(define deline
    (lambda x
        #f))


(get "/" index)
(get "/index"   deline  index)
(get "/page"    pass    page)
(get "/*/note"  note)
(get "/erro/*"  erro)
(post "/user"   user)

(staticpath "/users/local/www/")
(listen-on 8080)

(server-on)
