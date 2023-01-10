/**
 * @author HeCG
 * @description xxx
 * @date 2023/1/10 16:33
 * @since 1.0
 */
public class HelloJNI {

  static {
    System.load("E:/obank/code/github/HeCG95/vertx-in-action-cn/java-nio/lib/dll/dll/bin/Debug/dll.dll");
//    System.loadLibrary("dll"); // Load native library at runtime
    // hello.dll (Windows) or libhello.so (Unixes)
  }

  // Declare a native method sayHello() that receives nothing and returns void
  private native void sayHello();

  // Test Driver
  public static void main(String[] args) {
    new HelloJNI().sayHello();  // invoke the native method
  }

}

/**
 * 使用：
 * javac HelloJNI.java
 * javah HelloJNI
 *
 * 将java的native方法转换成C函数声明的规则是这样的：Java_{package_and_classname}_{function_name}(JNI arguments)。包名中的点换成单下划线。需要说明的是生成函数中的两个参数：
 * 1. JNIEnv *：这是一个指向JNI运行环境的指针，后面我们会看到，我们通过这个指针访问JNI函数
 * 2. jobject：这里指代java中的this对象
 *
 * Global compiler settings -> Search directories
 * $(JAVA_HOME)\include
 * $(JAVA_HOME)\include\win32
 */
