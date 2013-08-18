package us.codecraft.webmagic.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Combo 'ExtractBy' extractor with and/or operator.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
public @interface ComboExtract {

	/**
	 * operator of match logic.
	 * <p>
	 * <b>AND</b>:need match all {@code expr}<br>
	 * <b>OR</b>: just match one {@code expr}(In accordance with the order)
	 * </p>
	 */
	public enum OP {
        /**
         * All extractors will be arranged as a pipeline. <br>
         * The next extractor uses the result of the previous as source.
         */
        AND,
        /**
         * All extractors will do extracting separately, <br>
         * and the results of extractors will combined as the final result.
         */
        OR};

    /**
     * The extractors to be combined.
     *
     * @return the extractors to be combined
     */
    ExtractBy[] value();

    /**
     * Combining operation of extractors.<br>
     *
     * @return combining operation of extractors
     */
    OP op() default OP.OR;

    /**
     * Define whether the field can be null.<br>
     * If set to 'true' and the extractor get no result, the entire class will be discarded. <br>
     *
     * @return whether the field can be null
     */
    boolean notNull() default false;

    /**
     * types of source for extracting.
     */
    public static enum Source {
        /**
         * extract from the content extracted by class extractor
         */
        SelectedHtml,
        /**
         * extract from the raw html
         */
        RawHtml
    }

    /**
     * The source for extracting. <br>
     * It works only if you already added 'ExtractBy' to Class. <br>
     *
     * @return the source for extracting
     */
    Source source() default Source.SelectedHtml;

    /**
     * Define whether the extractor return more than one result.
     * When set to 'true', the extractor return a list of string (so you should define the field as List). <br>
     *
     * @return whether the extractor return more than one result
     */
    boolean multi() default false;

}
