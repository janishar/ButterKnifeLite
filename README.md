# ButterKnifeLite
##Android View initializer and onClick Listener boiler plate code remover

###This library is based on the ideas of ButterKnife but with use cases targeted for view binding of xml. Resulting is a tiny and compact library that don't hamper the existing project with lots of unused codes and consuming unneccessery build apk size

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
```java
  compile 'com.mindorks:butterknifelite:0.0.2'
```

