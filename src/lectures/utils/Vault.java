package lectures.utils;

public class Vault {

  private int password;

  public Vault(int password) {
    this.password = password;
  }

  public boolean isCorrectPassword(int guess) {

    try {
      Thread.sleep(5);
    } catch (InterruptedException ignored) {
    }
    return this.password == guess;
  }
}
