# gRPC Compression

Compression is used in networking to reduce the amount of bandwidth consumed by network communication. Similarly, the gRPC protocol enables compression over the network. There are two main types of gRPC compression schemes.
1. gRPC channel-level compression
1. gRPC message-level compression

Many languages prefer to support message-level compression, and the most popular compression algorithm in gRPC is Gzip. gRPC compression, in general, does not have many clear and concise resources. The primary intention of this repository is to provide examples that demonstrate the gRPC message compression functionality in different languages, Go, Java, and Ballerina.

# For More Information
- https://github.com/grpc/grpc-go/blob/v1.41.x/Documentation/compression.md
- https://github.com/ballerina-platform/module-ballerina-grpc/pull/448
- https://github.com/ballerina-platform/ballerina-standard-library/issues/1952
- https://github.com/ballerina-platform/module-ballerina-grpc/blob/master/docs/proposals/compression.md

