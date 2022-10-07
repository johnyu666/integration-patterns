package cn.johnyu;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyChannelHandler extends  ChannelInboundHandlerAdapter{

    //当channle被激活的回调（第次连接只发生一次）
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端的连接 channel id is ："+ctx.channel().id());
    }

    //当客户端发送数据时的回调处理
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //msg：如果设置了encoder编码器，则msg为String类型
        System.out.print("服务器线程名："+Thread.currentThread().getName()+"收到数据：" + msg);

        //ctx:处理连接上下文的对象
        if(msg.toString().startsWith(".exit")){
            System.out.println("exit.....");
            ctx.disconnect();

        }
        ctx.writeAndFlush("Hello:"+msg);
    }

}