package lectures;

public class ThreadInheritance extends Thread implements ThreadsInterface {

  @Override
  public void run() {
    System.out.println("Hello from " + this.getName());
  }
}
