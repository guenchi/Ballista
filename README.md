# Trabutium

***Trabutium*** is a web framwork for ***Igropyr***

***Igropyr*** is a async http-server for Chez Scheme

https://github.com/guenchi/Igropyr

***Igropyr*** : ***Trabutium*** = ***Node*** : ***Express***

her sister framwork: ***Ballista*** (pure functional)

https://github.com/guenchi/Ballista

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

***install Trabutium***

`$ raven install trabutium`



***use Trabutium***


```
(http
    (set 
        ('staticpath "/usr/local/www/"))
    (listen 8080))
```
