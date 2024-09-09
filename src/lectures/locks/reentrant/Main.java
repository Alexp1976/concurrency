package lectures.locks.reentrant;

public class Main extends Thread {

  public static void main(String[] args) {

    PriceContainer priceContainer = new PriceContainer();
    PriceUpdater priceUpdater = new PriceUpdater(priceContainer);

    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          if (priceContainer.getLockObject().tryLock()) {
            try {
              System.out.println("----------------------------------------------");
              System.out.println("BC: " + priceContainer.getBitcoinPrice());
              System.out.println("EP: " + priceContainer.getEtherPrice());
              System.out.println("LP: " + priceContainer.getLitecoinPrice());
              System.out.println("BP: " + priceContainer.getBitcoinCashPrice());
              System.out.println("RP: " + priceContainer.getRipplePrice());
            } finally {
              priceContainer.getLockObject().unlock();
            }
          }
        }
      }
    });

    priceUpdater.start();
    thread.start();
  }
}
