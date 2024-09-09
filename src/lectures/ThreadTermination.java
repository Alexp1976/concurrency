package lectures;

import java.math.BigInteger;

public class ThreadTermination implements Runnable {

  private BigInteger base;
  private BigInteger power;

  public ThreadTermination(BigInteger base, BigInteger power) {
    this.base = base;
    this.power = power;
  }
  @Override
  public void run() {
    System.out.println(base + "^" + power + " = " + pow(base, power));
  }

  private BigInteger pow(BigInteger base, BigInteger power) {
    BigInteger result = BigInteger.ONE;

    // checking if the thread is interrupted
    for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
      if (HackerThread.currentThread().isInterrupted()) {
        System.out.println("Prematurely interrupted computation");
        return BigInteger.ZERO;
      }
      result = result.multiply(base);
    }

    return result;
  }
}
