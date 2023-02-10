package vertx.fun.nio.regex;

import java.util.regex.Pattern;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/11 0:48
 * @since 1.0
 */
public class PatternTest {

    public static void main(String[] args) {
        System.out.println(goodAnswer("es"));
    }

    public static boolean goodAnswer (String answer)
    {
        return (Pattern.matches ("[Yy]es|[Yy]|[Tt]rue", answer));
    }

}
