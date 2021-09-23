package main

import (
	"context"
	"log"
	"time"
	"os"


	pb "github.com/BuddhiWathsala/grpc-compression/Go/client/stubs"
	"google.golang.org/grpc"
	"google.golang.org/grpc/encoding/gzip"
	"google.golang.org/protobuf/types/known/wrapperspb"
)

const (
	address     = "localhost:50052"
	defaultName = "world"
)

func main() {

	conn, err := grpc.Dial(address, grpc.WithInsecure())
	dat, _ := os.ReadFile("../../resources/text_file.txt")
	if err != nil {
		log.Fatalf("Did not connect: %v", err)
	}
	defer conn.Close()
	c := pb.NewHelloWorldServiceClient(conn)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	maxReqSizeOption := grpc.MaxCallRecvMsgSize(6455878)
	maxResSizeOption := grpc.MaxCallSendMsgSize(6455878)
	compressor := grpc.UseCompressor(gzip.Name)
	r, err := c.Hello(ctx, &wrapperspb.StringValue{Value: string(dat)}, maxReqSizeOption, maxResSizeOption, compressor)

	if err != nil {
		log.Fatalf("Could not greet: %v", err)
	}
	log.Printf("Greeting: %s", r)
}
