import ballerina/io;
import ballerina/grpc;

public function main() returns error? {
    string s = check io:fileReadString("../../resources/text_file.txt");

    HelloWorldServiceClient ep = check new ("http://localhost:50052");
    map<string|string[]> headers = grpc:setCompression(grpc:GZIP);
    ContextString cs = check ep->helloContext({content: s, headers: headers});
    io:println(cs);
}

