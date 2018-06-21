# NumberKeyboard
简单的自定义数字键盘

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Add the dependency

	dependencies {
	        implementation 'com.github.WilliamLu1991:NumberKeyboard:v1.0'
	}


使用方式：
//init()参数为上下文，键盘在屏幕上的位置，键盘标题，输入数字的最大长度

	KeyboardNumberUtil.getInstance().init(MainActivity.this, Gravity.CENTER, "本次取货数量", 3).setOnlistener(new KeyboardNumberUtil.OnListener() {
                    @Override
                    public void onInputListener(String finalNumber) {
                        ToastUtils.getInstance(MainActivity.this).showMessage(finalNumber);
                    }
                });
