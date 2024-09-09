package lectures.utils;

public class PoliceThread extends Thread {

  @Override
  public void run() {
    for (int i = 10; i > 0; i--) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ignored) {
      }
      System.out.println(i);
    }
    System.out.println("Game over for you hackers");
    System.exit(0);
  }
}
