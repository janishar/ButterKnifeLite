package com.mindorks.butterknifelite;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.Button;

import com.mindorks.butterknifelite.annotations.BindButtonSelector;
import com.mindorks.butterknifelite.annotations.BindView;
import com.mindorks.butterknifelite.annotations.OnClick;
import com.mindorks.butterknifelite.annotations.OnLongClick;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by janisharali on 16/08/16.
 */

/**
 * Static class with annotation extraction methods
 */
public class ButterKnifeLite {


    private static final String TAG = "ButterKnifeLite";


    /**
     * annotations for activity class
     * @param target is the activity with annotation
     */
    public static void bind(final Activity target) {
        bindViews(target, target.getClass().getDeclaredFields(), target.findViewById(android.R.id.content));
        createOnClick(target, target.getClass().getDeclaredMethods(), target.findViewById(android.R.id.content));
        createOnLongClick(target, target.getClass().getDeclaredMethods(), target.findViewById(android.R.id.content));
        bindToggle(target, target.getClass().getDeclaredFields(), target.getClass().getDeclaredMethods(), target.findViewById(android.R.id.content));
    }


    /**
     * annotations for any class with the inflated view from XML or the root view
     * @param obj is any class instance with annotations
     * @param promptsView is the inflated view from the XML
     */
    public static void bind(final Object obj, View promptsView){
        bindViews(obj, obj.getClass().getDeclaredFields(), promptsView);
        createOnClick(obj, obj.getClass().getDeclaredMethods(), promptsView);
        createOnLongClick(obj, obj.getClass().getDeclaredMethods(), promptsView);
        bindToggle(obj, obj.getClass().getDeclaredFields(), obj.getClass().getDeclaredMethods(), promptsView);
    }


    /**
     * initiate the onclick listener for the annotated public methods
     * @param obj is any class instance with annotations
     * @param methods list of methods in the class with annotation
     * @param rootView is the inflated view from the XML
     */
    private static void createOnClick(final Object obj, Method[] methods, View rootView){
        for(final Method method : methods){
            Annotation annotation = method.getAnnotation(OnClick.class);
            if(annotation != null){
                OnClick onClick = (OnClick) annotation;
                int id = onClick.value();
                View view =  rootView.findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.setAccessible(true);
                            method.invoke(obj);
                        }catch (IllegalAccessException e){
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    /**
     * initiate the onLongclick listener for the annotated public methods
     * @param obj is any class instance with annotations
     * @param methods list of methods in the class with annotation
     * @param rootView is the inflated view from the XML
     */
    private static void createOnLongClick(final Object obj, Method[] methods, View rootView){
        for(final Method method : methods){
            Annotation annotation = method.getAnnotation(OnLongClick.class);
            if(annotation != null){
                OnLongClick onLongClick = (OnLongClick) annotation;
                int id = onLongClick.value();
                View view =  rootView.findViewById(id);
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        try {
                            method.setAccessible(true);
                            method.invoke(obj);
                        }catch (IllegalAccessException e){
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e){
                            e.printStackTrace();
                        }
                        return true;
                    }
                });
            }
        }
    }

    /**
     * initiate the view for the annotated public fields
     * @param obj is any class instance with annotations
     * @param fields list of methods in the class with annotation
     * @param rootView is the inflated view from the XML
     */
    private static void bindViews(final Object obj, Field[] fields, View rootView){
        for(final Field field : fields) {
            Annotation annotation = field.getAnnotation(BindView.class);
            if (annotation != null) {
                BindView bindView = (BindView) annotation;
                int id = bindView.value();
                View view = rootView.findViewById(id);
                try {
                    field.setAccessible(true);
                    field.set(obj, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * initiate the view for the annotated public fields and also adds
     * clicklistener
     *
     * @param obj      is any class instance with annotations
     * @param fields   list of methods in the class with annotation
     * @param methods  list of methods in the class wtth annotation
     * @param rootView is the inflated view from the XML
     */
    private static void bindToggle(final Object obj, Field[] fields, Method[] methods, View rootView) {
        // Iterating over the fields

        for (final Field field : fields) {
            Annotation annotation = field.getAnnotation(BindButtonSelector.class);

            if (annotation != null) {

                BindButtonSelector bindView = (BindButtonSelector) annotation;

                int id = bindView.value();

                final int pressedBgResId = bindView.selectedBgResource();
                final int defaultBgResId = bindView.defaultBgResource();

                final int textColorOneResId = bindView.textColorNormal();
                final int textColorTwoResId = bindView.textColorSelected();

                final String textOne = bindView.textStrNormal();
                final String textTwo = bindView.textStrSelected();

                final View view = rootView.findViewById(id);

                for (final Method method : methods) {
                    Annotation methodAnnotation = method.getAnnotation(BindButtonSelector.class);

                    if (methodAnnotation != null) {

                        // Add code for setting background drawable and implementing click listener
                        // for default click implemen
                        if (view instanceof Button) {
                            final Button btn = (Button) view;

                            // Create a statelist drawable with states - Selected and Normal
                            final StateListDrawable selectorDrawable = new StateListDrawable();
                            final Resources res = view.getContext().getResources();

                            selectorDrawable.addState(new int[]{android.R.attr.state_selected}, res.getDrawable(pressedBgResId));
                            selectorDrawable.addState(new int[]{}, res.getDrawable(defaultBgResId));

                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View iView) {
                                    // Toggles
                                    if (iView.isSelected()) {
                                        btn.setText(textOne);
                                        btn.setTextColor(res.getColor(textColorOneResId));
                                    } else {
                                        btn.setText(textTwo);
                                        btn.setTextColor(res.getColor(textColorTwoResId));
                                    }

                                    // Finally update the state
                                    btn.setSelected(!iView.isSelected());

                                    // Following will invoke the callback in the activity
                                    try {
                                        method.setAccessible(true);
                                        method.invoke(obj, view);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            // Add selector and default text
                            btn.setBackground(selectorDrawable);
                            btn.setText(textOne);
                            btn.setTextColor(res.getColor(textColorOneResId));
                        }
                    }
                }

                // Init View
                try {
                    field.setAccessible(true);
                    field.set(obj, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
