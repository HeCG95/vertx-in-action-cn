package tenksteps.publicapi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CryptoHelper {

  /**
   *
   * openssl genrsa -out private.pem 2048
   * openssl pkcs8 -topk8 -inform PEM -in private.pem -out private_key.pem -nocrypt
   * openssl rsa -in private.pem -outform PEM -pubout -out public_key.pem
   *
   */

  static String publicKey() throws IOException {
    return read("public_key.pem");
  }

  static String privateKey() throws IOException {
    return read("private_key.pem");
  }

  private static String read(String file) throws IOException {
    Path path = Paths.get("public-api", file);
    if (!path.toFile().exists()) {
//      path = Paths.get("..", "public-api", file);
      path = Paths.get("E:\\Code\\Github\\vertx-in-action-cn\\part2-steps-challenge", "public-api", file);
    }
    return String.join("\n", Files.readAllLines(path, StandardCharsets.UTF_8));
  }
}
