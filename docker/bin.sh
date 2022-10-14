#!/bin/sh

usage() {
  echo "usage:  ./bin.sh [base|modules|send {发送到服务器}|sshkey {配置ssh}|stop|rm]"
}

sshkey() {
  echo "配置ssh-key......"
  ssh-copy-id root@$1
}

send() {

  echo "发送docker到$1:/home/app/minio......"
  scp -r ../docker root@$1:/home/app/minio/
}

base(){
  docker-compose up -d minio-service
}

case "$1" in
"base")
  base
;;
"modules")
  modules
;;
"stop")
  stop
;;
"rm")
  rm
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
