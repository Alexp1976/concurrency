package lectures.condition;

import java.io.FileReader;
import java.util.Scanner;

public class MatricesReaderProducer extends Thread {

  private Scanner scanner;
  private ThreadSafeQueue queue;

  private int N;

  public MatricesReaderProducer(FileReader reader, ThreadSafeQueue queue, int N) {
    this.scanner = new Scanner(reader);
    this.queue = queue;
    this.N = N;
  }

  @Override
  public void run() {
    while (true) {
      float[][] matrix1 = readMatrix();
      float[][] matrix2 = readMatrix();
      if (matrix1 == null || matrix2 == null) {
        queue.terminate();
        System.out.println("No more matrices to read. Producer Thread is terminating.");
        return;
      }

      MatricesPair matricesPair = new MatricesPair();
      matricesPair.setMatrix1(matrix1);
      matricesPair.setMatrix2(matrix2);

      queue.add(matricesPair);
    }
  }

  private float[][] readMatrix() {
    float[][] matrix = new float[N][N];
    for (int row = 0; row < N; row++) {
      if (!scanner.hasNext()) {
        return null;
      }
      String[] line = scanner.nextLine().split(",");
      for (int column = 0; column < N; column++) {
        matrix[row][column] = Float.parseFloat(line[column]);
      }
    }
    scanner.nextLine();
    return matrix;
  }
}
