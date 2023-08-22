# echo

echo is a toy microservice built with gRPC and jOOQ

## Quickstart

To run the database migration and build the project:

```bash
./gradlew migrateDb
./gradlew build
```

To start the gRPC server listening for incoming requests:

```bash
./gradlew runServer
```

To send a request to the server using the gRPC client:

```bash
./gradlew runClient -Pargs='Hello, World!'
```

To start the HTTP-to-gRPC proxy listening for incoming requests:

```bash
./gradlew runProxy
```

To send a HTTP POST request to the proxy:

```bash
curl -X POST -d "Hello, World!" http://localhost:8081/echo
```
