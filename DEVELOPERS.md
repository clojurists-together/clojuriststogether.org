# Developers

## Running

```console
$ hugo server
```

## Netlify

The `netlify` directory and `netlify.toml` is used by [Netlify](https://www.netlify.com). It serves a redirect from `clojuriststogether.org` to `www.clojuriststogether.org`. If our DNS provider (Hover) supported ALIAS records or ANAMEs then we wouldn't need to use this, and could instead set both www and the bare domain to both point to Heroku and handle the redirects there.  
