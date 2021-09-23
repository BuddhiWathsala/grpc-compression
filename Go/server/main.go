package main

import (
	"context"
	"log"
	"net"
	"os"

	"google.golang.org/grpc"
	_ "google.golang.org/grpc/encoding/gzip"

	hp "github.com/BuddhiWathsala/grpc-compression/Go/server/stubs"
	wrapperspb "google.golang.org/protobuf/types/known/wrapperspb"
)

const (
	port = ":50052"
)

type server struct {
	hp.UnimplementedHelloWorldServiceServer
}

func (s *server) Hello(ctx context.Context, in *wrapperspb.StringValue) (*wrapperspb.StringValue, error) {
	dat, _ := os.ReadFile("../../resources/text_file.txt")
	return &wrapperspb.StringValue{Value: string(dat)}, nil
}

func main() {
	maxInMsgSizeOption := grpc.MaxRecvMsgSize(6455878)
	maxOutMsfSizeOption := grpc.MaxSendMsgSize(6455878)
	s := grpc.NewServer(maxInMsgSizeOption, maxOutMsfSizeOption)
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("Failed to listen: %v", err)
	}

	hp.RegisterHelloWorldServiceServer(s, &server{})
	log.Printf("server listening at %v", lis.Addr())
	if err := s.Serve(lis); err != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
