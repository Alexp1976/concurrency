package lectures.condition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainApplication {

  private static final String INPUT_FILE = "./resources/matrice.txt";
  private static final String OUTPUT_FILE = "./resources/matrice_output.txt";
  private static final int N = 10;

  public static void main(String[] args) throws IOException {

    ThreadSafeQueue queue = new ThreadSafeQueue();
    File inputFile = new File(INPUT_FILE);
    File outputFile = new File(OUTPUT_FILE);

    MatricesReaderProducer matricesReader = new MatricesReaderProducer(new FileReader(inputFile), queue, N);
    MatricesMultiplierConsumer multiplierConsumer = new MatricesMultiplierConsumer(new FileWriter(outputFile), queue, N);

    matricesReader.start();
    multiplierConsumer.start();
  }
}
