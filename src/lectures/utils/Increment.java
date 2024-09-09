package lectures.utils;

public class Increment {

  private int counter = 0;

  public void increment() {
    counter++;
  }

  public void decrement() {
    counter--;
  }

  public int getCounter() {
    return counter;
  }

  public synchronized void incrementSync() {
    counter++;
  }

  public synchronized void decrementSync() {
    counter--;
  }
}
