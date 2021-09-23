import ballerina/grpc;
import ballerina/io;

listener grpc:Listener ep = new (50052);

@grpc:ServiceDescriptor {descriptor: ROOT_DESCRIPTOR_HELLO, descMap: getDescriptorMapHello()}
service "HelloWorldService" on ep {

    remote function hello(string value) returns ContextString|error {
        string s = check io:fileReadString("../../resources/text_file.txt");
        io:println(s.toBytes().length());
        map<string|string[]> headers = grpc:setCompression(grpc:GZIP);
        return {content: s, headers: headers};
    }
}
