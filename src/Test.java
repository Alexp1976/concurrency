public class Test {

  public static void main(String[] args) {

    Thread thread = new Thread(new TaskThread1());
    thread.start();

    Thread thread1 = new Thread(new Task2());
    thread1.start();
  }

  public static class TaskThread1 extends Thread {

    @Override
    public void run() {
      System.out.println("Hello from new thread");
    }
  }

  public static class Task2 implements Runnable {

    @Override
    public void run() {
      System.out.println("Hello from new thread");
    }
  }
}
