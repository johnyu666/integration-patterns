package xmlrpc.client;
 
import java.net.MalformedURLException;
import java.net.URL;
 
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
 
public class ClientTest {
    public static void main(String[] args) {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://127.0.0.1:8080/XML_RPC_Server/service"));
            
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);
            Object[] params = new Object[] { 32, 9 };
            Integer result = (Integer) client.execute("Calculator.add", params);
            System.out.printf("两数相加的结果是：%d\n",result);
            
            result = (Integer) client.execute("Calculator.subtract", params);
            System.out.printf("两数相减的结果是：%d",result);
 
        } catch (XmlRpcException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}