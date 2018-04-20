# ButterKnifeLite
[ ![Download](https://api.bintray.com/packages/janishar/mindorks/butterknifelite/images/download.svg) ](https://bintray.com/janishar/mindorks/butterknifelite/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ButterKnifeLite-yellow.svg?style=flat)](http://android-arsenal.com/details/1/4417)

##Android View initializer and Click Listener boiler plate code remover

####This library is based on the ideas of removing boilerplate code like ButterKnife but with use cases targeted for view binding of xml. Resulting is a vey tiny library.

##`@BindView` annotation to refer any view defined in XML
```java

 @BindView(R.id.txtView)
 public TextView textView1;

 
```

##`@OnClick` annotation to set onClick method for any view defined in XML
```java

@OnClick(R.id.btn1)
public void onBtn1Click(){
    textView1.setText("Btn 1 click");
}

```

##`@OnLongClick` annotation to set OnLongClick method for any view defined in XML
```java

@OnLongClick(R.id.btn2)
private void onBtn2LongClick(){
    txtView.setText("Btn 2 long click");
}

```

##`@BindButtonSelector` annotation to refer button and its selector implementation
```java

 @BindButtonSelector(value = R.id.bind_selector_demo_btn, defaultBgResource = R.drawable.button_bg_normal,
            selectedBgResource = R.drawable.button_bg_pressed, textStrNormal = "Normal", textStrSelected = "Selected",
            textColorNormal = R.color.colorBtnTxtNormal, textColorSelected = R.color.colorBtnTxtPressed)
 private Button selectorBindingDemoBtn;

 
```

##`@BindButtonSelector` annotation to set OnClick method for button with selector implementation
```java

   @BindButtonSelector(value = R.id.bind_selector_demo_btn, defaultBgResource = R.drawable.button_bg_normal,
            selectedBgResource = R.drawable.button_bg_pressed, textStrNormal = "Normal", textStrSelected = "Selected",
            textColorNormal = R.color.colorBtnTxtNormal, textColorSelected = R.color.colorBtnTxtPressed)
   private void onSelectorBindingBtnClick(View iView){
        String selected = "Button is " +(iView.isSelected() ? "Selected" : "Normal");
        Toast.makeText(MainActivity.this, selected, Toast.LENGTH_SHORT).show();
        // TODO any other implementation
    }

```


##For Any class with inflated view from XML can be used as described below
##For example in a Fragment
```java

public class MyFragment extends Fragment {

    @BindView(R.id.txtView)
    public TextView txtView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View promptsView = inflater.inflate(R.layout.my_fragment, container, false);
        ButterKnifeLite.bind(this, promptsView);
        return promptsView;
    }

    @OnClick(R.id.btn1)
    public void onBtn1Click(){
        txtView.setText("Btn 1 frag click");
    }

    @OnClick(R.id.btn2)
    public void onBtn2Click(){
        txtView.setText("Btn 2 frag click");
    }
}

```
# Note: In version 0.0.1 and 0.0.2 we had to call ButterKnifeLite.unbind(obj) to remove the binded class. It is not required from version 0.0.3

#Gradle
```groovy
  compile 'com.mindorks:butterknifelite:0.0.3'
```
# Proguard Note:
### If you are using proguard, then add this rule in proguard-project.txt
```groovy
  -keepattributes *Annotation*
  -keepclassmembers class ** {
    @com.mindorks.butterknifelite.annotations.** <methods>;
  }
```

# Recent Libraries: 
#[`PlaceHolderView`](https://github.com/janishar/PlaceHolderView)
#### `PlaceHolderView` helps create views without any adapter in a very modular form. It uses the power of RecyclerView and enhances it to another level. For the first time with the list view comes card stack view.

#[`JPost`](https://github.com/janishar/JPost)
#### JPost is a pubsub library based on messages over a channel. It's very efficient and much powerful than other pubsub libraries. It prevents memory leak and increases code control. Also, provide a mechanism to run code asynchronously.

#### Why should you use `JPost` library
1. In contrast to the existing pub-sub libraries, it hold the subscribers with weakreference. Thus it doesn't create memory leaks.
2. Single message can be sent to selected subscribes. This avoids the problem of event getting received at undesirable places. Thus minimising the chances of abnormal application behaviour.
3. The subscriber addition can be controlled by using private channels. It minimises the chances of adding subscribes by mistake to receive undesirable messages.
4. It is a tiny library < 55kb . Thus not effecting the application overall size.
5. It facilicates synchronous as well as asynchronous message delivery and processing.
6. It provides a mechanism to run code asynchronously.
