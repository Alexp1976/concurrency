import lectures.BusinessLogic;
import lectures.Metrics;
import lectures.MetricsPrinter;
import lectures.MinMaxMetrics;

public class MetricsExample {

  public static void main(String[] args) {

    Metrics metrics = new Metrics();
    MinMaxMetrics minMaxMetrics = new MinMaxMetrics();

    BusinessLogic businessLogicThread1 = new BusinessLogic(metrics, minMaxMetrics);
    BusinessLogic businessLogicThread2 = new BusinessLogic(metrics, minMaxMetrics);

    MetricsPrinter metricsPrinter = new MetricsPrinter(metrics, minMaxMetrics);

    businessLogicThread1.start();
    businessLogicThread2.start();
    metricsPrinter.start();
  }
}
