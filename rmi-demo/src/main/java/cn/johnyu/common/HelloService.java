package cn.johnyu.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote {
    public String sayHello(String msg) throws RemoteException;
}