package lectures.semaphore;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    int numberOfThreads = 3;

    List<Thread> threads = new ArrayList<>();

    Barrier barrier = new Barrier(3);

    for (int i = 0; i < numberOfThreads; i++) {
      threads.add(new Thread(new CoordinatedWorkRunner(barrier)));
    }

    for (Thread thread : threads) {
      thread.start();
    }
  }
}
