import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

public class NIOClient {
	public static void main(String[] args) throws IOException {
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
}
