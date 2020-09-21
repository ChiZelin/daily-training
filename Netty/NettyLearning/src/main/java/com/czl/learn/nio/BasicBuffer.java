package com.czl.learn.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        //Buffer 的使用
        //创建一个 Buffer, 大小 5， 即可以存放 5 个 int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        /*intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);*/

        for(int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        //从 buffer 读取数据
        //将 buffer 转换，读写切换
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
