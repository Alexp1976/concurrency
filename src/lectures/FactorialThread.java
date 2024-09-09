package lectures;

import java.math.BigInteger;

public class FactorialThread extends Thread {

  private final long inputNumber;
  private BigInteger result = BigInteger.ZERO;
  private boolean isFinished = false;

  public FactorialThread(long inputNumber) {
    this.inputNumber = inputNumber;
  }

  @Override
  public void run() {
    this.result = factorial(inputNumber);
    this.isFinished = true;
  }

  private BigInteger factorial(long inputNumber) {

    BigInteger tempResult = BigInteger.ONE;

    for (long i = inputNumber; i > 0; i--) {
      tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
    }

    return tempResult;
  }

  public boolean isFinished() {
    return isFinished;
  }

  public BigInteger getResult() {
    return result;
  }
}
