
/**
 * A race condition is achieved when multiple threads work simeltaneously to execute instructions and output
 * a result that is dependent upon a shared value between threads if the shared value is not what was expected for
 * each specific thread producing the wrong output.
 * 
 * In this example there is a static counter which means the counter is shared between instances. Since Thread is extended
 * in this class and we use the run method for thread work we can have multiple instances of this class creaiting multiple threads
 * when we "start()" the instances in Multithreads.java. Each thread will be provided a value and added to the static counter value.
 * 
 * Since each thread working on the program can work simeltenously but have to be scheduled there is a chance that multiple threads
 * will need to access the same resource at the same time. If one thread's result is dependent upon that shared resource being a specific value
 * then a race condition could cause the result to be incorrect.
 * 
 * The value for Counter should increment providing the values 1, 2, 3, 4.
 * The input values for startI are 0, 2, 4, 6.
 * Adding each respective incremented count with input in each respective thread should output 1, 4, 7, 10 which is the sum
 * of each respecitve set of values pertaining to a specific thread. To see this result achieved one thread would be run in a loop so that the count
 * would increment to 4 which never happens in this example. If the race conditions did not occur than the result desired might be obtained.
 * 
 * If you comment out the static counter and use the non-static counter you will see that the counter will never be higher than 1 and the sums
 * will be 1, 3, 5, 7 because each thread will run at the same time and increment the count to only 1 and no higher.
 * 
 * To see what happens when the race condition occurs, use a static counter and set usingStaticCounter to true.
 * 
 * You can also uncomment the statements under "counter++" which will exasperate the race condition issue showing
 * that each thread is in the middle of the process of the program at the same time. Due to this, each of them increment counter
 * before the other has the chance to use count at a value of less than 4 (max count).
 * 
 * 
 * @author Caleb Krauter
 * @verion 1.0.0.1
 * 
 */
public class CurThread extends Thread{

    /** Start index. */
    private int startI = 0;

    // Comment out the static counter to see the program work without race conditions. Also set usingStaticCounter to false.
    /** Shared resource counter. */
    // private static int counter = 0;
    // Comment out the non-static counter to see the program work with race conditions. Also set usingStaticCounter to true.
    /** Non-shared resource counter. */
    // private int counter = 0;
    /** Used to help display information based on the use of a shared resource. */
    private boolean usingStaticCounter = true;

    /**
     * The constructor of the current Thread.
     */
    CurThread() {
        
    }

    /**
     * Setter used to set a start value.
     * @param start
     */
    public void setStart(int start) {
        startI = start;
    }

    /**
     * Returns startI to get the startI passed to the current instance.
     * @return startI
     */
    private int getStartI() {
        return startI;
    }
    /**
     * Run the current thread.
     */
    @Override
    public void run() {
        super.run();
        // 1, 2, 3, 4
        synchronized(CurThread.class) {
            // Timeout can help threads to have more time to possibly use the shared resource before it is changed to the max value of 4.
            // The race condition will more than likely still occur.
            // timeOut();

            counter++;

            // Use this to view the thread associated with each increment to the counter.
            // Seeing when a thread increments the counter should help understanding that the counter
            // was incremented while other processes took place meaning that the counter shown here is likely outdated
            // after the next few lines execute.
            // System.out.println("Thread " + threadId() + " incremented counter to " + counter);
        }
            switch (counter) {
                case 1:
                // This print will not be correct for each thread if we are not using a static value.
                // The output of the program will however be correct.
                if (usingStaticCounter) {
                    System.out.println("Thread " + threadId() + " Should be-> startI (0) + Counter (1) = 1");

                }
                int result1 = getStartI() + counter;
                System.out.println("Thread " + threadId() + " Resulted in startI (" + getStartI() + ") + counter (" + counter + ") = " + result1);
                    break;
                case 2:
                System.out.println("Thread " + threadId() + " Should be-> startI (2) + Counter (2) = 4");
                int result2 = getStartI() + counter;
                System.out.println("Thread " + threadId() + " Resulted in startI (" + getStartI() + ") + counter (" + counter + ") = " + result2);
                    break;
                case 3:
                System.out.println("Thread " + threadId() + " Should be-> startI (4) + Counter (3) = 7");
                int result3 = getStartI() + counter;
                System.out.println("Thread " + threadId() + " Resulted in startI (" + getStartI() + ") + counter (" + counter + ") = " + result3);
    
                    break;
                case 4:
                System.out.println("Thread " + threadId() + " Should be-> startI (6) + Counter (4) = 10");
                int result4 = getStartI() + counter;
                System.out.println("Thread " + threadId() + " Resulted in startI (" + getStartI() + ") + counter (" + counter + ") = " + result4);
    
                    break;
                default:
                    break;
            }

    }

    /**
     * A helper method to provide more time to the individual threads.
     * This allows other threads enough time to possibly use the counter
     * before it increments to the max value of 4.
     */
    private void timeOut() {
        try {
            CurThread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
