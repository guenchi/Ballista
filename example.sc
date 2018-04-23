(import (igropyr http) 
        (ballista ballista))

(printf "server is start, listen on port..~a\n" 8080)

(define index
    (lambda x
        (res "this is index")))

(define user
    (lambda (header path payload)
        (res 200 "text/html" payload)))

(define page
    (lambda (header path query)
        (res (string-append header " Path:" path))))

(define note
    (lambda x
        (res "text/html" "<h1>NOTE</h1>")))

(define erro
    (lambda x
        (res 200 "text/html" "<h1>erro</h1>")))



(define handle-header
    (lambda (header path query break)
        (lambda (f)
            (f (cookie? header) path query break))))


(define handle-path
    (lambda (header path query break)
        (lambda (f)
            (f header (path-parser path 0) query))))

(define deline
    (lambda (header path query break)
        (break (errorpage 403))))


(get "/" index)
(get "/index"   deline  index)
(get "/user"  handle-header handle-path page)
(get "/*/note"  note)
(get "/erro/*"  erro)
(post "/user"   user)

(staticpath "/users/local/www/")
(listen-on 8080)

(server-on)
