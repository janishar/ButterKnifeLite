package test.mindorks.com.butterknifelite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.butterknifelite.ButterKnifeLite;
import com.mindorks.butterknifelite.annotations.BindButtonSelector;
import com.mindorks.butterknifelite.annotations.BindView;
import com.mindorks.butterknifelite.annotations.OnClick;
import com.mindorks.butterknifelite.annotations.OnLongClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtView)
    private TextView txtView;

    @BindButtonSelector(value = R.id.bind_selector_demo_btn, defaultBgResource = R.drawable.button_bg_normal,
            selectedBgResource = R.drawable.button_bg_pressed, textStrNormal = "Normal", textStrSelected = "Selected",
            textColorNormal = R.color.colorBtnTxtNormal, textColorSelected = R.color.colorBtnTxtPressed)
    private Button selectorBindingDemoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnifeLite.bind(this);
        txtView.setText("Testing");
    }

    @BindButtonSelector(value = R.id.bind_selector_demo_btn, defaultBgResource = R.drawable.button_bg_normal,
            selectedBgResource = R.drawable.button_bg_pressed, textStrNormal = "Normal", textStrSelected = "Selected",
            textColorNormal = R.color.colorBtnTxtNormal, textColorSelected = R.color.colorBtnTxtPressed)
    private void onSelectorBindingBtnClick(View iView){
        String selected = "Button is " +(iView.isSelected() ? "Selected" : "Normal");
        Toast.makeText(MainActivity.this, selected, Toast.LENGTH_SHORT).show();
        // TODO any other implementation
    }

    @OnClick(R.id.btn1)
    private void onBtn1Click(){
        txtView.setText("Btn 1 click");
    }

    @OnClick(R.id.btn2)
    private void onBtn2Click(){
        txtView.setText("Btn 2 click");
    }

    @OnLongClick(R.id.btn2)
    private void onBtn2LongClick(){
        txtView.setText("Btn 2 long click");
    }
}
