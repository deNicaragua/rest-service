A service that accesses the service of exchange rates and sends a gif in response. 
If the exchange rate against the ruble for today has become higher than yesterday,
then we give a random one from here https://giphy.com/search/rich,
if below - from here https://giphy.com/search/broke
Spring Boot 2 + Java service Requests arrive at the HTTP endpoint,
where the currency code is passed. 
Feign is used to interact with external services. 
Building and running with a Docker container.
***
# Host
```
[Example](http://localhost:8080)
```
# Docker
### Image creation:
```
make image
```
### Start:
```
make run
```