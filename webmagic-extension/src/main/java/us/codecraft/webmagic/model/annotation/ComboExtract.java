package us.codecraft.webmagic.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ComboExtract {
	
	/**
	 * operator of match logic.
	 * <b>AND</b>
	 * <b>OR</b>
	 */
	public enum OP {AND,OR};
	
	OP op() default OP.OR;

	ExtractBy[] value();
}
