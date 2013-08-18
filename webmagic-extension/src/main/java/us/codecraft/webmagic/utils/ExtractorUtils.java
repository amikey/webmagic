package us.codecraft.webmagic.utils;

import us.codecraft.webmagic.model.ConfigInfoObj;
import us.codecraft.webmagic.model.annotation.ConfigInfo;
import us.codecraft.webmagic.model.annotation.ExprType;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.selector.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tools for annotation converting. <br>
 * @author code4crafter@gmail.com <br>
 * @since 0.2.1
 */
public class ExtractorUtils {

    public static Selector getSelector(ExtractBy extractBy) {
        ConfigInfo configInfo = extractBy.configure();
        boolean isOuterHtml = configInfo.isOuterHtml();
        String attrName = configInfo.attrName();
        String defaultValue = configInfo.defaultValue();
        boolean isTrim = configInfo.isTrim();
        boolean isRemoveTag = configInfo.isRemoveTag();

        ConfigInfoObj configInfoObj = new ConfigInfoObj();
        configInfoObj.setOuterHtml(isOuterHtml);
        configInfoObj.setAttrName(attrName);
        configInfoObj.setDefaultValue(defaultValue);
        configInfoObj.setTrim(isTrim);
        configInfoObj.setRemoveTag(isRemoveTag);
        return getSelector(extractBy.type(),extractBy.value(),configInfoObj);
    }

    public static Selector getSelector(ExprType type, String expr, ConfigInfoObj configInfoObj) {
        if (configInfoObj == null)
            configInfoObj = new ConfigInfoObj();
        boolean isOuterHtml = configInfoObj.isOuterHtml();
        String attrName = configInfoObj.getAttrName();
        String defaultValue = configInfoObj.getDefaultValue();
        boolean isTrim = configInfoObj.isTrim();
        boolean isRemoveTag = configInfoObj.isRemoveTag();
        AbstractedSelector.Temp tempObj = new AbstractedSelector.Temp();
        tempObj.setDefaultValue(defaultValue);
        tempObj.setTrim(isTrim);
        tempObj.setRemoveTag(isRemoveTag);
        Selector selector;
        switch (type) {
            case CSS:
                selector = new CssSelector(expr, isOuterHtml, attrName, tempObj);
                break;
            case REGEX:
                selector = new RegexSelector(expr, tempObj);
                break;
            case XPATH:
                selector = new XpathSelector(expr, tempObj);
                break;
            case CONTAINS:
                selector = new ContainSelector(expr, tempObj);
                break;
            default:
                selector = new XpathSelector(expr, tempObj);
        }
        return selector;
    }


    public static List<Selector> getSelectors(ExtractBy[] extractBies) {
        List<Selector> selectors = new ArrayList<Selector>();
        if (extractBies==null){
            return selectors;
        }
        for (ExtractBy extractBy : extractBies) {
            selectors.add(getSelector(extractBy));
        }
        return selectors;
    }
}
