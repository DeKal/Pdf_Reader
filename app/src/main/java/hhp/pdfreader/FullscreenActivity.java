package hhp.pdfreader;

import hhp.pdfreader.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    private ImageButton sort;
    private ImageButton controlPanel;
    private ImageButton edit;
    private View mainView;
    private View controlsView;
    private View contentView;
    private View editControlView;
    private View sortControlView;
    private OnTouchTracking direction = new OnTouchTracking();
    private static final boolean AUTO_HIDE = false;

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createActivityLayout();
        setUpViewPosition();
        setEventForControl();
    }
    private void setSubControlPosition(){
        editControlView.animate()
                .translationY(-editControlView.getHeight());
        sortControlView.animate()
                .translationY(-sortControlView.getHeight());
    }

    private void setUpViewPosition() {
        int mControlsHeight = controlsView.getHeight();
        controlsView.animate()
                .translationY(-mControlsHeight);
        controlsView.bringToFront();
        contentView.animate()
                .translationY(0);
        setSubControlPosition();
    }

    private void setControlsVisibility(boolean visible) {
        if (!visible){
            setUpViewPosition();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            int mControlsHeight = controlsView.getHeight();
            int mShortAnimTime = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);
            setSubControlPosition();
            controlsView.animate()
                    .translationY(0)
                    .setDuration(mShortAnimTime);
            contentView.animate()
                    .translationY(mControlsHeight)
                    .setDuration(mShortAnimTime);

        }
    }
    private void setSubControlVisibility(boolean visible, View subControlView){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int mControlsHeight = controlsView.getHeight();
            int mShortAnimTime = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);
            subControlView.animate()
                    .translationY(visible ? mControlsHeight : -mControlsHeight);
            contentView.animate()
                    .translationY(visible ? mControlsHeight+ subControlView.getHeight() :
                            mControlsHeight)
                    .setDuration(mShortAnimTime);
        }
        if (visible) {
            delayedSubHide(AUTO_HIDE_DELAY_MILLIS);
        }

    }


    private void setEventForControl() {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editControlView.bringToFront();
                controlsView.bringToFront();
                mainView.invalidate();
                setSubControlVisibility(true, editControlView);


            }
        });
        controlPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sort. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSubControlVisibility(true, sortControlView);
                sortControlView.bringToFront();
                controlsView.bringToFront();
                mainView.invalidate();

            }
        });

        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        direction.addCoordinates(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        direction.addCoordinates(event.getX(), event.getY());
                        switch (direction.getDirection()){
                            case OnTouchTracking.GO_DOWN:
                                setControlsVisibility(true);
                                break;
                            case OnTouchTracking.GO_UP:
                                setControlsVisibility(false);
                        }
                }

                return true;
            }
        });
    }

    private void createActivityLayout() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);

        edit = (ImageButton) findViewById(R.id.button_edit);
        controlPanel = (ImageButton) findViewById(R.id.button_control_panel);
        sort = (ImageButton) findViewById(R.id.button_sort);
        controlsView = findViewById(R.id.fullscreen_content_controls);
        contentView = findViewById(R.id.fullscreen_content);
        editControlView = findViewById(R.id.fullscreen_edit_control);
        sortControlView = findViewById(R.id.fullscreen_sort_control);
        mainView = findViewById(R.id.MainView);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }

    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            setControlsVisibility(false);
        }
    };
    Runnable mHideRunnable2 = new Runnable() {
        @Override
        public void run() {
            setSubControlVisibility(false, editControlView);
            setSubControlVisibility(false,sortControlView);
        }
    };

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    private void delayedSubHide(int autoHideDelayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable2);
        mHideHandler.postDelayed(mHideRunnable2, autoHideDelayMillis);
    }

}
