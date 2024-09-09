package lectures.locks.reentrantRW;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductReviewMain {

  private static final int NUM_OF_PRODUCTS = 5;

  public static void main(String[] args) throws InterruptedException {

    ProductReviewService service = new ProductReviewService();
    Random random = new Random();

    for (int i = 0; i < NUM_OF_PRODUCTS; i++) {
      service.addProduct(i);
    }

    Thread writer = new Thread(() -> {
      int count = 1;
      while (true) {
        int productId = random.nextInt(NUM_OF_PRODUCTS);
        service.addProductReview(productId, "Review " + count++);
        service.removeProduct(random.nextInt(NUM_OF_PRODUCTS));
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
        }
      }
    });

    writer.setDaemon(true);
    writer.start();

    List<Thread> readers = new ArrayList<>();

    for (int readerIndex = 0; readerIndex < 7; readerIndex++) {
      Thread reader = new Thread(() -> {
        while (true) {
          System.out.println("-----------------------------------------------------");
          System.out.println("All products with review: " + service.getAllProductIdsWithReviews());
          System.out.println("All product review: " + service.getAllProductReviews(random.nextInt(NUM_OF_PRODUCTS)));
          System.out.println("Latest review: " + service.getLatestReview(random.nextInt(NUM_OF_PRODUCTS)));
          System.out.println("-----------------------------------------------------");
        }
      });
      reader.setDaemon(true);
      readers.add(reader);
    }

    for (Thread reader : readers) {
      reader.start();
    }

    for (Thread reader : readers) {
      reader.join();
    }
  }
}
