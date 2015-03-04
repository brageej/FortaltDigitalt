package leselyst.fortaltdigitalt;

import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by BrageEkroll on 18.02.2015.
 */
public class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {

    private FragmentCommunication callback;

    public SwipeGestureDetector(FragmentCommunication callback){
        this.callback = callback;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                           float velocityX, float velocityY) {

        switch (getSlope(e1.getX(), e1.getY(), e2.getX(), e2.getY())) {
            case 1:
                Log.d("tag", "top");
                return true;
            case 2:
                Log.d("tag", "left");
                callback.nextFragment();
                return true;
            case 3:
                Log.d("tag", "down");
                return true;
            case 4:
                Log.d("tag", "right");
                callback.prevFragment();
                return true;
        }
        return false;
    }

    private int getSlope(float x1, float y1, float x2, float y2) {
        Double angle = Math.toDegrees(Math.atan2(y1 - y2, x2 - x1));
        if (angle > 45 && angle <= 135)
            // top
            return 1;
        if (angle >= 135 && angle < 180 || angle < -135 && angle > -180)
            // left
            return 2;
        if (angle < -45 && angle >= -135)
            // down
            return 3;
        if (angle > -45 && angle <= 45)
            // right
            return 4;
        return 0;
    }
}