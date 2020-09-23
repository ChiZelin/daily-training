public class TestProducerAndConsumer {
	public static void main(String[] args) {
		Store store = new Store();

		Producer producer = new Producer(store);
		Consumer consumer = new Consumer(store);

		new Thread(producer,"生产者A").start();
		new Thread(producer,"生产者B").start();
		new Thread(consumer, "消费者A").start();
		new Thread(consumer, "消费者B").start();

	}
}


class Store {
	private int product = 1;

	public synchronized void put() {

		if(product >= 1) {
			System.out.println(
				Thread.currentThread().getName() + "来了，但产品已满！");

			try{
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		System.out.println(
			Thread.currentThread().getName() + "操作后，库存为: "
				+ ++product);
		this.notifyAll();

	}







	public synchronized void get(){

		while(product <= 0) {
			System.out.println(
				Thread.currentThread().getName()
					+ "来了，但缺货！");

			try{
				this.wait();
			}catch (InterruptedException e){
			}
		}

		System.out.println(
			Thread.currentThread().getName()
				+ "操作后，库存为: " + --product);
		this.notifyAll();

	}
}


class Consumer implements Runnable{

	private Store store;

	public Consumer(Store store){
		this.store = store;
	}

	public void run() {
		store.get();
	}
}


class Producer implements Runnable {

	private Store store;

	public Producer(Store store) {
		this.store = store;
	}

	public void run() {
		store.put();
	}
}
