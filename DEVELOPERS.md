# Developers

## Running

```console
$ docker-compose up
$ lein repl
user> (go)
```

## Heroku

Heroku Redis maxmemory-policy is `volatile-lru`

```console
$ heroku redis:maxmemory --policy volatile-lru
```
