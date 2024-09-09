import lectures.DecrementThread;
import lectures.IncrementThread;
import lectures.utils.Increment;

public class Synchronization extends Thread {

  public static void main(String[] args) throws InterruptedException {

    Increment increment = new Increment();
    IncrementThread incrementThread = new IncrementThread(increment, false);
    DecrementThread decrementThread = new DecrementThread(increment, false);

    incrementThread.start();
    decrementThread.start();

    incrementThread.join();
    decrementThread.join();

    System.out.println("Counter no synchronized: " + increment.getCounter());

  }
}
