
![img](img/ballista.jpg)


***Installation with Raven***

`$ raven install ballista`

***use***

just
```
(server-on)
```




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




