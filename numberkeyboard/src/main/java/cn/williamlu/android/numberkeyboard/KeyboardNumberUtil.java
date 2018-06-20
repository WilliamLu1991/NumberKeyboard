package cn.williamlu.android.numberkeyboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Author:lu.en
 * Date:2018/5/21
 * Description: 本类要与pop_number_keyboard布局一起使用
 * 调用KeyboardNumberUtil.getInstance().init(Context context, int layoutIds);
 */
public class KeyboardNumberUtil {
    private static KeyboardNumberUtil instance = null;
    private static OnListener mOnListener;
    private Context       mContext;
    private TextView      mKeyboardTvInput;
    private TextView      mKeyboardTvTitle;
    private Button        mKeyboardBtn0;
    private Button        mKeyboardBtn1;
    private Button        mKeyboardBtn2;
    private Button        mKeyboardBtn3;
    private Button        mKeyboardBtn4;
    private Button        mKeyboardBtn5;
    private Button        mKeyboardBtn6;
    private Button        mKeyboardBtn7;
    private Button        mKeyboardBtn8;
    private Button        mKeyboardBtn9;
    private Button        mKeyboardBtnDelete;
    private Button        mKeyboardBtnClear;
    private Button        mKeyboardBtnCancel;
    private Button        mKeyboardBtnConfirm;
    private StringBuilder mStringBuilder;
    private AlertDialog   mDialog;
    private int           mNumLength;

    public static KeyboardNumberUtil getInstance() {
        synchronized (KeyboardNumberUtil.class) {
            if (instance == null) {
                instance = new KeyboardNumberUtil();
            }
        }
        return instance;
    }

    public KeyboardNumberUtil init(Context context, int gravity, String title, int numLength) {
        mContext = context;
        mNumLength = numLength;
        mDialog = new AlertDialog.Builder(mContext, R.style.KeyboardNumberDialog).create();
        //mDialog.setView(LayoutInflater.from(mContext).inflate(layoutIds, null));
        mDialog.show();
        mDialog.getWindow().setGravity(gravity);

        mDialog.getWindow().setContentView(R.layout.dialog_number_keyboard);
        //初始化键盘布局
        initPopupWindow(mDialog, title);

        return instance;
    }

    private void initPopupWindow(Dialog keyboardView, String title) {
        mKeyboardTvTitle = (TextView) keyboardView.findViewById(R.id.keyboard_tv_title);
        mKeyboardTvTitle.setText(title );
        mKeyboardTvInput = (TextView) keyboardView.findViewById(R.id.keyboard_tv_input);
        mKeyboardBtn0 = (Button) keyboardView.findViewById(R.id.keyboard_btn0);
        mKeyboardBtn1 = (Button) keyboardView.findViewById(R.id.keyboard_btn1);
        mKeyboardBtn2 = (Button) keyboardView.findViewById(R.id.keyboard_btn2);
        mKeyboardBtn3 = (Button) keyboardView.findViewById(R.id.keyboard_btn3);
        mKeyboardBtn4 = (Button) keyboardView.findViewById(R.id.keyboard_btn4);
        mKeyboardBtn5 = (Button) keyboardView.findViewById(R.id.keyboard_btn5);
        mKeyboardBtn6 = (Button) keyboardView.findViewById(R.id.keyboard_btn6);
        mKeyboardBtn7 = (Button) keyboardView.findViewById(R.id.keyboard_btn7);
        mKeyboardBtn8 = (Button) keyboardView.findViewById(R.id.keyboard_btn8);
        mKeyboardBtn9 = (Button) keyboardView.findViewById(R.id.keyboard_btn9);
        mKeyboardBtnDelete = (Button) keyboardView.findViewById(R.id.keyboard_btn_delete);
        mKeyboardBtnClear = (Button) keyboardView.findViewById(R.id.keyboard_btn_clear);
        mKeyboardBtnCancel = (Button) keyboardView.findViewById(R.id.keyboard_btn_cancel);
        mKeyboardBtnConfirm = (Button) keyboardView.findViewById(R.id.keyboard_btn_confirm);
        //监听点击回调
        setClickLisenter();
    }

    private void setClickLisenter() {
        mStringBuilder = new StringBuilder();
        mKeyboardBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("0");
            }
        });
        mKeyboardBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("1");
            }
        });
        mKeyboardBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("2");
            }
        });
        mKeyboardBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("3");
            }
        });
        mKeyboardBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("4");
            }
        });
        mKeyboardBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("5");
            }
        });
        mKeyboardBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("6");
            }
        });
        mKeyboardBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("7");
            }
        });
        mKeyboardBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("8");
            }
        });
        mKeyboardBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputNumber("9");
            }
        });
        mKeyboardBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStringBuilder.length() > 0) {
                    mStringBuilder.deleteCharAt(mStringBuilder.length() - 1);
                    mKeyboardTvInput.setText(mStringBuilder.toString());
                } else {
                    ToastUtils.getInstance(mContext).showMessage("没有输入了哦～！");
                }
            }
        });
        mKeyboardBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStringBuilder.delete(0, mStringBuilder.length());
                mKeyboardTvInput.setText("");
            }
        });
        mKeyboardBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyboardTvInput.setText("");
                mDialog.dismiss();
            }
        });
        mKeyboardBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示最终确认数字
                String finalNumber = getFinalNumber();
                mKeyboardTvInput.setText("");
                mDialog.dismiss();
                mOnListener.onInputListener(finalNumber);
            }
        });
    }

    private String getFinalNumber() {
        String finalNum = mKeyboardTvInput.getText().toString().trim();
        if ("0".equals(finalNum) || "".equals(finalNum)) {
            ToastUtils.getInstance(mContext).showMessage("您还没有输入任何数量哦～！");
        }
        return finalNum;
    }

    /**
     * 设置用户输入数字
     */
    private void setInputNumber(String num) {
        if (mStringBuilder.length() > mNumLength) {
            ToastUtils.getInstance(mContext).showMessage("不能再多啦～！");
            return;
        }
        mStringBuilder.append(num);
        mKeyboardTvInput.setText(mStringBuilder.toString());
    }

    public static void setOnlistener(OnListener onListener) {
        mOnListener = onListener;
    }

    public interface OnListener {

        void onInputListener(String finalNumber);

    }
}
