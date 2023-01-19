import grpc
import argparse
from myservice_pb2 import MyInput
from myservice_pb2_grpc import MyServiceNameStub

class MyServiceNameClient:
    def __init__(self, ip="localhost", port=35015):
        self.server_ip = ip
        self.server_port = port
        self.stub = MyServiceNameStub(
            grpc.insecure_channel(self.server_ip + ":" + str(self.server_port))
        )

    def get_myservice(self, inputs):
        myinput = MyInput()
        myinput.mystrin = inputs

        myservice_out = self.stub.MyRequestHandler(myinput)
        return myservice_out.mystrout

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", default="localhost", type=str, help="IP address")
    parser.add_argument("--port", default=35015, type=int, help="port number")
    parser.add_argument("--input", default="test", type=str, help="request string")
    args = parser.parse_args()

    myservice_client = MyServiceNameClient(ip=args.ip, port=args.port)
    print("Output:", myservice_client.get_myservice(args.input))