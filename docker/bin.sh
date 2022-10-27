#!/bin/sh

usage() {
  echo "usage:  ./bin.sh [copy|send|base|modules|rm |复制文件|发送到服务器|启动基础服务|启动app|停止并删除docker容器及镜像]"
}

sshkey() {
  echo "配置ssh-key......"
  ssh-copy-id root@$1
}

copy() {
  echo "begin copy jar......"
  cp -rf ../minio-admin/target/minio-admin.jar ./admin/jar/minio-admin.jar
  echo "begin copy dist......"
  cp -rf ../minio-ui/dist/** ./nginx/html/dist
}

send() {
  echo "发送docker到$1:/home/app/minio......"
  scp -r ../docker root@$1:/app/minio/
}

base(){
  docker-compose up -d minio-service minio-nginx
}

modules() {
    docker-compose up -d minio-app
}

rm() {
  docker stop minio-app
  docker rm minio-app
  docker rmi docker_minio-app
}

case "$1" in
"rm")
  rm
;;
"copy")
  copy
;;
"base")
  base
;;
"modules")
  modules
;;
"stop")
  stop
;;
"send")
  send $2
;;
"sshkey")
  sshkey $2
;;
*)
  usage
;;
esac
