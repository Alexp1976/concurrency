import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lectures.ThroughputHttpServer;

public class Throughput {

  private static final String INPUT_FILE = "resources/war_and_peace.txt";

  public static void main(String[] args) throws IOException {

    String text = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
    ThroughputHttpServer server = new ThroughputHttpServer();
    server.startServer(text);
  }
}
