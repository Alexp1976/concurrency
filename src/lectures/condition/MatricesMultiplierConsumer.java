package lectures.condition;

import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class MatricesMultiplierConsumer extends Thread {

  private ThreadSafeQueue queue;
  private FileWriter writer;
  private int N;

  public MatricesMultiplierConsumer(FileWriter writer, ThreadSafeQueue queue, int N) {
    this.writer = writer;
    this.queue = queue;
    this.N = N;
  }

  @Override
  public void run() {

    while (true) {
      MatricesPair matricesPair = queue.remove();
      if (matricesPair == null) {
        System.out.println("No more matrices to read from the queue, consumer is terminating");
        break;
      }
      float[][] result = multiplyMatrices(matricesPair.getMatrix1(), matricesPair.getMatrix2());
      try {
        saveMatrixToFile(writer, result);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    try {
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private float[][] multiplyMatrices(float[][] matrix1, float[][] matrix2) {

    float[][] result = new float[N][N];

    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        for (int counter = 0; counter < N; counter++) {
          result[row][col] += matrix1[row][counter] * matrix2[counter][counter];
        }
      }
    }

    return result;
  }

  private void saveMatrixToFile(FileWriter writer, float[][] matrix) throws IOException {

    for (int row = 0; row < N; row++) {
      StringJoiner joiner = new StringJoiner(", ");
      for (int col = 0; col < N; col++) {
        joiner.add(String.format("%2f", matrix[row][col]));
      }
      writer.write(joiner.toString());
      writer.write("\n");
    }

    writer.write("\n");
  }
}
