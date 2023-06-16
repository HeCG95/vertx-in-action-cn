package chapter2._01_hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloVerticle extends AbstractVerticle {

  private final Logger logger = LoggerFactory.getLogger(HelloVerticle.class);

  private long counter = 1;

  @Override
  public void stop() throws Exception {
    System.out.println(">>>>>>>>>>>>>>>>>>>>> done");
  }

  @Override
  public void start() {

    vertx.setPeriodic(5000, id -> {
      logger.info("tick");
    });

    vertx.createHttpServer()
      .requestHandler(req -> {
        logger.info("Request #{} from {}", counter++, req.remoteAddress().host());
        req.response().end("Hello!");
      })
      .listen(8080);

    logger.info("Open http://localhost:8080/");
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HelloVerticle());
  }
}
