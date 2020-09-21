package com.czl.learn.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URI;

//1.说明 SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter
//2.HttpObject 客户端和服务器端相互通讯的数据被封装成 HttpObject 类型
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //channelRead0 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断 msg 是不是 HttpRequest 请求
        if(msg instanceof HttpRequest) {

            System.out.println(
                "pipeline hashcode" + ctx.pipeline().hashCode()+
                    " TestHttpServerHandler hash = " + this.hashCode());
            System.out.println("msg 类型 = " + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());

            //获取到
            HttpRequest httpRequest = (HttpRequest) msg;
            //获取 uri
            URI uri = new URI(httpRequest.uri());
            // 对特定资源进行过滤
            if("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 favicon.ico，不做响应");
                return;
            }

            //回复信息给浏览器 [http 协议]
            ByteBuf content = Unpooled.copiedBuffer("hello, this is server!", CharsetUtil.UTF_8);

            // 构造一个 http 的相应， 即 HttpResponse
            FullHttpResponse fullHttpResponse =
                new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //将构建好 的 response 返回
            ctx.writeAndFlush(fullHttpResponse);
        }
    }
}
