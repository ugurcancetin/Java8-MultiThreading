package project1;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static final String EOF = "EOF";

    public static void main(String[] args) throws InterruptedException {
        List<String> l = new ArrayList<>();
        Producer p = new Producer(l);
        Consumer c = new Consumer(l);
        Consumer c2 = new Consumer(l);
        Thread producer = new Thread(p);
        Thread consumer = new Thread(c);
        Thread consumer2 = new Thread(c2);
        consumer.setName("Consumer 1");
        consumer2.setName("Consumer 2");
        producer.setName("Producer");
        producer.start();
        consumer.start();
        consumer2.start();
    }
}

class Producer implements Runnable {

    private List<String> buffer;

    public Producer(List<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        String[] nums = {"1", "2", "3", "4", "5"};
        try {
            for (String num : nums) {
                synchronized (buffer){
                    System.out.println("Added number into buffer: " + num + " " + Thread.currentThread().getName());
                    buffer.add(num);
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Adding End of File and exciting...");
        synchronized (buffer){
            buffer.add("EOF");
        }
    }
}

class Consumer implements Runnable {
    private List<String> buffer;

    public Consumer(List<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            synchronized (buffer){
                if(buffer.isEmpty()){
                    continue;
                }
                if(buffer.get(0).equals(App.EOF)){
                    System.out.println("Exiting Consumer...");
                    break;
                } else {
                    System.out.println("Removed from buffer : " + buffer.remove(0) + " --- " + Thread.currentThread().getName());
                }
            }
        }

    }
}