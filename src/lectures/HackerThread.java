package lectures;

import lectures.utils.Vault;

public abstract class HackerThread extends Thread {

  public static final int MAX_PASSWORD = 9999;
  protected Vault vault;

  public HackerThread(Vault vault) {
    this.vault = vault;
    this.setName(this.getClass().getSimpleName());
    this.setPriority(Thread.MAX_PRIORITY);
  }

  @Override
  public synchronized void start() {
    System.out.println("Starting thread " + this.getName());
    super.start();
  }
}
