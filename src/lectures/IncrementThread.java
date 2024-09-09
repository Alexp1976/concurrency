package lectures;

import lectures.utils.Increment;

public class IncrementThread extends Thread {

  private final Increment increment;
  private final boolean sync;

  public IncrementThread(Increment increment, boolean sync) {
    this.increment = increment;
    this.sync = sync;
  }

  @Override
  public void run() {
    if (sync) {
      for (int i = 0; i < 100000; i++) {
        increment.incrementSync();
      }
    } else {
      for (int i = 0; i < 100000; i++) {
        increment.increment();
      }
    }
  }
}
