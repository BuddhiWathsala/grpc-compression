## Prerequisites
This example uses:
- Go language version `1.16.6`
- Go gRPC library version `1.40.0`

## Enable gRPC Compression in Go
There are two different ways to enable gRPC compression at the client and the server sides.

### Client Side
Users have to pass Gzip compressor call option to the client-size RPC call. Here, `Hello` is a unary RPC call.

```go
compressor := grpc.UseCompressor(gzip.Name)
r, err := c.Hello(ctx, &wrapperspb.StringValue{Value: string("Hello")}, compressor)
```

### Server Side
On the server-side, there is no value passing as on the client-side. Instead, users have to import the following package into the Go program.

```go
import (
    _ "google.golang.org/grpc/encoding/gzip"
)
```

## Running the Example

Use the following commands to run this sample in two separate terminals, one for the client and the other for the server.

In terminal 01.
```sh
$ cd grpc-compression/Go/server/
$ go build
$ ./server
```

In terminal 02.
```sh
$ cd grpc-compression/Go/client/
$ go build
$ ./client
```
