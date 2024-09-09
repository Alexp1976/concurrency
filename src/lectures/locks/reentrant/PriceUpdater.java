package lectures.locks.reentrant;

import java.util.Random;

public class PriceUpdater extends Thread {

  private PriceContainer priceContainer;

  private Random random = new Random();

  public PriceUpdater(PriceContainer priceContainer) {
    this.priceContainer = priceContainer;
  }

  @Override
  public void run() {
    while (true) {
      priceContainer.getLockObject().lock();

      try {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        priceContainer.setBitcoinPrice(random.nextInt(2000));
        priceContainer.setEtherPrice(random.nextInt(2000));
        priceContainer.setLitecoinPrice(random.nextInt(500));
        priceContainer.setBitcoinCashPrice(random.nextInt(5000));
        priceContainer.setRipplePrice(random.nextDouble());
      } finally {
        priceContainer.getLockObject().unlock();
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {}
    }
  }
}
