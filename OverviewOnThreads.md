
# High Level Overview to Threads

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

