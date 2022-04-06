## execution guide
```bash
python -m pip --no-cache-dir install --upgrade pip grpcio grpcio-tools
python -m grpc.tools.protoc -I. --python_out . --grpc_python_out . ./myservice.proto
```

```bash
python myservice_server.py
```
in a seperate terminal, run
```bash
python myservice_client.py --input hello_world
```

## execution guide with docker
#### build docker image
```bash
docker build -t {my new image name}:{my new tag} .
docker build -t grpc_practice_image:grpc_practice .
```

#### check docker images
```bash
docker images
```

#### create server container
```bash
docker run -dit --restart on-failure --gpus "{device to use}" -p {external access port}:{server.py port (internal access port)} --name {new container name} {previously created image name}:{previously created image tag}
docker run -dit --restart on-failure --gpus all -p 222:35015 --name grpc_practice_container grpc_practice_image:grpc_practice
```
use -it to access server.py error log. <br>
docker: Error response from daemon: could not select device driver "" with capabilities: [[gpu]]. <br>
https://bluecolorsky.tistory.com/110


#### test server with client
```bash
docker exec -it grpc_practice_container /bin/bash
python myservice_client.py --input hello_world!
```