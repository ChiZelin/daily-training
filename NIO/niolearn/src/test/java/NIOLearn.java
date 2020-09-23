
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class NIOLearn {

	@Test
	public void send() throws IOException {
		DatagramChannel dc = DatagramChannel.open();

		dc.configureBlocking(false);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			String string = scanner.next();
			buffer.put((new Date().toString() + ":\n" + string).getBytes());
			buffer.flip();
			dc.send(buffer, new InetSocketAddress("127.0.0.1",9999));
			buffer.clear();
		}

		dc.close();
	}

	@Test
	public void testPip() throws IOException {
		Pipe pipe = Pipe.open();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		Pipe.SinkChannel sinkChannel = pipe.sink();

		buffer.put("通过单向管道发送数据".getBytes());
		buffer.flip();
		sinkChannel.write(buffer);

		Pipe.SourceChannel sourceChannel = pipe.source();
		buffer.flip();
		int len = sourceChannel.read(buffer);
		System.out.println(new String(buffer.array(), 0, len));

		sourceChannel.close();
		sinkChannel.close();

	}

	@Test
	public void receive() throws IOException {
		DatagramChannel dc = DatagramChannel.open();

		dc.configureBlocking(false);

		dc.bind(new InetSocketAddress(9999));

		Selector selector = Selector.open();

		dc.register(selector, SelectionKey.OP_READ);

		while (selector.select() > 0) {
			Iterator<SelectionKey> iterable =
				selector.selectedKeys().iterator();

			while (iterable.hasNext()) {
				SelectionKey sk = iterable.next();

				if(sk.isReadable()) {
					ByteBuffer buffer = ByteBuffer.allocate(1024);

					dc.receive(buffer);

					buffer.flip();

					System.out.println(new String(buffer.array(),0,buffer.limit()));

					buffer.clear();
				}
			}

			iterable.remove();
		}
	}

	@Test
	public void test1() {
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		System.out.println(buffer.isDirect());
	}


	@Test
	public void testClient() throws IOException {
		SocketChannel sc = SocketChannel.open(
			new InetSocketAddress("127.0.0.1",9999));

		FileChannel fc = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while(fc.read(buffer) != -1) {
			buffer.flip();

			sc.write(buffer);

			buffer.clear();
		}

		sc.shutdownOutput();

		int len;

		while((len = sc.read(buffer)) != -1) {
			buffer.flip();
			System.out.println(new String(buffer.array(),0 ,len));
			buffer.clear();
		}

		fc.close();
		sc.close();

	}


	@Test
	public void testServer() throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();

		FileChannel outChannel =
			FileChannel.open(
				Paths.get("2.jpg"),
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		ssc.bind(new InetSocketAddress(9999));

		SocketChannel sc = ssc.accept();
		sc.
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while (sc.read(buffer) != -1) {
			buffer.flip();

			outChannel.write(buffer);

			buffer.clear();
		}

		buffer.put("服务端接收数据成功".getBytes());
		buffer.flip();
		sc.write(buffer);

		sc.close();
		outChannel.close();
		ssc.close();
	}

	@Test
	public void serverNIO() throws Exception{
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


	@Test
	public void clientNIO() throws Exception{
		SocketChannel socketChannel =
			SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));

		socketChannel.configureBlocking(false);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		Scanner scanner = new Scanner(System.in);

		while(scanner.hasNext()) {
			String string = scanner.next();
			buffer.put((new Date().toString()+ ":  " + string).getBytes());
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();
		}

		socketChannel.close();
	}

	@Test
	public void test5() throws Exception {
		RandomAccessFile raf1 = new RandomAccessFile("1.txt","rw");


		FileChannel channel1 = raf1.getChannel();

		ByteBuffer buf1 = ByteBuffer.allocate(100);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);

		ByteBuffer[] bufs = {buf1,buf2};
		channel1.read(bufs);

		for (ByteBuffer byteBuffer : bufs) {
			byteBuffer.flip();
		}

		System.out.println(new String(bufs[0].array(),0, bufs[0].limit()));

		System.out.println("------------------");
		System.out.println(new String(bufs[1].array(),0, bufs[1].limit()));

		RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
		FileChannel channel2 = raf2.getChannel();


		channel2.write(bufs);



	}

	@Test
	public void test7() throws CharacterCodingException {
		Charset cs1 = Charset.forName("GBK");

		CharsetEncoder ce = cs1.newEncoder();

		CharsetDecoder cd = cs1.newDecoder();

		CharBuffer cBuf = CharBuffer.allocate(1024);

		cBuf.put("尚硅谷威武！");
		cBuf.flip();

		ByteBuffer bBuf = ce.encode(cBuf);


		for (int i = 0; i < 12; i++) {
			System.out.println(bBuf.get());
		}

		//解码
		bBuf.flip();

		CharBuffer cBuf2 = cd.decode(bBuf);

		System.out.println(cBuf2.toString());

		System.out.println("--------------------------------------");

		Charset cs2 = Charset.forName("UTF-8");


		bBuf.flip();
		CharBuffer cBuf3 = cs2.decode(bBuf);

		System.out.println(cBuf3.toString());

	}

	@Test
	public void test6() {
		Map<String, Charset> map = Charset.availableCharsets();

		Set<Map.Entry<String,Charset>> set = map.entrySet();

		for(Map.Entry<String, Charset> entry : set) {
			System.out.println(entry.getKey() + "=" +entry.getValue());
		}
	}

	@Test
	public void test4() throws Exception {
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

		outChannel.transferFrom(inChannel,0, inChannel.size());

		inChannel.close();
		inChannel.close();
	}

	// 直接缓冲区
	@Test
	public void test3() throws Exception {
		long start = System.currentTimeMillis();

		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

		MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY,0, inChannel.size());
		MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE,0, inChannel.size());

		//直接对直接缓冲区进行数据的读写操作
		byte[] dst = new byte[1024];

		inMappedBuf.get(dst);
		outMappedBuf.put(dst);

		inChannel.close();
		outChannel.close();
		long end = System.currentTimeMillis();

		System.out.println(end - start);
	}


	//非直接缓冲区
	@Test
	public void test2() throws Exception{
		long start = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream("1.jpg");
		FileOutputStream fos = new FileOutputStream("2.jpg");

		FileChannel inChannel = fis.getChannel();
		FileChannel outChanel = fos.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(1024);

		while(inChannel.read(buf) != -1) {
			buf.flip();

			outChanel.write(buf);
			buf.clear();
		}

		outChanel.close();
		inChannel.close();
		fos.close();
		fis.close();

		long end = System.currentTimeMillis();
		System.out.println(end - start);

	}

}
