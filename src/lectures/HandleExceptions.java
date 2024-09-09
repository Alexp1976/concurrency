package lectures;

public class HandleExceptions implements ThreadsInterface {
  @Override
  public void run() throws InterruptedException {

    Thread thread = new Thread(() -> {
      int number = 10 / 0;
    });

    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread t, Throwable e) {
        System.out.println("A critical error occurred in thread " + thread.getName() +
            " the error is " + e.getMessage());
      }
    });
    thread.start();
  }
}
