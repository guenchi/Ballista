# Ballista

***Ballista*** is a ***Express style*** webframwork for ***Igropyr***

***Igropyr*** : ***Ballista*** = ***Node*** : ***Express***

> ***Ballista*** is repackaged on ***Catapult*** basis to make the application easier. But no longer purely functional.

***easily to write the router***

```
(get     "/"             index)
(get     "/index"        index)
(get     "/blog/*/en"    blogEN)
(get     "/articles/*"   article)
(get     "/*"            handle404)
(post    "/users"        users)
(post    "/notes"        notes)
```

***easily to control the process***

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


***easily to set up the server***

```
(staticpath "/usr/local/www/")
(listen-on "127.0.0.1")
(listen-on 8080)
(listen-on "127.0.0.1" 8080)
```



***[Manual](https://guenchi.github.io/Ballista)***

***[Raven](http://ravensc.com)*** : Chez Scheme Package Manager 

***[Igropyr](https://guenchi.github.io/Igropyr)*** is a async http-server for Chez Scheme

Ballista's sister framwork and its dependence: ***[Catapult](https://guenchi.github.io/Catapult)*** (purely functional)


