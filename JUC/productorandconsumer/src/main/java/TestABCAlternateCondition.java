import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestABCAlternateCondition {
	public static void main(String[] args) {

		Counter counter = new Counter();

		new Thread(new ThreadA(counter), "A").start();
		new Thread(new ThreadB(counter), "B").start();
		new Thread(new ThreadC(counter), "C").start();

	}
}


class Counter {
	private int number = 1;
	Lock lock = new ReentrantLock();

	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();

	public void prA(){

		lock.lock();

		try {
			while(number != 1) {
				try {
					condition1.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println(Thread.currentThread().getName());
			number = 2;
			condition2.signal();
		}finally {
			lock.unlock();
		}

	}

	public void prB(){
		lock.lock();

		try {
			while(number != 2) {
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println(Thread.currentThread().getName());
			number = 3;
			condition3.signal();
		}finally {
			lock.unlock();
		}
	}

	public void prC(){
		lock.lock();

		try {
			while(number != 3) {
				try {
					condition3.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println(Thread.currentThread().getName());
			System.out.println("----------------------");
			number = 1;
			condition1.signal();
		}finally {
			lock.unlock();
		}
	}


}

class ThreadA implements Runnable {

	Counter counter;

	public ThreadA(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 20; i++){
			counter.prA();
		}
	}
}

class ThreadB implements Runnable {

	Counter counter;

	public ThreadB(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 20; i++){
			counter.prB();
		}
	}
}

class ThreadC implements Runnable {

	Counter counter;

	public ThreadC(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 20; i++){
			counter.prC();
		}

	}
}