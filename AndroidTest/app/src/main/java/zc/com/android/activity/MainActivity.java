package zc.com.android.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import zc.com.android.view.RtButton;
import zc.com.androidtest.R;

/**
 * The Main Activity
 */
public class MainActivity extends Activity {
    private RtButton mRtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRtButton = (RtButton) findViewById(R.id.bt_rt);
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
}
