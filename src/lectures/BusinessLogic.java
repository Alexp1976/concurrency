package lectures;

import java.util.Random;

public class BusinessLogic extends Thread {

  private Metrics metrics;
  private MinMaxMetrics minMaxMetrics;

  private Random random = new Random();

  public BusinessLogic(Metrics metrics, MinMaxMetrics minMaxMetrics) {
    this.metrics = metrics;
    this.minMaxMetrics = minMaxMetrics;
  }

  @Override
  public void run() {

    while (true) {
      long start = System.currentTimeMillis();
      try {
        Thread.sleep(random.nextInt(10));
      } catch (InterruptedException e) {
      }
      long end = System.currentTimeMillis();
      metrics.addSample(end - start);
      minMaxMetrics.addSample(end - start);
    }
  }
}
