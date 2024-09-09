package lectures;

import lectures.utils.Increment;

public class DecrementThread extends Thread {

  private final Increment increment;
  private final boolean sync;

  public DecrementThread(Increment increment, boolean sync) {
    this.increment = increment;
    this.sync = sync;
  }

  @Override
  public void run() {
    if (sync) {
      for (int i = 0; i < 100000; i++) {
        increment.decrementSync();
      }
    } else {
      for (int i = 0; i < 100000; i++) {
        increment.decrement();
      }
    }
  }
}
