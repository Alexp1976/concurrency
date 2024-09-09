package lectures.locks.reentrantRW;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProductReviewService {

  private final HashMap<Integer, List<String>> productIdToReviews;
  private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private final Lock readLock = readWriteLock.readLock();
  private final Lock writeLock = readWriteLock.writeLock();

  public ProductReviewService() {
    this.productIdToReviews = new HashMap<>();
  }

  public void addProduct(int productId) {
    Lock lock = getLockForAddProduct();

    lock.lock();

    try {
      if (!productIdToReviews.containsKey(productId)) {
        productIdToReviews.put(productId, new ArrayList<>());
      }
    } finally {
      lock.unlock();
    }
  }

  public void removeProduct(int productId) {
    Lock lock = getLockForRemoveProduct();

    lock.lock();

    try {
      productIdToReviews.remove(productId);
    } finally {
      lock.unlock();
    }
  }

  public void addProductReview(int productId, String review) {

    Lock lock = getLockForAddProductReview();

    lock.lock();

    try {
      if (!productIdToReviews.containsKey(productId)) {
        productIdToReviews.put(productId, new ArrayList<>());
      }
      productIdToReviews.get(productId).add(review);
    } finally {
      lock.unlock();
    }
  }

  public List<String> getAllProductReviews(int productId) {

    Lock lock = getLockForGetAllProductReviews();

    lock.lock();

    try {
      if (productIdToReviews.containsKey(productId)) {
        return Collections.unmodifiableList(productIdToReviews.get(productId));
      }
    } finally {
        lock.unlock();
    }

    return Collections.emptyList();
  }

  public Optional<String> getLatestReview(int productId) {
    Lock lock = getLockForGetLatestReview();

    lock.lock();

    try {
      if (productIdToReviews.containsKey(productId) && !productIdToReviews.get(productId).isEmpty()) {
        List<String> reviews = productIdToReviews.get(productId);
        return Optional.of(reviews.get(reviews.size() - 1));
      }
    } finally {
      lock.unlock();
    }

    return Optional.empty();
  }

  public Set<Integer> getAllProductIdsWithReviews() {
    Lock lock = getLockForGetAllProductIdsWithReviews();

    lock.lock();

    try {
      Set<Integer> productsWithReviews = new HashSet<>();
      for (Map.Entry<Integer, List<String>> productEntry : productIdToReviews.entrySet()) {
        if (!productEntry.getValue().isEmpty()) {
          productsWithReviews.add(productEntry.getKey());
        }
      }
      return productsWithReviews;
    } finally {
      lock.unlock();
    }
  }

  private Lock getLockForAddProduct() {
    return writeLock;
  }

  private Lock getLockForRemoveProduct() {
    return writeLock;
  }

  private Lock getLockForAddProductReview() {
    return writeLock;
  }

  private Lock getLockForGetAllProductReviews() {
    return readLock;
  }

  private Lock getLockForGetLatestReview() {
    return readLock;
  }

  private Lock getLockForGetAllProductIdsWithReviews() {
    return readLock;
  }
}
