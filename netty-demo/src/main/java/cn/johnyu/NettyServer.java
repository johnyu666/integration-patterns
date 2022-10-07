package cn.johnyu;




import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


import java.nio.charset.StandardCharsets;

public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup(1);//Parnet线程只用一个
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);//Worker线程，可以设置1...n

        //这是一个Helper类，简化生成ServerSocketChannel的模板代码的
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .channel(NioServerSocketChannel.class)//指定"Channel类型",这是该helper类的目标
                .group(parentGroup, workerGroup)//设置"ServerSocketChannel线程组"和"SocketChannel线程组"
                .option(ChannelOption.SO_BACKLOG, 64)//ServerSocket的配置参数（可选）：最大64个连接的排队
                .childOption(ChannelOption.SO_BACKLOG, 64)//Socket的配置参数
                .childOption(ChannelOption.SO_KEEPALIVE, true)

                //当有客户端的连接，会自动生成Socket对象，同时会生成与socket对象对应的Channel对象
                //此处是添加Channel的处理对象：ChannelHandler,但由于需要添加比较多的数量，所以此处可以使用Initializer对象，进行流式API的封装
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        //channel是流式API,可以添加ChannelHandler(1,2是预定义Handler,3是自定义Handler)
                        channel.pipeline()
                                //接收到的数据进行文字解码,接收到数据类型也会转型为String类型（否则是ByteBuffer类型）
                                .addLast("decoder", new StringDecoder(StandardCharsets.UTF_8))
                                //发送的数据时行文字编码
                                .addLast("encoder", new StringEncoder(StandardCharsets.UTF_8))
                                //添加自定义的Handler(使用Adapter模式，只处理具体事件)
                                .addLast(new MyChannelHandler());
                    }
                });
        try {
            //同步启动服务器（在Parent线程组中）
            bootstrap.bind(9200).sync();
            System.out.println("服务器已经在 9000 端口启动...");
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            parentGroup.shutdownGracefully();//防止线程泄漏
            workerGroup.shutdownGracefully();
        }
    }
}
