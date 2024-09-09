package lectures.atomic;

public class IncrementThread extends Thread {

  private final InventoryCounter inventoryCounter;

  public IncrementThread(InventoryCounter inventoryCounter) {
    this.inventoryCounter = inventoryCounter;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      inventoryCounter.increment();
    }  }
}
