package chapter2._05_opts;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleVerticle extends AbstractVerticle {

  private final Logger logger = LoggerFactory.getLogger(SampleVerticle.class);

  @Override
  public void start() {
    logger.info("n = {}", config().getInteger("n", -1));
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    // 1+2+3
    for (int n = 0; n < 5; n++) {
      JsonObject conf = new JsonObject().put("n", n);
      DeploymentOptions opts = new DeploymentOptions()
        .setConfig(conf)
        .setInstances(n);
      vertx.deployVerticle("chapter2._05_opts.SampleVerticle", opts, ar->{
        if(ar.succeeded()){
//          System.out.println(">>>>>>> "+vertx.getOrCreateContext().getInstanceCount());
        }else {
//          System.out.println("Failed");
//          System.out.println(">>>>>>> "+vertx.getOrCreateContext().getInstanceCount());
//          ar.cause().printStackTrace();
        }
      });
    }
  }
}
