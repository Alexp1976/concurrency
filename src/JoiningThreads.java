import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lectures.FactorialThread;

public class JoiningThreads {

  public static void main(String[] args) throws InterruptedException {

    List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

    List<FactorialThread> threads = new ArrayList<>();

    for (long inputNumber : inputNumbers) {
      threads.add(new FactorialThread(inputNumber));
    }

    for (Thread thread : threads) {
      thread.setDaemon(true);
      thread.start();
    }

    for (FactorialThread thread : threads) {
      thread.join(2000);
    }

    for (int i = 0; i < inputNumbers.size(); i++) {
      FactorialThread factorialThread = threads.get(i);
      if (factorialThread.isFinished()) {
        System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
      } else {
        System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
      }
    }
  }
}
