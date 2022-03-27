# mutantes-api

Dada una cadena de ADN verifica si es mutante o no-mutante.

## Dependencias
1. [mutantes-registry](https://github.com/arthurmessias/mutantes-registry) - NetFlix Eureka Server
2. [mutantes-db-api](https://github.com/arthurmessias/mutantes-db-api) - Microservice para pesistencia de las estadisticas en MongoDB

## Para clonar este repositorio
```bash
git clone https://github.com/arthurmessias/mutantes-api.git
cd mutantes-api
```

## Unit tests
```bash
mvn test
```
## Start Microservice
```bash
mvn spring-boot:run
```

## Microservice endpoint
* Local endpoint: http://localhost:8090
* Heroku endpoint: https://mutantes-svc-api.herokuapp.com
* Swagger UI `/mutantes-api/swagger-ui.html`

### POST /mutant
```
http://localhost:8090/mutantes-api/mutant?version=1
```
___
#### Mutante
**Parameters**

`version=1`

**Body**

`application/json`
```
{
    "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```

**Response**
`text/plain;charset=UTF-8` `200-OK`
```
Mutant
```
___

#### No-Mutante
**Parameters**

`version=1`

**Body**

`application/json`
```
{
    "dna":["AGAATT","CGCCTT","TTATTT","AGACGG","GCGTCA","TCACTG"]
}
```

**Response**
`application/json` `403-Forbidden`
```
{
    "timestamp": "2022-03-26T00:38:08.208+00:00",
    "message": "No-Mutante",
    "details": "uri=/mutantes-api/mutant"
}
```
