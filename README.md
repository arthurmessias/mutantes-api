# mutantes-api

Dada una cadena de ADN verifica si es mutante o no-mutante.

## Dependencias
[mutantes-registry](https://github.com/arthurmessias/mutantes-registry) - NetFlix Eureka Server

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
Swagger UI http://localhost:8090/mutantes-api/swagger-ui.html

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
