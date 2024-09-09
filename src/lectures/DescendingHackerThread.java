package lectures;

import lectures.utils.Vault;

public class DescendingHackerThread extends HackerThread {
  public DescendingHackerThread(Vault vault) {
    super(vault);
  }

  @Override
  public void run() {
    for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
      System.out.println(this.getName() + " is trying to guess password " + guess);
      if (vault.isCorrectPassword(guess)) {
        System.out.println(this.getName() + " guessed the password " + guess);
        System.exit(0);
      }
    }
  }
}
