## Prerequisites
This example uses:
- Java `11`
- Java gRPC library version `1.29.0`

## Enable gRPC Compression in Java
There are two different ways to enable gRPC compression at the client and the server sides.

### Client Side
Users have to invoke the `withCompression` API in the stub class.

```java
StringValue msg = client.blockingStub.withCompression("gzip").hello(StringValue.of("content"));
```

### Server Side
On the server side, users have to use an interceptor to enable gRPC compression as below.

```java
server = ServerBuilder.forPort(50052).intercept(
    new ServerInterceptor() {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
                                                                  ServerCallHandler<ReqT, RespT> next) {
        call.setCompression("gzip");
        return next.startCall(call, headers);
    }
})
.addService(new HelloWorldServiceImpl())
.build()
.start();
```

## Running the Example

Use the following commands to run this sample in two separate terminals, one for the client and the other for the server.

In terminal 01.
```sh
$ cd grpc-compression/Java/server/
$ ./gradlew clean run
```

In terminal 02.
```sh
$ cd grpc-compression/Java/client/
$ ./gradlew clean run
```