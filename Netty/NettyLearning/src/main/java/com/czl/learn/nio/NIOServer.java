package com.czl.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel =
            ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while(it.hasNext()) {
                SelectionKey sk = it.next();

                if(sk.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) sk.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int len = 0;

                    while ((len = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0 , len));
                        buffer.clear();
                    }
                }

                it.remove();
            }
        }
    }
}
