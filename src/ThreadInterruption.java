import java.math.BigInteger;
import lectures.ThreadTermination;

public class ThreadInterruption {

  public static void main(String[] args) {

    Thread thread = new Thread(new ThreadTermination(new BigInteger("20000"), new BigInteger("10000000")));
    thread.start();
    thread.interrupt();
  }
}
