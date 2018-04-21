# Ballista

***Ballista*** is a ***Express style*** web framwork for ***Igropyr***

***Igropyr*** : ***Ballista*** = ***Node*** : ***Express***

***easily to write the router***

```
(get     "/"             index)
(get     "/index"        index)
(post    "/users"        users)
(post    "/notes"        notes)
(get     "/blog/*/en"    blogEN)
(get     "/articles/*"   article)
(get     "/*"            handle404)
```
the route can with any numbers of middleware:
```
(get     "/index"  middleware ...  index)
```
the middleware have to accept three augements: header, path, query/payload and return boolean.


***easily to define respone***

```
(define index
    (lambda (header path query)
        (res "hello world")))

(define index
    (lambda (header path query)
        (res 200 "hello world")))

(define index
    (lambda (header path query)
        (res "text/html" "<h1>hello world</h1>")))
        
(define index
    (lambda (header path query)
        (res 200 "text/html" "<h1>hello world</h1>")))
```


```
(res string)                => respone content only

(res int string)            => respone status and content

(res string string)         => respone style and content

(res int string string)     => respone status, style and content
```

***install***

`$ raven install ballista`



***use***


these set up is not prerequiste
```
(staticpath "/usr/local/www/")
(listen-on "127.0.0.1")
(listen-on 8080)
```
then or just
```
(server-on)
```

***Igropyr*** is a async http-server for Chez Scheme

https://github.com/guenchi/Igropyr

Ballista's sister framwork: ***Trabutium*** (pure functional)

https://github.com/guenchi/Trabutium
