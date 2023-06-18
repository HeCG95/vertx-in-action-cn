package chapter4._02_jukebox;

import io.vertx.core.Vertx;

public class Main {

  public static void main(String[] args) {
    /**
     * nc 127.0.0.1 3000
     * /list
     * /schedule test.mp3
     * /play
     * /pause
     */
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Jukebox());
    vertx.deployVerticle(new NetControl());
  }
}
