(import (igropyr http) 
        (ballista ballista))

(printf "server is start, listen on port..~a\n" 8080)

(define index
    (lambda x
        (res "this is index")))

(define user
    (lambda (x y)
        (res 200 "text/html" (caddr x))))

(define page
    (lambda (x y)
        (res (car x))))

(define note
    (lambda x
        (res "text/html" "<h1>NOTE</h1>")))

(define erro
    (lambda x
        (res 200 "text/html" "<h1>erro</h1>")))



(define pass
    (lambda (x return)
        (next x return)))



(define deline
    (lambda (x return)
        (return (errorpage 403))))

(get-use pass)
(get-use pass)
(get-use pass)


(get "/" index)
(get "/index"   deline  index)
(get "/user"  pass pass pass pass page)
(get "/*/note"  note)
(get "/erro/*"  erro)
(post "/user"   user)

(staticpath "/users/local/www/")
(listen-on 8080)

(server-on)
