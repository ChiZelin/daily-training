import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class NIOServerUDP {
	public static void main(String[] args) throws IOException {
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
}
