# gRPC Compression

Compression is used in networking to reduce the amount of bandwidth consumed by network communication. Similarly, the gRPC protocol enables compression over the network. There are two main types of gRPC compression schemes.
1. gRPC channel-level compression
1. gRPC message-level compression

Many languages prefer to support message-level compression, and the most popular compression algorithm in gRPC is Gzip. gRPC compression, in general, does not have many clear and concise resources. The primary intention of this repository is to provide examples that demonstrate the gRPC message compression functionality in different languages, Go, Java, and Ballerina. This repository contains both gRPC client and server implementations in Go, Java, and Ballerina. Users have to freedom try these examples either using clients and servers from the same language or different languages. 

Note that, you can verify the gRPC message compression using Wireshark. You just have to filter out the HTTP2 or gRPC packets and check the compressed flag or relevant message size details.

# For More Information
- https://github.com/grpc/grpc-go/blob/v1.41.x/Documentation/compression.md
- https://github.com/ballerina-platform/module-ballerina-grpc/pull/448
- https://github.com/ballerina-platform/ballerina-standard-library/issues/1952
- https://github.com/ballerina-platform/module-ballerina-grpc/blob/master/docs/proposals/compression.md

