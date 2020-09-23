//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class TestPACCondition {
//		public static void main(String[] args) {
//			Store clerk = new Store();
//
//			Productor productor = new Productor(clerk);
//			Consumer cusumer = new Consumer(clerk);
//
//			new Thread(cusumer, "消费者A").start();
//			new Thread(cusumer, "消费者B").start();
//
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			new Thread(productor,"生产者A").start();
//			new Thread(productor,"生产者B").start();
//		}
//	}
//
//
//	class Store {
//		private int product = 0;
//
//		private Lock lock = new ReentrantLock();
//
//		Condition condition = lock.newCondition();
//
//
//		public  void put() {
//			lock.lock();
//
//			try{
//				while(product >= 1) {
//					System.out.println("产品已满！");
//
//					try{
//						condition.await();
//					} catch (InterruptedException e) {
//
//					}
//				}
//
//				System.out.println(Thread.currentThread().getName() + ":" + ++product);
//				condition.signalAll();
//			}finally {
//				lock.unlock();
//			}
//
//
//		}
//
//		public void get(){
//			lock.lock();
//
//			try {
//				while (product <= 0) {
//					System.out.println("缺货！");
//
//					try{
//						condition.await();
//					}catch (InterruptedException e){
//					}
//				}
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(Thread.currentThread().getName() + ":" + --product);
//				condition.signalAll();
//			}finally {
//				lock.unlock();
//			}
//
//
//		}
//	}
//
//
//	class Consumer implements Runnable{
//
//		private Store clerk;
//
//		public Consumer(Store clerk){
//			this.clerk = clerk;
//		}
//
//		public void run() {
//			for (int i = 0; i < 1; i++) {
//				clerk.get();
//			}
//		}
//	}
//
//
//	class Productor implements Runnable {
//
//		private Store clerk;
//
//		public Productor(Store clerk) {
//			this.clerk = clerk;
//		}
//
//		public void run() {
//			for (int i = 0; i < 1; i++) {
//				clerk.put();
//			}
//		}
//	}
