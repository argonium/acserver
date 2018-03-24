# Aircraft Server

Coding challenge for a REST API to manage an aircraft queue.

The completed work is the REST API and unit tests.  The API stores
the aircraft in memory (no database).

## Commands

To build the JAR:

```
$ ./gradlew bootJar
```

To run the unit tests:

```
$ ./gradlew test
```

To run the JAR from the command line:

```
$ java -jar build/libs/acserver-0.0.1-SNAPSHOT.jar
```

The server will be available at http://localhost:8080/

Requires Java 8 and Gradle to build.


## Endpoints

To list the aircraft in the queue:

```
GET /api/list
```

To dequeue the aircraft from the queue; this returns the dequeued aircraft:

```
GET /api/dequeue
```

To enqueue an aircraft:

```
POST /api/enqueue
```

Include a JSON payload of the aircraft traits:

* id: A unique number
* modelType: Emergency, VIP, Passenger, Cargo
* modelSize: Large, Small

Sample curl command:

```
$ curl -X POST -H "Content-Type: application/json" -d '{"id":1, "modelType":"Cargo", "modelSize":"Large"}' http://localhost:8080/api/enqueue
```

## TODO
* Add Swagger support

