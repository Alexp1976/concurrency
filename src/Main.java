import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lectures.AscendingHackerThread;
import lectures.DescendingHackerThread;
import lectures.HackerThread;
import lectures.utils.PoliceThread;
import lectures.utils.Vault;

public class Main {
  public static void main(String[] args) throws InterruptedException {

//    ThreadsInterface thread1 = new BasicThread();
//    thread1.run();
//
//    ThreadsInterface thread2 = new HandleExceptions();
//    thread2.run();
//
//    ThreadInheritance thread3 = new ThreadInheritance();
//    thread3.start();

    Random random = new Random();
    int password = random.nextInt(HackerThread.MAX_PASSWORD);
    Vault vault = new Vault(password);

    System.out.println("Vault created with password " + password);

    List<Thread> threads = new ArrayList<>();

    threads.add(new AscendingHackerThread(vault));
    threads.add(new DescendingHackerThread(vault));
    threads.add(new PoliceThread());

    for (Thread thread : threads) {
      thread.start();
    }
  }
}
