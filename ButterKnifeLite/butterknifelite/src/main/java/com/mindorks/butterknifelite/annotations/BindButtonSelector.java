package com.mindorks.butterknifelite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * The annotation definition to get public fields and methods from the class
 * This annotation is used with both field and method. It helps in removing boilerplate code for implementing
 * selector on a button (on click of button changes only in text, textcolor and background).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface BindButtonSelector {
    /**
     * Resource id of the button
     */
    int value();

    /**
     * Drawable resource id for the default/normal state
     */
    int defaultBgResource();

    /**
     * Drawable resource id for the selected state
     */
    int selectedBgResource();

    /**
     * Color resource id for the default/normal state
     */
    int textColorNormal();

    /**
     * Color resource id for the selected state
     */
    int textColorSelected();

    /**
     * String resource id for the default/normal state
     */
    String textStrNormal();

    /**
     * String resource id for the seleted state
     */
    String textStrSelected();

}


