package us.codecraft.webmagic.selector;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.2.1
 */
public abstract class Selectors {

    public static RegexSelector regex(String expr) {
        return SelectorFactory.getInstatnce().newRegexSelector(expr);
    }

    public static CssSelector $(String expr) {
        return SelectorFactory.getInstatnce().newCssSelector(expr);
    }

    public static XpathSelector xpath(String expr) {
        return SelectorFactory.getInstatnce().newXpathSelector(expr);
    }

    public static AndSelector and(Selector... selectors) {
        return new AndSelector(selectors);
    }

    public static OrSelector or(Selector... selectors) {
        return new OrSelector(selectors);
    }

    public static void main(String[] args) {
        String s = "a";
        or(regex("<title>(.*)</title>"), xpath("//title"), $("title")).select(s);
    }

}
