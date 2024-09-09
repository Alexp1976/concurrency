package lectures;

import lectures.utils.Vault;

public class AscendingHackerThread extends HackerThread {
  public AscendingHackerThread(Vault vault) {
    super(vault);
  }

  @Override
  public void run() {
    for (int guess = 0; guess < MAX_PASSWORD; guess++) {
      System.out.println(this.getName() + " is trying to guess password " + guess);
      if (vault.isCorrectPassword(guess)) {
        System.out.println(this.getName() + " guessed the password " + guess);
        System.exit(0);
      }
    }
  }
}
