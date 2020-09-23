import java.nio.ByteBuffer;

public class NIOTest {

	public static void main(String[] args) {

		String str = "abcde";

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		System.out.println("----------- allocate ------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());



		buffer.put(str.getBytes());


		System.out.println("---------- put ----------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		buffer.flip();

		System.out.println("---------- flip ----------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		byte[] dst = new byte[buffer.limit()];

		buffer.get(dst);

		//System.out.println(new String(dst, 0, dst.length));

		System.out.println("--------- read ---------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		buffer.rewind();

		buffer.get(dst, 0, 2);
		System.out.println(buffer.position());

		buffer.mark();

		System.out.println(buffer.remaining());

		buffer.get(dst, 2, 3);

		System.out.println(new String(dst));

		buffer.reset();

		System.out.println(buffer.position());

		System.out.println("---------- rewind ----------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		buffer.clear();

		System.out.println("---------- clear ----------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

	}
}
