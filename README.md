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


***install***

`$ raven install ballista`



***use***

just
```
(server-on)
```

***Manuel***: https://guenchi.gitbooks.io/igropyr/content/ballista.html

***Raven***: Chez Scheme Package Manager http://ravensc.com

***Igropyr*** is a async http-server for Chez Scheme

https://github.com/guenchi/Igropyr

Ballista's sister framwork: ***Catapult*** (purely functional)

https://github.com/guenchi/Catapult
