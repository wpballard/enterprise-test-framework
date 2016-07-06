package com.etf.qa.rally;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/***
 * 
 * @author ballaw01
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RallyTestCase {
	
	public String TestCase() default "";
	public String TestSet() default "";
	public String Verdict() default "";
	public String Build() default "";
	public String Notes() default "" ;
	public String Tester() default "";
	public String Duration() default "";
	public String[] TestCaseTestSet() default "";
	public String[] TestCases() default "";

}
