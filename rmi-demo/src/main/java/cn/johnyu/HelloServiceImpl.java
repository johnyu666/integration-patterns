package cn.johnyu;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class HelloServiceImpl extends UnicastRemoteObject implements HelloService{
    protected HelloServiceImpl() throws RemoteException {}
    @Override
    public String sayHello(String msg) throws RemoteException {
        return "您好 ,"+msg;
    }
    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        //向服务器进行注册
        Naming.bind("rmi://localhost:3001/helloService",helloService);
        System.out.println("helloService 注册完毕！");

    }
}