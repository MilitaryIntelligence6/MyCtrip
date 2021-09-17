package net.lishaoy.ft_home.util.stringutil;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ConstString
 * @Description TODO
 * @CreateTime 2021年09月09日 10:36:00
 */
public enum ConstString {

    /**
     * String 常量池
     */
    EMPTY(""),

    NULL("null"),
    ;

    private final String value;

    ConstString(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
