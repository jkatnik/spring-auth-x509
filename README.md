# Jak skonfigurować autentykację x509 MTLS (mutual authentication) w Spring'u

## Materiały
- https://www.baeldung.com/x-509-authentication-in-spring-security
- https://www.baeldung.com/spring-cloud-openfeign

## 
1. wygenerować certyfikat własnego Authentication Center (aka Self Signed Root CA)
2. wygenerować certyfikat dla resource servera (server-side certificate)
	a. wygenerować plik z certificate signing request
	b. wygenerować plik konfiguracyjny localhost.ext
	c. podpisać request kluczem prywatnym Root CA
3. zaimportować certyfikat i klucz prywatny resource servera do JKS (key store)
4. utworzyć aplikację resource-server, skonfigurować w application.properties SSL
5. zaimportować CA Root cert do przeglądarki
6. utworzyć truststore i umieścić w nim rootCA.crt
7. Utworzyć SecurityConfig
   a. w SecurityFilterChain włączyć x509
   b. w UserDetailsService zbudować UserDetails i nadać rolę
8. dodać `@PreAuthorize("hasAuthority('ROLE_USER')")` do endpointu
9. wygenerować certyfikat dla klienta, ustawić Common Name (CN)
10. zainstalować certyfikat klienta w przeglądarce
11. utworzyć aplikację rest-client a w niej endpoint call-user
12. dodać OpenFeign