package com.qrazhan.twitchdroidtv;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends Activity{

    private static final String TAG = "PlayerActivity";

    WebView player;

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Stream s = (Stream) getIntent().getSerializableExtra(getResources().getString(R.string.movie));
        player = (WebView) findViewById(R.id.player);
        player.getSettings().setJavaScriptEnabled(true);
        player.getSettings().setMediaPlaybackRequiresUserGesture(false);
        player.setWebViewClient(new MyWebViewClient());
        player.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if(i == KeyEvent.KEYCODE_BUTTON_B || i == KeyEvent.KEYCODE_BACK){
                    player.onPause();
                }

                return false;
            }
        });
        player.loadUrl(s.getVideoUrl());


    }



    @Override
    protected void onPause() {
        player.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() was called");
        player.onPause();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() is called");
        player.onPause();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart was called");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() was called");
        super.onResume();
    }

    /*
     * @Override public boolean onKeyDown(int keyCode, KeyEvent event) { return
     * super.onKeyDown(keyCode, event); }
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        /*if(keyCode == KeyEvent.KEYCODE_BUTTON_B || keyCode == KeyEvent.KEYCODE_BACK){
            System.out.println("went in here");
            player.onPause();
            finish();
        }*/
        System.out.println("called onKeyDown");

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

}
