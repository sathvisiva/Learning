package setc.com.learning1;

import android.animation.TimeInterpolator;
import android.graphics.Rect;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends Activity {

    private final Rect mTmpRect = new Rect();

    private FrameLayout mMainContainer, mEditModeContainer, mEditFragmentContainer;
    private ScrollView mScrollView;
    private RelativeLayout mScrollViewContainer,mFirstGroup;
    private TextView mTv1, mTv3, mTv4, mTv5, mTv6;
    private LinearLayout mSecondGroup;
    private View mFirstSpacer, mSecondSpacer;
    private AutoCompleteTextView mTv2, mTv7;

    private TimeInterpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    private int ANIMATION_DURATION = 3500;
    private int mHalfHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                mHalfHeight = view.getHeight() / 2;
                mEditModeContainer.setTranslationY(mHalfHeight);
                mEditModeContainer.setAlpha(0f);
            }
        });

        setContentView(view);

        retrieveViews();

        startAnimation();
    }

    private void retrieveViews() {
        mMainContainer = (FrameLayout) findViewById(R.id.main_container);
        mScrollView = (ScrollView) findViewById(R.id.normal_mode_container);
        mScrollViewContainer = (RelativeLayout) findViewById(R.id.scrollview_container);

        mFirstGroup = (RelativeLayout) findViewById(R.id.first_group_container);
        mTv1 = (TextView) findViewById(R.id.from);
        mTv2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        mTv3 = (TextView) findViewById(R.id.textView2);
        mTv7 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        mFirstSpacer = findViewById(R.id.first_spacer);

        mSecondGroup = (LinearLayout) findViewById(R.id.second_group_container);
        mTv4 = (TextView) findViewById(R.id.tv4);
        mTv5 = (TextView) findViewById(R.id.tv5);

        mSecondSpacer = findViewById(R.id.second_spacer);

        mEditModeContainer = (FrameLayout) findViewById(R.id.edit_mode_container);
        mEditFragmentContainer = (FrameLayout) findViewById(R.id.edit_mode_fragment_container);
    }

    private void startAnimation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Show edit mode
                focusOn(mTv1, mFirstGroup, true);
                fadeOutToBottom(mSecondGroup, true);
                stickTo(mFirstSpacer, mTv1, true);
                slideInToTop(mEditModeContainer, true);
                mEditModeContainer.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Back to normal mode
                        slideOutToBottom(mEditModeContainer, true);
                        unstickFrom(mFirstSpacer, mTv2, true);
                        fadeInToTop(mSecondGroup, true);
                        unfocus(mTv2, mFirstGroup, true);
                    }
                }, 6000);
            }
        }, 2000);
    }

    private void focusOn(View v, View movableView, boolean animated) {

        v.getDrawingRect(mTmpRect);
        mMainContainer.offsetDescendantRectToMyCoords(v, mTmpRect);

        movableView.animate().
                translationY(-mTmpRect.top).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(movableView)).
                start();
    }

    private void unfocus(View v, View movableView, boolean animated) {
        movableView.animate().
                translationY(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(movableView)).
                start();
    }

    private void fadeOutToBottom(View v, boolean animated) {
        v.animate().
                translationYBy(mHalfHeight).
                alpha(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(v)).
                start();
    }

    private void fadeInToTop(View v, boolean animated) {
        v.animate().
                translationYBy(-mHalfHeight).
                alpha(1).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(v)).
                start();
    }

    private void slideInToTop(View v, boolean animated) {
        v.animate().
                translationY(0).
                alpha(1).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setListener(new LayerEnablingAnimatorListener(v)).
                setInterpolator(ANIMATION_INTERPOLATOR);
    }

    private void slideOutToBottom(View v, boolean animated) {
        v.animate().
                translationY(mHalfHeight * 2).
                alpha(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setListener(new LayerEnablingAnimatorListener(v)).
                setInterpolator(ANIMATION_INTERPOLATOR);
    }

    private void stickTo(View v, View viewToStickTo, boolean animated) {
        v.getDrawingRect(mTmpRect);
        mMainContainer.offsetDescendantRectToMyCoords(v, mTmpRect);

        v.animate().
                translationY(viewToStickTo.getHeight() - mTmpRect.top).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                start();
    }

    private void unstickFrom(View v, View viewToStickTo, boolean animated) {
        v.animate().
                translationY(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(viewToStickTo)).
                start();
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
}
