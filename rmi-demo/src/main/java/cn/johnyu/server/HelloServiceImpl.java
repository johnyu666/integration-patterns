package cn.johnyu.server;

import cn.johnyu.common.HelloService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
    protected HelloServiceImpl() throws RemoteException {}
    @Override
    public String sayHello(String msg) throws RemoteException {
        return "您好 ,"+msg;
    }

}