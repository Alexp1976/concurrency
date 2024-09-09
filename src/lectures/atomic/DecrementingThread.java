package lectures.atomic;

public class DecrementingThread extends Thread {

  private final InventoryCounter inventoryCounter;

  public DecrementingThread(InventoryCounter inventoryCounter) {
    this.inventoryCounter = inventoryCounter;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      inventoryCounter.decrement();
    }
  }
}
