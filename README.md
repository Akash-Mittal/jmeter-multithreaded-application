# jmeter-multithreaded-application
Java Project that deals with Following parameters (Number of Concurrent Users, Number of Requests(Threads) Per User, Number of  Operations Per Thread)

* K Users Should Run Concurrently.
* Each Kth User Can Spawn N number of New Concurrent Requests.
* Each Nth Request can Spawn M number of Concurrent Operations(Rounds) 
* Total Number of threads will K x M x N

## What is Challenging about this Problem.

* ``` IsCompleted(Kth thread) ```--> Depends Upon ``` IsCompleted(M Threads) ``` --> Depends Upon ``` IsCompleted(N Threads) ```   
* How to Measure the Performance ?
* Decide Maximumm number of Threads to make available for thread pool.
* Child Thread Should Be Blocking or Non Blocking.
* If Child Threads are Blocking then there will be large number of threads waiting in the ``` ThreadPool ``` bounded queue.


