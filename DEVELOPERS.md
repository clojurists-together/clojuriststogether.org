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

## Markdown notes

Section headings use multimarkdown metadata.

Title: Name Of Page



% is used for forcing something to be processed literally. Notably used on the Paypal form pages to makes sure that `<input type="hidden" name="hosted_button_id" value="EB8BF96SAUTK6">` isn't transformed into `<input type="hidden" name="hosted<i>button</i>id" value="EB8BF96SAUTK6">`

## Netlify

The `netlify` directory and `netlify.toml` is used by [Netlify](https://www.netlify.com). It serves a redirect from `clojuriststogether.org` to `www.clojuriststogether.org`. If our DNS provider (Hover) supported ALIAS records or ANAMEs then we wouldn't need to use this, and could instead set both www and the bare domain to both point to Heroku and handle the redirects there.  
