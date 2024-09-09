package lectures.condition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.StringJoiner;

public class MatriceGenerator {

  private static final String OUTPUT_FILE = "./resources/matrice.txt";
  private static final int N = 10;
  private static final int NUMBER_OF_MATRIX_PAIRS = 10000;

  public static void main(String[] args) throws IOException {

    File file = new File(OUTPUT_FILE);
    FileWriter writer = new FileWriter(file);

    createMatrices(writer);

    writer.flush();
    writer.close();
  }

  private static void createMatrices(FileWriter writer) throws IOException {

    Random random = new Random();

    for (int i = 0; i < NUMBER_OF_MATRIX_PAIRS*2; i++) {
      float[][] matrix = createMatrix(random);
      saveMatrixToFile(writer, matrix);
    }
  }

  private static float[][] createMatrix(Random random) {

    float[][] matrix = new float[N][N];

    for (int i = 0; i < N; i++) {
      matrix[i] = createRow(random);
    }

    return matrix;
  }

  private static float[] createRow(Random random) {
    float[] row = new float[N];

    for (int i = 0; i < N; i++) {
      row[i] = random.nextFloat() * random.nextInt(100);
    }

    return row;
  }

  private static void saveMatrixToFile(FileWriter writer, float[][] matrix) throws IOException {

    for (int row = 0; row < N; row++) {
      StringJoiner joiner = new StringJoiner(", ");
      for (int column = 0; column < N; column++) {
        joiner.add(String.format("%.2f", matrix[row][column]));
      }
      writer.write(joiner.toString());
      writer.write("\n");
    }

    writer.write("\n");
  }
}
