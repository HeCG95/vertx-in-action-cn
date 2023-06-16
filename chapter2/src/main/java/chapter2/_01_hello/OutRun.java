package chapter2._01_hello;

import io.vertx.core.Vertx;

/**
 * 分类名称
 * 分类备注/描述
 *
 * @author HeCG
 * @module [归属项目]
 * @description xxx
 * @date 2023/6/16 22:00
 * @since 1.0
 */
public class OutRun {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());

    }

}
