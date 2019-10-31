# Concurrency In Java

The purpose of this repository is providing a solid and hands-on-experience to newbies in this subject. Concurrency in Java is one of the most critical point for a career turnover. There are lots of tricky point and even under normal conditions, it can become a quite difficult to work with this subject in programming life. Therefore, you can expect from this repository to provide you a basic and advanced coverage of this matter. 

There are lot's of things to learn on a theoretical way. I would suggest you all to go parellel with practicing with coding and playing araound, also keep learning theoretical knowledge.

There are lots of sources used to build this repository. Mainly articles, books and online courses from well experienced people in this industry. I am thanking to all of them for sharing their knowledge with the community.

Wish you the best and happy coding!!

## High Level Overview to Threads

* Thread in Java represent an independent path of execution.

* During its life time thread remains on various Thread states like NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING which describe what thread is doing. NEW means thread is just created but not yet stated, RUNNABLE means thread is started but waiting for CPU to be assigned by thread scheduler. BLOCKED, WAITING and TIMED_WAITING means thread is not doing anything instead its been blocked and waiting for IO to finished, class or object lock, or any other thread etc.

* Every Java threads has priority and name. You can set priority and assign meaningful name while creating object of java.lang.Thread class in Java. its recommend practice to give every thread a meaningful name while creating it , it helps later when you debug your Java program or take thread dump for analysis. Otherwise Java will give your Thread default name like "Thread-number" if Thread is created using java.lang.Thread or "pool-number-thread-number" if Thread is created using ThreadFactory. In Java higher priority thread get preference in Execution over lower priority thread. you can check priority by using method like getProiroty() from thread class.

* Creation of thread is a time-consuming job so having a Thread pool for performing task concurrently is modern day requirement of performance and scalability. Java 5 provides Executor framework which encapsulate task of creating and managing thread from application code. Consider using Thread pool if your application requires to handle load. In web and application server manages this thread pool because each request is processed in its own thread.

* Thread.sleep() method is used to pause thread for specified duration. It is an overloaded method defined in java.lang.Thread class. On the other hand Thread.join() is used to wait for another thread to complete its task before running and yield() method is used to relinquish CPU so that other thread can acquire it.

* wait() and notify() methods are used to communicate between two threads i.e. for inter thread communication in Java. Always check condition of  wait() method in loop and call them from synchronized context. wait() is a method which is defined in object class, and puts the current thread on hold and also releases the monitor (lock) held by this thread,  while notify() and notifyAll() methods notifies all thread waiting on that monitor. There is no guarantee which thread will picked up by thread scheduler and given CPU to execute because of of notification. To learn more about how to use wait, notify and notifyAll method to achieve inter-thread communication solving producer consumer problem in Java using wait and notify method is quite common way.

* Thread scheduling is done by Thread Scheduler which is platform dependent and stays inside JVM. There is no known way to control thread scheduler from Java and many of thread related decision is done by scheduler like if there are many threads is waiting then which thread will be awarded CPU.

* Thread.isActive() method is used to check whether a thread is active or not. A thread is said to be active until it has not finished either by returning from run() method normally or due to any exception. Thread.holdsLock() method is used to check if a thread holds a lock or not.

* Every thread in Java has its own stack, which is used to store local variables and method calls. Size of this stack can be controlled using -XX:ThreadStackSize JVM option e.g. -XX:ThreadStackSize=512.

* Java 5 introduced another way to define task for threads in Java by using Callable interface. It's similar to Runnable interface but provides some more capability e.g. it can return result of task execution, which was not possible in Runnable, because return type of run() method was void. Like its predecessor it define a call() method which can return Future object, you can call get() method on this object to get the result of task execution.

* Java provides interrupt() method to interrupt a thread in Java. You can interrupt a running thread, waiting thread or sleep thread. This is the control Java provides to prevent a blocked or hanged thread. Once you interrupt a thread, it will also throw InterruptedException, which is a checked exception to ensure that your code should take handle interrupts.



## Learn With Questions 

### What is Thread in Java? 

The thread is an independent path of execution. It's way to take advantage of multiple CPU available in a machine. By employing multiple threads you can speed up CPU bound task. For example, if one thread takes 100 milliseconds to do a job, you can use 10 thread to reduce that task into 10 milliseconds. Java provides excellent support for multithreading at the language level, and it's also one of the strong selling points.

### What is the difference between Thread and Process in Java?

The thread is a subset of Process, in other words, one process can contain multiple threads. Two process runs on different memory space, but all threads share same memory space. Don't confuse this with stack memory, which is different for the different thread and used to store local data to that thread. For more detail see the answer.

### How do you implement Thread in Java?

At the language level, there are two ways to implement Thread in Java. An instance of java.lang.Thread represent a thread but it needs a task to execute, which is an instance of interface java.lang.Runnable. Since Thread class itself implement Runnable, you can override run() method either by extending Thread class or just implementing Runnable interface.

### When to use Runnable vs Thread in Java?

This is a follow-up of previous multi-threading interview question. As we know we can implement thread either by extending Thread class or implementing Runnable interface, the question arise, which one is better and when to use one? This question will be easy to answer if you know that Java programming language doesn't support multiple inheritances of class, but it allows you to implement multiple interfaces. Which means, it's better to implement Runnable then extends Thread if you also want to extend another class e.g. Canvas or CommandListener.


### What is the difference between start() and run() method of Thread class?

One of trick Java question from early days, but still good enough to differentiate between shallow understanding of Java threading model start() method is used to start newly created thread, while start() internally calls run() method, there is difference calling run() method directly. When you invoke run() as normal method, its called in the same thread, no new thread is started, which is the case when you call start() method. Read this answer for much more detailed discussion.

### What is the difference between Runnable and Callable in Java?

Both Runnable and Callable represent task which is intended to be executed in a separate thread. Runnable is there from JDK 1.0 while Callable was added on JDK 1.5. Main difference between these two is that Callable's call() method can return value and throw Exception, which was not possible with Runnable's run() method. Callable return Future object, which can hold the result of computation.

### What is the difference between CyclicBarrier and CountDownLatch in Java?

Though both CyclicBarrier and CountDownLatch wait for number of threads on one or more events, the main difference between them is that you can not re-use CountDownLatch once count reaches to zero, but you can reuse same CyclicBarrier even after barrier is broken.  See this answer for few more points and sample code example.


### What is Java Memory model?

Java Memory model is set of rules and guidelines which allows Java programs to behave deterministically across multiple memory architecture, CPU, and operating system. It's particularly important in case of multi-threading. Java Memory Model provides some guarantee on which changes made by one thread should be visible to others, one of them is happens-before relationship. This relationship defines several rules which allows programmers to anticipate and reason behaviour of concurrent Java programs. For example, happens-before relationship guarantees :
  * Each action in a thread happens-before every action in that thread that comes later in the program order, this is known as program order rule.
  * An unlock on a monitor lock happens-before every subsequent lock on that same monitor lock, also known as Monitor lock rule.
  * A write to a volatile field happens-before every subsequent read of that same field, known as Volatile variable rule.
  * A call to Thread.start on a thread happens-before any other thread detects that thread has terminated, either by successfully return from Thread.join() or by Thread.isAlive() returning false, also known as Thread start rule.
  * A thread calling interrupt on another thread happens-before the interrupted thread detects the interrupt( either by having  InterruptedException thrown, or invoking isInterrupted or interrupted), popularly known as Thread Interruption rule.
  * The end of a constructor for an object happens-before the start of the finalizer for that object, known as Finalizer rule.
If A happens-before B, and B happens-before C, then A happens-before C, which means happens-before guarantees Transitivity.
