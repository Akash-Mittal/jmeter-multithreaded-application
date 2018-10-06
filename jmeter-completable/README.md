# jmeter-multithreaded-application
Java Project that deals with Following parameters (Number of Concurrent Users, Number of Requests(Threads) Per User, Number of  Operations Per Thread)

* K Users Should Run Concurrently.
* Each Kth User Can Spawn N number of New Concurrent Requests.
* Each Nth Request can Spawn M number of Concurrent Operations(Rounds) 
* Total Number of threads will K x M x N

## What is Challenging about this Problem.

* ``` IsCompleted(Kth thread) ```--> Depends Upon ``` IsCompleted(M Threads) ``` --> Depends Upon ``` IsCompleted(N Threads) ```   
* How to Measure the Performance ?
* Decide Maximum number of Threads to make available for thread pool.
* Child Thread Should Be Blocking or Non Blocking.
* If Child Threads are Blocking then there will be large number of threads waiting in the ``` ThreadPool ``` bounded queue.
* For multi threaded app writing Junits is Difficult task So the code design should be kept on best possible basis as independent testable code
* The `Single Responsibility Principle` Aka `S` of `SOLID` is very much applicable here.
* Multiple Running Threads have the ability to corrupt the state of data - This ideally should be reconciled via Integration tests however this POC shall explore other ways also.
* This POC aims at Writing the `Robust Benchmark Reporting`.
* The POC will compare the performance of 3 Asycn Frame works `Java 5 Runnable`, `Java 8 Completable Future`, `Guava Listen-able Future` and `Eclipse's VertX Toolkit`.
