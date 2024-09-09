import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import lectures.LatencyOptimization;

public class Latency {

  public static final String SOURCE_FILE = "./resources/many-flowers.jpg";
  public static final String DESTINATION_FILE = "./resources/many-flowers-out.jpg";


  public static void main(String[] args) throws IOException {

    LatencyOptimization lo = new LatencyOptimization();

    BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
    BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

    long start = System.currentTimeMillis();
    //recolorSingleThread(originalImage, resultImage, lo);
    recolorMultiThread(originalImage, resultImage, lo, 8);
    long end = System.currentTimeMillis();

    long duration = end - start;

    System.out.println(duration);

    File outputFile = new File(DESTINATION_FILE);
    ImageIO.write(resultImage, "jpg", outputFile);

  }

  public static void recolorSingleThread(BufferedImage originalImage, BufferedImage resultImage, LatencyOptimization lo)  {
    lo.recolorImage(originalImage, resultImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
  }

  public static void recolorMultiThread(BufferedImage originalImage, BufferedImage resultImage, LatencyOptimization lo, int numberOfThreads) {

    List<Thread> threads = new ArrayList<>();

    int width = originalImage.getWidth();
    int height = originalImage.getHeight() / numberOfThreads;

    for (int i = 0; i < numberOfThreads; i++) {
      final int threadMultiplier =i;
      Thread thread = new Thread(() -> {
        int leftCorner = 0;
        int topCorner = height * threadMultiplier;
        lo.recolorImage(originalImage, resultImage, leftCorner, topCorner, width, height);
      });
      threads.add(thread);
    }

    for (Thread thread : threads) {
      thread.start();
    }

    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException ignored) {
      }
    }
  }
}
