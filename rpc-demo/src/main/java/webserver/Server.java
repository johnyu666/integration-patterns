package webserver;
 
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int port = 8080;
    public static void main(String[] args) throws Exception {   
        WebServer webServer = new WebServer(port);//http的连接器
        //配置XMLRpc的服务
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        PropertyHandlerMapping phm = new PropertyHandlerMapping();

//        phm.load(Thread.currentThread().getContextClassLoader(), "MyHandlers.properties");
        Map<String,String> map=new HashMap<>();
        map.put("Calculator","service.Calculator");
        phm.load(Thread.currentThread().getContextClassLoader(),map);
        xmlRpcServer.setHandlerMapping(phm);
        webServer.start();//启动服务
        System.out.println("服务已经成功的在"+port);
    }
}