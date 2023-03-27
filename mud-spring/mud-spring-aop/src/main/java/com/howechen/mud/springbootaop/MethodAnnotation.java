package com.howechen.mud.springbootaop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;



public @interface MethodAnnotation {
  String value() default "MethodTagged";
}
