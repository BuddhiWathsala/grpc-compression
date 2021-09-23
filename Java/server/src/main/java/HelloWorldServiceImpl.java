import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void hello(StringValue request, StreamObserver<StringValue> responseObserver) {
        Path fileName = Path.of("../../resources/text_file.txt");
        String content = "";
        try {
            content = Files.readString(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Size: " + content.getBytes(StandardCharsets.UTF_8).length);
        responseObserver.onNext(StringValue.of(content));
        responseObserver.onCompleted();
    }
}

