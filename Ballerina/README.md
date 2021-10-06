## Prerequisites
This example uses:
- Ballerina language version Swan Lake Beta 3+
- Ballerina gRPC library version `1.0.0+`

## Enable gRPC Compression in Ballerina
Unlike Go and Java, there are no two different ways to enable compression in Ballerina. Ballerina has an API to enable compression on the client and the server sides as follows.

### Client Side
Initiate a header map with Gzip compression headers and pass it to the RPC call.

```ballerina
map<string|string[]> headers = grpc:setCompression(grpc:GZIP);
ContextString cs = check ep->helloContext({content: s, headers: headers});
```

Even users can reuse an existing header map to populate compression headers.

```ballerina
map<string|string[]> headers = {...};
headers = grpc:setCompression(grpc:GZIP, headers);
ContextString cs = check ep->helloContext({content: s, headers: headers});
```

### Server Side
Compression enabling on the server-side is also similar to the client-side.

```ballerna
remote function hello(string value) returns ContextString|error {
    ...
    map<string|string[]> headers = grpc:setCompression(grpc:GZIP);
    return {content: "content", headers: headers};
}
```

## Running the Example

Use the following commands to run this sample in two separate terminals, one for the client and the other for the server.

In terminal 01.
```sh
$ cd grpc-compression/Ballerina/server/
$ bal run
```

In terminal 02.
```sh
$ cd grpc-compression/Ballerina/client/
$ bal run
```
