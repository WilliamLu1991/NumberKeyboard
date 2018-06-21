package com.example.mwbyd.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.williamlu.android.numberkeyboard.KeyboardNumberUtil;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_btn)
    Button myBtn;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.my_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_btn:
                KeyboardNumberUtil.getInstance().init(MainActivity.this, Gravity.CENTER, "本次取货数量", 3).setOnlistener(new KeyboardNumberUtil.OnListener() {
                    @Override
                    public void onInputListener(String finalNumber) {
                        ToastUtils.getInstance(MainActivity.this).showMessage(finalNumber);
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
