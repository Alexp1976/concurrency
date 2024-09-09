package lectures;

public class MinMaxMetrics {

  private volatile long min;
  private volatile long max;

  public MinMaxMetrics() {
    min = Long.MAX_VALUE;
    max = Long.MIN_VALUE;
  }

  public synchronized void addSample(long newSample) {
    min = Math.min(newSample, min);
    max = Math.max(newSample, max);
  }

  public long getMax() {
    return max;
  }

  public long getMin() {
    return min;
  }

}
