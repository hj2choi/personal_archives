import time
import grpc
import base64
from io import BytesIO
from PIL import Image
from concurrent import futures
import argparse

import torch

from image_classification_service_pb2 import ClassificationResult
from image_classification_service_pb2_grpc import ImageClassificationServicer, add_ImageClassificationServicer_to_server

class ImageClassificationServer(ImageClassificationServicer):
    def __init__(self, args):
        self.inference = None

    def ImageClassificationRequestHandler(self, request, context):
        print("===================================================\nrequest from client:")
        print(request)

        decoded_image = base64.b64decode(request.image_data)  # binary image
        im_file = BytesIO(decoded_image)  # convert image to file-like object
        img = Image.open(im_file)  # img is now PIL Image object
        img.show()

        print("request echo:", request.image_data)
        print("response:", 1)
        print("\n")
        return ClassificationResult(cls=1)

def main(args):
    # create server instance with 4 threads in thread pool
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=4), )

    image_classification_server = ImageClassificationServer(args=args)
    my_service_servicer = add_ImageClassificationServicer_to_server(image_classification_server, server)
    server.add_insecure_port("[::]:{}".format(args.port))
    server.start()

    try:
        # keep the main process alive till keyboard interrupt
        while True:
            time.sleep(9999999)
    except KeyboardInterrupt:
        server.stop(0)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--port", default=35015)
    #parser.add_argument("-lm", "--load_model_path", default="./models/mymodel_checkpoint.pt", type=str, help="load model path")
    args = parser.parse_args()
    main(args)