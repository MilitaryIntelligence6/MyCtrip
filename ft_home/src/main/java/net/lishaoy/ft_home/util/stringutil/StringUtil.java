package net.lishaoy.ft_home.util.stringutil;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName StringUtil
 * @Description TODO
 * @CreateTime 2021年09月09日 12:54:00
 */
public class StringUtil {

    private StringUtil() {
        throw new RuntimeException(String.format("here are no %s instance for you!", getClass().getName()));
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null
                || string.equals(ConstString.EMPTY.value())
                || string.equals(ConstString.NULL.value());
    }

    public static String safeStringOf(String string) {
        return string == null
                ? ""
                : string;
    }
}
