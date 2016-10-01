# ButterKnifeLite
[ ![Download](https://api.bintray.com/packages/janishar/mindorks/butterknifelite/images/download.svg) ](https://bintray.com/janishar/mindorks/butterknifelite/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ButterKnifeLite-yellow.svg?style=flat)](http://android-arsenal.com/details/1/4417)

##Android View initializer and onClick Listener boiler plate code remover

####This library is based on the ideas of ButterKnife but with use cases targeted for view binding of xml. Resulting is a tiny and compact library that don't hamper the existing project with lots of unused codes and consuming unneccessery build apk size

##@BindView annotation to refer any view defined in XML (Note: Use public Keyword)
```java

 @BindView(R.id.txtView)
 public TextView textView1;

 
```

##@OnClick annotation to set onClick method for any view defined in XML
```java

@OnClick(R.id.btn1)
public void onBtn1Click(){
    textView1.setText("Btn 1 click");
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnifeLite.unbind(this);
    }
}

```

##Call  ButterKnifeLite.unbind(this) in the onDestroy() method
```java

@Override
public void onDestroy() {
    super.onDestroy();
    ButterKnifeLite.unbind(this);
}

```

## For other classes 
```java

@Override
protected void finalize() throws Throwable {
    super.finalize();
    ButterKnifeLite.unbind(this);
}

```

#Gradle
```groovy
  compile 'com.mindorks:butterknifelite:0.0.2'
```

# Recent Libraries: 
#[`PlaceHolderView`](https://github.com/janishar/PlaceHolderView)
#### `PlaceHolderView` helps create views without any adapter in a very modular form. It uses the power of RecyclerView and enhances it to another level. For the first time with the list view comes card stack view.

#[`JPost`](https://https://github.com/janishar/JPost)
#### JPost is a pubsub library based on massages over a channel. It's very efficient and much powerful than other pubsub libraries. It prevents memory leak and increases code control. Also, provide a mechanism to run code asynchronously.

#### Why should you use `JPost` library
1. In contrast to the existing pub-sub libraries, it hold the subscribers with weakreference. Thus it doesn't create memory leaks.
2. Single message can be sent to selected subscribes. This avoids the problem of event getting received at undesirable places. Thus minimising the chances of abnormal application behaviour.
3. The subscriber addition can be controlled by using private channels. It minimises the chances of adding subscribes by mistake to receive undesirable messages.
4. It is a tiny library < 55kb . Thus not effecting the application overall size.
5. It facilicates synchronous as well as asynchronous message delivery and processing.
6. It provides a mechanism to run code asynchronously.

# Proguard Note:
### If you are using proguard, then add this rule in proguard-project.txt
```groovy
  -keepattributes *Annotation*
  -keepclassmembers class ** {
    @com.mindorks.butterknifelite.annotations.** <methods>;
  }
```
