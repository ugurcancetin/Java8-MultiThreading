import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static final String EOF = "EOF";

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(6);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Producer p = new Producer(arrayBlockingQueue);
        Consumer c = new Consumer(arrayBlockingQueue);
        Consumer c2 = new Consumer(arrayBlockingQueue);
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

    private ArrayBlockingQueue arrayBlockingQueue;
    ReentrantLock lock;

    public Producer(ArrayBlockingQueue arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
        String[] nums = {"1", "2", "3", "4", "5"};
        for (String num : nums) {
            try {
                System.out.println("Added number into buffer: " + num + " " + Thread.currentThread().getName());
                arrayBlockingQueue.put(num);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Adding End of File and exciting...");
        try {
            arrayBlockingQueue.put("EOF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Consumer implements Runnable {
    private ArrayBlockingQueue arrayBlockingQueue;

    public Consumer(ArrayBlockingQueue arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (arrayBlockingQueue) {
                if (arrayBlockingQueue.isEmpty()) {
                    //synchronized because of the fact that NPE can be caused in this point.
                    continue;
                }
                if (arrayBlockingQueue.peek().equals(App.EOF)) {
                    System.out.println("Exiting Consumer...");
                    break;
                } else {
                    try {
                        System.out.println("Removed from buffer : " + arrayBlockingQueue.take() + " --- " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}