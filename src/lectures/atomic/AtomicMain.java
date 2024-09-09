package lectures.atomic;

public class AtomicMain {

  public static void main(String[] args) throws InterruptedException {

    InventoryCounter inventoryCounter = new InventoryCounter();
    IncrementThread incrementThread = new IncrementThread(inventoryCounter);
    DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

    incrementThread.start();
    decrementingThread.start();

    incrementThread.join();
    decrementingThread.join();

    System.out.println("We currently have " + inventoryCounter.getItems() + " items");

  }
}
