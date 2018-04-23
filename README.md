# Ballista

***Ballista*** is a ***Express style*** webframwork for ***Igropyr***

***Igropyr*** : ***Ballista*** = ***Node*** : ***Express***

> ***Ballista*** is repackaged on ***Catapult*** basis to make the application easier. But no longer purely functional.

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

***easily to control the process***

any numbers of application layer middleware
```
(get-use middleware1)
(get-use middleware2)
(get-use middleware3)
...
```

any numbers of router layer middleware:
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

***Igropyr*** is a async http-server for Chez Scheme

https://github.com/guenchi/Igropyr

Ballista's sister framwork: ***Catapult*** (purely functional)

https://github.com/guenchi/Catapult
