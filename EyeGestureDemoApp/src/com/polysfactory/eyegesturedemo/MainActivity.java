package com.polysfactory.eyegesturedemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.glass.eye.EyeGesture;
import com.google.android.glass.eye.EyeGestureManager;
import com.google.android.glass.media.Sounds;

public class MainActivity extends Activity {

    private static final String TAG = "EyeGestureDemo";

    private EyeGestureReceiver mReceiver;

    private AudioManager mAudioManager;

    private EyeGestureManager mEyeGestureManager;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        mEyeGestureManager = EyeGestureManager.from(this);

        mReceiver = new EyeGestureReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mEyeGestureManager.stopDetector(EyeGesture.DOUBLE_BLINK);
        mEyeGestureManager.stopDetector(EyeGesture.WINK);
        mEyeGestureManager.enableDetectorPersistently(EyeGesture.DOUBLE_BLINK, true);
        mEyeGestureManager.enableDetectorPersistently(EyeGesture.WINK, true);

        IntentFilter filter = new IntentFilter("com.google.glass.action.EYE_GESTURE");
        filter.setPriority(2000);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mEyeGestureManager.stopDetector(EyeGesture.DOUBLE_BLINK);
        mEyeGestureManager.stopDetector(EyeGesture.WINK);

        unregisterReceiver(mReceiver);
    }

    class EyeGestureReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mAudioManager.playSoundEffect(Sounds.SUCCESS);
            Bundle extras = intent.getExtras();

            String eyeGesture = extras.getString("gesture");
            boolean screenOff = extras.getBoolean("screen_off");

            Log.d(TAG, eyeGesture + " is detected");

            mTextView.setText("Detected " + eyeGesture + "!");

            abortBroadcast();
        }
    }
}
