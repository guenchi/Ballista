# Ballista

***Ballista*** is an ***Express-style*** web framework for ***Igropyr***

### Igropyr : Ballista = Node : Express

> ***Ballista*** is repackaged on ***Catapult*** basis to make the application easier. But no longer purely functional.

## [Manual](https://guenchi.github.io/Ballista)

***easy to write the router***

```
(get     "/"             index)
(get     "/index"        index)
(get     "/blog/*/en"    blogEN)
(get     "/articles/*"   article)
(get     "/*"            handle404)
(post    "/users"        users)
(post    "/notes"        notes)
```

***easy to control the process***

application-level middleware:
```
(get-use middleware1)
(get-use middleware2)
(get-use middleware3)
...
```

router-level middleware:
```
(get  "/index"  middleware ...  index)
```


***easy to set up the server***

```
(staticpath "/usr/local/www/")
(listen-on "127.0.0.1")
(listen-on 8080)
(listen-on "127.0.0.1" 8080)
```


***[Igropyr](https://guenchi.github.io/Igropyr)*** is an async http-server for Chez Scheme

Ballista's sister framework and dependency: ***[Catapult](https://guenchi.github.io/Catapult)*** (purely functional)


