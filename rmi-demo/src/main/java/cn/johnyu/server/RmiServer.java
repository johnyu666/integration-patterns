package cn.johnyu.server;

import cn.johnyu.common.HelloService;

import java.rmi.Naming;

public class RmiServer {
    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        //向服务器进行注册
        Naming.bind("rmi://localhost:3001/helloService",helloService);
        System.out.println("helloService 注册完毕！");

    }
}
