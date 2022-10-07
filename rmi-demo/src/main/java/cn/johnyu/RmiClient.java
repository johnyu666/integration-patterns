package cn.johnyu;

import java.rmi.Naming;

public class RmiClient {
    public static void main(String[] args) throws Exception{
        HelloService helloService = (HelloService) Naming.lookup("rmi://127.0.0.1:3001/helloService");
        String info=helloService.sayHello("Mr.John123");
        System.out.println(info);
    }
}