package com.example.hlqcountdowntimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvShowH, tvShowC, test;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			tvShowH.setText(msg.what - 1 + "s");
			if (msg.what == 0) {
				// 倒计时结束让按钮可用
				tvShowH.setEnabled(true);
				tvShowH.setText("Handler获取验证码");
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvShowH = (TextView) findViewById(R.id.tv_show_h);

		tvShowH.setOnClickListener(listenerH);

		tvShowC = (TextView) findViewById(R.id.tv_show_c);

		tvShowC.setOnClickListener(listenerC);

		test = (TextView) findViewById(R.id.test);

		test.setOnClickListener(listenerT);
	}

	private OnClickListener listenerH = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			tvShowH.setEnabled(false);
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 10; i >= 0; i--) {
						handler.sendEmptyMessage(i);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	};

	private OnClickListener listenerC = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			tvShowC.setEnabled(false);
			timer.start();
		}
	};

	private CountDownTimer timer = new CountDownTimer(10000, 1000) {

		@Override
		public void onTick(long millisUntilFinished) {
			long time = millisUntilFinished / 1000;

			tvShowC.setText(time + "秒后可重发");
		}

		@Override
		public void onFinish() {
			tvShowC.setEnabled(true);
			tvShowC.setText("CountDownTimer获取验证码");
		}

	};

	private OnClickListener listenerT = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			test.setEnabled(false);
			new com.example.hlqcountdowntimer.weight.CountDownTimer(test, 10000, 1000) {
			}.start();
		}
	};

}
