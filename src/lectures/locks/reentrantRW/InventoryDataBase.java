package lectures.locks.reentrantRW;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InventoryDataBase {

  private TreeMap<Integer, Integer> priceToCountMap = new TreeMap<>();
  private ReentrantLock lock = new ReentrantLock();
  private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
  private Lock readLock = reentrantReadWriteLock.readLock();
  private Lock writeLock = reentrantReadWriteLock.writeLock();

  public int getNumberOfItemsPriceRange(int lowerBound, int upperBound) {

    readLock.lock();

    try {
      Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
      Integer toKey = priceToCountMap.floorKey(upperBound);

      if (fromKey == null || toKey == null) {
        return 0;
      }

      NavigableMap<Integer, Integer> rangeOfPrices = priceToCountMap.subMap(fromKey, true, toKey, true);

      int sum = 0;

      for (int numberOfItemsPrice : rangeOfPrices.values()) {
        sum += numberOfItemsPrice;
      }
      return sum;
    } finally {
      readLock.unlock();
    }
  }

  public void addItem(int price) {
    writeLock.lock();
    try {
      priceToCountMap.merge(price, 1, Integer::sum);
    } finally {
      writeLock.unlock();
    }
  }

  public void removeItem(int price) {
    writeLock.lock();
    try {
      Integer numberOfItemsPrice = priceToCountMap.get(price);

      if (numberOfItemsPrice == null || numberOfItemsPrice == 1) {
        priceToCountMap.remove(price);
      } else {
        priceToCountMap.put(price, numberOfItemsPrice - 1);
      }
    } finally {
      writeLock.unlock();
    }
  }
}
