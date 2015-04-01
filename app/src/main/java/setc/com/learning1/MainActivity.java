package setc.com.learning1;

import android.animation.TimeInterpolator;
import android.graphics.Rect;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextWatcher;
import android.text.Editable;
import setc.com.learning1.DummyDB;
import java.util.Arrays;
import android.widget.Toast;


public class MainActivity extends Activity {

    private final Rect mTmpRect = new Rect();

    private FrameLayout mMainContainer, mEditModeContainer, mEditFragmentContainer;
    private ScrollView mScrollView;
    private RelativeLayout mScrollViewContainer,mFirstGroup;
    private TextView mTv1, mTv3, mTv4, mTv5, mTv6;
    private LinearLayout mSecondGroup;
    private View mFirstSpacer, mSecondSpacer;
    private EditText mTv2, mTv7;
    private String[] myDataset ;
    private TimeInterpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    private int ANIMATION_DURATION = 3500;
    private int mHalfHeight;
    private CustomAnimator animator = new CustomAnimator(); // added for custom animator
    DummyDB db = new DummyDB();

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
                // Pass the calculated height to animator
                animator.setEditModeHalfHeight(mHalfHeight);
            }
        });

        setContentView(view);


        retrieveViews();

        mTv2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                startAnimation();
            }
        });




/*    mTv1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stopAnimation();
        }
    }); */

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mTv2.setText(myDataset[position]);
                        stopAnimation();

                    }
                }));

        mTv2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String theText = s.toString();
                myDataset = db.getCityList(theText);
            }
        });


    }

    private void retrieveViews() {
        mMainContainer = (FrameLayout) findViewById(R.id.main_container);
        mScrollView = (ScrollView) findViewById(R.id.normal_mode_container);
        mScrollViewContainer = (RelativeLayout) findViewById(R.id.scrollview_container);

        mFirstGroup = (RelativeLayout) findViewById(R.id.first_group_container);
        mTv1 = (TextView) findViewById(R.id.from);
       mTv2 = (EditText) findViewById(R.id.editText);
      mTv3 = (TextView) findViewById(R.id.textView2);
      /*  mTv7 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);*/
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
                animator.setAnimatorViews(mMainContainer, mTv2, mFirstGroup, Arrays.asList(new View[]{mSecondGroup, mSecondSpacer}), mFirstSpacer, mEditModeContainer, Arrays.asList(new View[]{}));
			//animator.setAnimatorViews(mMainContainer, mTv4, mSecondGroup, Arrays.asList(new View[]{}), mSecondSpacer, mEditModeContainer, Arrays.asList(new View[]{mFirstGroup, mFirstSpacer}));
                animator.prepareAnimation();
                animator.start();

        /*        new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animator.prepareRevert();
                        animator.start();
                    }
                }, 6000);*/
            }
        }, 600);
    }

    private void stopAnimation(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animator.prepareRevert();
                animator.start();
            }
        }, 600);

    }
}
