# gh-12871
Demos for https://github.com/spring-projects/spring-security/issues/12871

I have no more CSRF cookie set with Spring Security 6 or when switching from Webmvc to WebFlux.

The project under `gh-12871-webmvc` is a servlet with spring-boot `2.7.9` and `http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())`. When browsing to index, CSRF cookie is set.

If switching this `gh-12871-webmvc` from `2.7.9` to `3.0.4`, the cookie is not set anymore.

The project under `gh-12871-webflux` is a reactive application with `http.csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())`. The CSRF cookie is never set, whatever the Boot version.