import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static final String EOF = "EOF";

    public static void main(String[] args) throws InterruptedException {
        List<String> l = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Producer p = new Producer(l, lock);
        Consumer c = new Consumer(l, lock);
        Consumer c2 = new Consumer(l, lock);
        Thread producer = new Thread(p);
        Thread consumer = new Thread(c);
        Thread consumer2 = new Thread(c2);

        executorService.execute(producer);
        executorService.execute(consumer);
        executorService.execute(consumer2);

        executorService.shutdown();
    }
}

class Producer implements Runnable {

    private List<String> buffer;
    ReentrantLock lock;

    public Producer(List<String> buffer, ReentrantLock lock) {
        this.buffer = buffer;
        this.lock = lock;
    }

    @Override
    public void run() {
        String[] nums = {"1", "2", "3", "4", "5"};
        for (String num : nums) {

            System.out.println("Added number into buffer: " + num + " " + Thread.currentThread().getName());
            lock.lock();
            try {
                buffer.add(num);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        System.out.println("Adding End of File and exciting...");
        try {
            lock.lock();
            buffer.add("EOF");
        } finally {
            lock.unlock();
        }
    }
}

class Consumer implements Runnable {
    private List<String> buffer;
    ReentrantLock lock;

    public Consumer(List<String> buffer, ReentrantLock lock) {
        this.buffer = buffer;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            if (lock.tryLock()) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.get(0).equals(App.EOF)) {
                        System.out.println("Exiting Consumer...");
                        break;
                    } else {
                        System.out.println("Removed from buffer : " + buffer.remove(0) + " --- " + Thread.currentThread().getName());
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
