package lectures.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier {

  private final int numberOfWorkers;
  private final Semaphore semaphore = new Semaphore(0);
  int counter = 0;
  private final Lock lock= new ReentrantLock();

  public Barrier(int numberOfWorkers) {
    this.numberOfWorkers = numberOfWorkers;
  }

  public void waitForOthers() throws InterruptedException {

    lock.lock();

    boolean isLastWorker = false;

    try {
      counter++;
      if (counter == numberOfWorkers) {
        isLastWorker = true;
      }
    } finally {
      lock.unlock();
    }

    if (isLastWorker) {
      semaphore.release(numberOfWorkers - 1);
    } else {
      semaphore.acquire();
    }
  }

}
