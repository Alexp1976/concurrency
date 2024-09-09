package lectures.semaphore;

public class CoordinatedWorkRunner implements Runnable {

  private final Barrier barrier;

  public CoordinatedWorkRunner(Barrier barrier) {
    this.barrier = barrier;
  }

  @Override
  public void run() {
    try {
      task();
    } catch (InterruptedException ignored) {}
  }

  private void task() throws InterruptedException {

    System.out.println(Thread.currentThread().getName() + " part 1 of the work is finished");

    barrier.waitForOthers();

    System.out.println(Thread.currentThread().getName() + " part 2 of the work is finished");
  }
}
