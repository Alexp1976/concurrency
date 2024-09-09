import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lectures.ThreadCalculation;

public class MultiThreadCalculation {

  public static void main(String[] args) throws InterruptedException {

    BigInteger result = BigInteger.ZERO;

    List<ThreadCalculation> threads = new ArrayList<>();

    threads.add(new ThreadCalculation(new BigInteger("3"), new BigInteger("2")));
    threads.add(new ThreadCalculation(new BigInteger("3"), new BigInteger("10")));

    for (Thread t : threads) {
      t.start();
    }

    for (Thread t : threads) {
      t.join();
    }

    for (ThreadCalculation t : threads) {
      result = result.add(t.getResult());
    }

    System.out.println("result: " + result);
  }
}
