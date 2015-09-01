package zc.com.android.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import zc.com.android.Model.MessageEvent;
import zc.com.android.view.RtButton;
import zc.com.androidtest.R;

/**
 * The Main Activity
 */
public class MainActivity extends Activity {
    private RtButton mRtButton;
    private MTestHandler mTestHandler;
    private MTestThread mTestThread;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mRtButton = (RtButton) findViewById(R.id.bt_rt);
        mTestHandler = new MTestHandler();
        new MTestThread().start();
        mRtButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("RtButton~~ontouch~~down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("RtButton~~ontouch~~move");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("RtButton~~ontouch~~up");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("Activity~~dispatch~~down");
                return true;
            //break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Activity~~dispatch~~move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("Activity~~dispatch~~up");
                break;
        }
        //return true;
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EventBus.getDefault().post(new MessageEvent("EventBus"));
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("Activity~~ontouchevent~~down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Activity~~ontouchevent~~move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("Activity~~ontouchevent~~up");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Test the performance of the Code
     */
    private Bitmap getFavicon(final WebView webview) {
        Bitmap mFavBitmap = null;
        if (null != webview && (mFavBitmap = webview.getFavicon()) != null) {
            return mFavBitmap;
        } else {
            return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
    }

    /**
     * Different kinds of implement
     * Low Performance
     *
     * @param webview
     * @return
     */
    private Bitmap getFaviconOne(final WebView webview) {
        if (null == webview) {
            return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        } else {
            if (null == webview.getFavicon()) {
                return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            } else {
                return webview.getFavicon();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onEvent(MessageEvent event) {
        Toast.makeText(mContext, event.message, Toast.LENGTH_SHORT).show();
    }

    /**
     * The Handler Oject on The Main Thread
     */
    class MTestHandler extends Handler {
        public MTestHandler() {

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String text = bundle.getString("test");
            mRtButton.setText(text);
        }
    }

    class MTestThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);

                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("test", "fighting");
                msg.setData(bundle);
                mTestHandler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
