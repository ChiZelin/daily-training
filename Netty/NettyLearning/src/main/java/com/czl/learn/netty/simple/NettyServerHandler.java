package com.czl.learn.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/*
* 说明
* 1.自定义一个Handler 需要继续 netty 规定好的某个 HandlerAdapter
* 2.这时我们自定义一个 Handler， 才能称为一个 Handler
* */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    //读取数据实际
    //ChannelHandlerContext 含有 管道 pipeline, 通道 channel，地址
    //Object msg: 就是客户端发送的数据 默认 object
    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        /*System.out.println("服务器读取线程 " + Thread.currentThread().getName());
        System.out.println("Server ctx =" + ctx);
        System.out.println("看看channel 和 pipline的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline(); //本质上是一个双向链表，出栈入栈

        //ByteBuf 是 netty 提供的，不是 NIO 的 ByteBuffer.
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("客户端发送消息是：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());*/



        //比如有一个非常耗时的业务-> 异步执行 -> 把它提交到该 Channel 对应的 NIOEventLoop 的 taskQueue 中，

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello 客户端2~", CharsetUtil.UTF_8));
                }catch (Exception e) {
                    System.out.println("发生异常：" + e.getMessage());
                }

            }
        });

        //用户自定义定时任务，该任务是提交到 ScheduleTaskQueue 中

        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    //Thread.sleep(2 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello 客户端haha~", CharsetUtil.UTF_8));
                }catch (Exception e) {
                    System.out.println("发生异常：" + e.getMessage());
                }

            }
        },0, TimeUnit.SECONDS);


        System.out.println("go on....");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存，并刷新
        //一般将，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello 客户端~", CharsetUtil.UTF_8));
    }

    //异常处理，一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception {
        ctx.close();
    }

}
