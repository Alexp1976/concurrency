package lectures.basic;

import lectures.ThreadsInterface;

public class BasicThread implements ThreadsInterface {

  @Override
  public void run() throws InterruptedException {
    Thread thread = new Thread(new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("We are now in thread " + Thread.currentThread().getName());
        System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
      }
    }));
    thread.setName("New Worker Thread");
    thread.setPriority(Thread.MAX_PRIORITY);
    System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
    thread.start();
    System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");
    Thread.sleep(1000);
  }
}
