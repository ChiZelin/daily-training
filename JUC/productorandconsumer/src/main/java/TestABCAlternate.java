//public class TestABCAlternate {
//	public static void main(String[] args) {
//
//		Counter counter = new Counter();
//
//		new Thread(new ThreadA(counter), "A").start();
//		new Thread(new ThreadB(counter), "B").start();
//		new Thread(new ThreadC(counter), "C").start();
//
//	}
//}
//
//
//class Counter {
//	private int number = 1;
//
//	public synchronized void prA(){
//		while(number != 1) {
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		System.out.println(Thread.currentThread().getName());
//		number = 2;
//		this.notifyAll();
//	}
//
//	public synchronized void prB(){
//		while(number != 2) {
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		System.out.println(Thread.currentThread().getName());
//		number = 3;
//		this.notifyAll();
//	}
//
//	public synchronized void prC(){
//		while(number != 3) {
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		System.out.println(Thread.currentThread().getName());
//		System.out.println("-------------------");
//		number = 1;
//		this.notifyAll();
//	}
//
//
//}
//
//class ThreadA implements Runnable {
//
//	Counter counter;
//
//	public ThreadA(Counter counter) {
//		this.counter = counter;
//	}
//
//	public void run() {
//		for (int i = 0; i < 20; i++){
//			counter.prA();
//		}
//	}
//}
//
//class ThreadB implements Runnable {
//
//	Counter counter;
//
//	public ThreadB(Counter counter) {
//		this.counter = counter;
//	}
//
//	public void run() {
//		for (int i = 0; i < 20; i++){
//			counter.prB();
//		}
//	}
//}
//
//class ThreadC implements Runnable {
//
//	Counter counter;
//
//	public ThreadC(Counter counter) {
//		this.counter = counter;
//	}
//
//	public void run() {
//		for (int i = 0; i < 20; i++){
//			counter.prC();
//		}
//
//	}
//}