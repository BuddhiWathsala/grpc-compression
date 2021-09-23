import com.google.protobuf.StringValue;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class HelloWorldClient {

    private final HelloWorldServiceGrpc.HelloWorldServiceBlockingStub blockingStub;

    public HelloWorldClient(ManagedChannel channel) {

        blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();

        try {
            HelloWorldClient client = new HelloWorldClient(channel);
            Path fileName = Path.of("../../resources/text_file.txt");
            String content = "";
            try {
                content = Files.readString(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Size: " + content.getBytes(StandardCharsets.UTF_8).length);
            StringValue msg = client.blockingStub.withCompression("gzip").withMaxInboundMessageSize(6455878)
                    .withMaxOutboundMessageSize(6455878).hello(StringValue.of(content));
            System.out.println(msg.getValue());

        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
