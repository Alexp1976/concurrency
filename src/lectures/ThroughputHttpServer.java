package lectures;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThroughputHttpServer {

  private static final int NUMBER_OF_THREADS = 4;

  public void startServer(String text) throws IOException {

    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    server.createContext("/search", new WordCountHandler(text));
    Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    server.setExecutor(executor);
    server.start();
  }
}
