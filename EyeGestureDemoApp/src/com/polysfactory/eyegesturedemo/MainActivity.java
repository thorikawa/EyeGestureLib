package com.polysfactory.eyegesturedemo;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.glass.eye.EyeGesture;
import com.google.android.glass.eye.EyeGestureManager;
import com.google.android.glass.eye.EyeGestureManager.Listener;
import com.google.android.glass.media.Sounds;

public class MainActivity extends Activity {

    private static final String TAG = "EyeGestureDemo";

    private AudioManager mAudioManager;

    private TextView mTextView;

    private EyeGestureManager mEyeGestureManager;
    private EyeGestureListener mEyeGestureListener;

    // private EyeGesture target1 = EyeGesture.DON;
    // private EyeGesture target2 = EyeGesture.DOFF;
    private EyeGesture target1 = EyeGesture.WINK;
    private EyeGesture target2 = EyeGesture.DOUBLE_BLINK;

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
        mEyeGestureListener = new EyeGestureListener();

        // print out each eye gesture is supported or not
        for (EyeGesture eg : EyeGesture.values()) {
            boolean supported = mEyeGestureManager.isSupported(eg);
            Log.d(TAG, eg.name() + ":" + supported);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEyeGestureManager.register(target1, mEyeGestureListener);
        mEyeGestureManager.register(target2, mEyeGestureListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEyeGestureManager.unregister(target1, mEyeGestureListener);
        mEyeGestureManager.unregister(target2, mEyeGestureListener);
    }

    private class EyeGestureListener implements Listener {

        @Override
        public void onEnableStateChange(EyeGesture eyeGesture, boolean paramBoolean) {
            Log.i(TAG, eyeGesture + " state changed:" + paramBoolean);
        }

        @Override
        public void onDetected(final EyeGesture eyeGesture) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAudioManager.playSoundEffect(Sounds.SUCCESS);
                    Log.i(TAG, eyeGesture + " is detected");
                    mTextView.setText("Detected " + eyeGesture + "!");
                }
            });
        }
    }

}