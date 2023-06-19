package chapter6;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.AbstractVerticle;

public class ProxyClient extends AbstractVerticle {

  @Override
  public void start() {
    // Obtaining a service proxy
    SensorDataService service = SensorDataService.createProxy(vertx, "sensor.data-service");
    service.average(ar -> {
      if(ar.succeeded()){
        System.out.println("Average = "+ar.result());
      }else {
        ar.cause().printStackTrace();
      }
    });
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle("chapter6.HeatSensor",
      new DeploymentOptions().setInstances(4));
    vertx.deployVerticle(new DataVerticle());
    vertx.deployVerticle(new ProxyClient());
  }
}
