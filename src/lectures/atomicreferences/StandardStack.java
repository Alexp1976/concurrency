package lectures.atomicreferences;

public class StandardStack<T> {

  private StackNode<T> head;
  private int counter = 0;

  public synchronized void push(T value) {
    StackNode<T> newHead = new StackNode<T>(value);
    newHead.setNext(head);
    head = newHead;
    counter++;
  }

  public synchronized T pop() {
    if (head == null) {
      return null;
    }

    T value = head.getValue();
    head = head.getNext();
    counter++;
    return value;
  }

  public int getCounter() {
    return counter;
  }
}
