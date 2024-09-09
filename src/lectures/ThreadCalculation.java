package lectures;

import java.math.BigInteger;

public class ThreadCalculation extends Thread {

  private final BigInteger base;
  private final BigInteger power;
  private BigInteger result = BigInteger.ONE;
  private boolean isFinished = false;

  public ThreadCalculation(BigInteger base, BigInteger power) {
    this.base = base;
    this.power = power;
  }

  @Override
  public void run() {
    this.result = power(base, power);
    this.isFinished = true;
  }

  private BigInteger power(BigInteger base, BigInteger power) {

    long start = power.longValue();

    for (long i = start; i > 0; i--) {
      result = result.multiply(base);
    }

    return result;
  }

  public BigInteger getResult() {
    return result;
  }

  public boolean isFinished() {
    return isFinished;
  }
}
