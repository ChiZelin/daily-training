package com.czl.learn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        //线程池机制

        //思路
        //1.创建一个线程池
        //2.如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了...");

        while (true) {
            //监听，等待客户端连接

            System.out.println("等待连接...");
            final Socket socket = serverSocket.accept();
            System.out.println("连接了一个客户端...");

            //创建一个线程，与客户端通讯(单独写一个方法)
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    //可以和客户端通讯
                    handler(socket);

                }
            });
        }
    }

    //将与客户端通讯的逻辑提取到一个方法中
    public static void handler(Socket socket) {

        //通过 socket 或取输入流
        try {

            System.out.println("线程信息 id =" + Thread.currentThread().getId() +
                "线程名字： " + Thread.currentThread().getName());

            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            //循环读取
            while(true) {

                System.out.println("Read.....");
                int read = inputStream.read(bytes);

                if(read != -1) {
                    System.out.println("线程信息 id =" + Thread.currentThread().getId() +
                        "线程名字： " + Thread.currentThread().getName());

                    System.out.println(new String(bytes,0, read));
                }else {
                    System.out.println("线程信息 id =" + Thread.currentThread().getId() +
                        "线程名字： " + Thread.currentThread().getName() + "已退出！！！");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
