package lectures.atomicreferences;

public class StackNode<T> {

  private T value;
  public StackNode<T> next;

  public StackNode(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public StackNode<T> getNext() {
    return next;
  }

  public void setNext(StackNode<T> next) {
    this.next = next;
  }
}
