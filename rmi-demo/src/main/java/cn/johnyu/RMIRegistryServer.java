package cn.johnyu;

import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class RMIRegistryServer {
    public static void main(String[] args) throws Exception{
        //启动一个RMI注册服务器(在子线程中启动)
        LocateRegistry.createRegistry(3001);
        System.out.println("RMI 注册服务器启动完毕...");
        //阻塞主线程，使用RMI注册服务器保持运行
        new Scanner(System.in).next();
    }
}