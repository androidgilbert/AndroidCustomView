package zc.com.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by zhouchao on 15/8/25.
 * Custom Button and test the event delivery
 */
public class RtButton extends Button {

    public RtButton(Context context) {
        super(context);
    }

    public RtButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RtButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RtButton~~dispatch~~down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("RtButton~~dispatch~~move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RtButton~~dispatch~~up");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RtButton~~ontouchevent~~down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("RtButton~~ontouchevent~~move");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RtButton~~ontouchevent~~up");
                break;
        }
        return super.onTouchEvent(event);
    }
}
