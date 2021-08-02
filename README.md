[![CircleCI](https://circleci.com/gh/mauriciovalli/topfarming-poc.svg?style=shield&circle-token=f546068af510735a08f43ba5fdb318d7458d12a3)](https://circleci.com/gh/mauriciovalli/topfarming-poc)
#Top Farming POC

##Requerimientos de la prueba de concepto
Se necesita generar un proyecto de código cumpliendo con las definiciones técnicas arriba descriptas y cerradas.
- Kotlin, Micronaut, GraalVM, Postgres, Redis, Elastic, OAuth
- Gradle/Kotlin, GraalVM native binary
- REST con JSON o Protobuf

La aplicación debe tener las siguientes funciones:
- Exponer métodos de API REST
- Verificar un token OAuth2 contra un server externo
- Recibir un identificador de usuario (dentro del token o fuera)
- Responder en JSON o Protobuf según el header de Accept (como lo dice el estándar)
- Leer datos secretos de propiedades inyectadas en runtime (no dentro del binario)
- Conectarse a la base de datos, al redis y al elastic (a modo de ejemplo)

##This project uses:
- Micronaut version 2.5.11
- Kotlin version 1.5.21
- Protobuf version 3.17.3