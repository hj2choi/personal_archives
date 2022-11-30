import grpc
import argparse
import base64
from image_classification_service_pb2 import ImageUpload
from image_classification_service_pb2_grpc import ImageClassificationStub

class ImageClassificationServiceClient:
    def __init__(self, ip="localhost", port=35015):
        self.server_ip = ip
        self.server_port = port
        self.stub = ImageClassificationStub(
            grpc.insecure_channel(self.server_ip + ":" + str(self.server_port))
        )

    def request_image_classification_service(self, image_path):
        # read image as bytes
        with open(image_path, 'rb') as open_file:
            byte_content = open_file.read()

        # base64 encoding => bytes
        base64_bytes = base64.b64encode(byte_content)

        request = ImageUpload()
        request.image_data = base64_bytes

        image_classification_res = self.stub.ImageClassificationRequestHandler(request)
        return image_classification_res.cls

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", default="localhost", type=str, help="IP address")
    parser.add_argument("--port", default=35015, type=int, help="port number")
    parser.add_argument("--image_path", default="test", type=str, help="request string")
    args = parser.parse_args()

    image_classification_service_client = ImageClassificationServiceClient(ip=args.ip, port=args.port)
    print("Output:", image_classification_service_client.request_image_classification_service(args.image_path))