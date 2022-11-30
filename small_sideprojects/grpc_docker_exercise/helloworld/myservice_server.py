import time
import grpc
from concurrent import futures
import argparse

import torch

from myservice_pb2 import MyOutput
from myservice_pb2_grpc import MyServiceNameServicer, add_MyServiceNameServicer_to_server

class MyServiceNameServer(MyServiceNameServicer):
    def __init__(self, args):
        self.inference = None

    def MyRequestHandler(self, request, context):
        print("===================================================\nrequest from client:")
        print(request)

        response_str = "hello from server. request echo="+request.mystrin
        print("response:", response_str)
        print("\n")
        return MyOutput(mystrout=response_str)

def main(args):
    # create server instance with 4 threads (in thread pool)
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=4), )

    my_service_server = MyServiceNameServer(args=args)
    my_service_servicer = add_MyServiceNameServicer_to_server(my_service_server, server)
    server.add_insecure_port("[::]:{}".format(args.port))
    server.start()

    try:
        # keep the main process alive
        while True:
            time.sleep(60 * 60 * 24)
    except KeyboardInterrupt:
        server.stop(0)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--port", default=35015)
    #parser.add_argument("-lm", "--load_model_path", default="./models/mymodel_checkpoint.pt", type=str, help="load model path")
    args = parser.parse_args()
    main(args)