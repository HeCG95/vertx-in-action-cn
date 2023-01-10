/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 17:08
 * @since 1.0
 */
public class HelloJNICpp {
  static {
    System.loadLibrary("hello"); // hello.dll (Windows) or libhello.so (Unixes)
  }

  // Native method declaration
  private native void sayHello();

  // Test Driver
  public static void main(String[] args) {
    new HelloJNICpp().sayHello();  // Invoke native method
  }
}
