package lectures;

public class MetricsPrinter extends Thread {

  private final Metrics metrics;
  private final MinMaxMetrics minMaxMetrics;

  public MetricsPrinter(Metrics metrics, MinMaxMetrics minMaxMetrics) {
    this.metrics = metrics;
    this.minMaxMetrics = minMaxMetrics;
  }

  @Override
  public void run() {

    while (true) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }

      double currentAverage = metrics.getAverage();
      long maxSample = minMaxMetrics.getMax();
      long minSample = minMaxMetrics.getMin();

      System.out.println("Current average is " + currentAverage);
      System.out.println("Max metric is " + maxSample);
      System.out.println("Min metric is " + minSample);
    }
  }
}
