# Concurrency In Java

The purpose of this repository is providing a solid and hands-on-experience to newbies in this subject. Concurrency in Java is one of the most critical point for a career turnover. There are lots of tricky point and even under normal conditions, it can become a quite difficult to work with this subject in programming life. Therefore, you can expect from this repository to provide you a basic and advanced coverage of this matter. 

There are lot's of things to learn on a theoretical way. I would suggest you all to go parellel with practicing with coding and playing araound, also keep learning theoretical knowledge.

There are lots of sources used to build this repository. Mainly articles, books and online courses from well experienced people in this industry. I am thanking to all of them for sharing their knowledge with the community.

Wish you the best and happy coding!!

## Learn With Questions 

### 1)  What is Thread in Java? 

The thread is an independent path of execution. It's way to take advantage of multiple CPU available in a machine. By employing multiple threads you can speed up CPU bound task. For example, if one thread takes 100 milliseconds to do a job, you can use 10 thread to reduce that task into 10 milliseconds. Java provides excellent support for multithreading at the language level, and it's also one of the strong selling points.

### 2)  What is the difference between Thread and Process in Java?

The thread is a subset of Process, in other words, one process can contain multiple threads. Two process runs on different memory space, but all threads share same memory space. Don't confuse this with stack memory, which is different for the different thread and used to store local data to that thread. For more detail see the answer.

### 3)  How do you implement Thread in Java?

At the language level, there are two ways to implement Thread in Java. An instance of java.lang.Thread represent a thread but it needs a task to execute, which is an instance of interface java.lang.Runnable. Since Thread class itself implement Runnable, you can override run() method either by extending Thread class or just implementing Runnable interface.

### 4)  When to use Runnable vs Thread in Java?

This is a follow-up of previous multi-threading interview question. As we know we can implement thread either by extending Thread class or implementing Runnable interface, the question arise, which one is better and when to use one? This question will be easy to answer if you know that Java programming language doesn't support multiple inheritances of class, but it allows you to implement multiple interfaces. Which means, it's better to implement Runnable then extends Thread if you also want to extend another class e.g. Canvas or CommandListener.


### 6)  What is the difference between start() and run() method of Thread class?

One of trick Java question from early days, but still good enough to differentiate between shallow understanding of Java threading model start() method is used to start newly created thread, while start() internally calls run() method, there is difference calling run() method directly. When you invoke run() as normal method, its called in the same thread, no new thread is started, which is the case when you call start() method. Read this answer for much more detailed discussion.
