import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

public class NIOClientUDP {
	public static void main(String[] args) throws IOException {
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
}
