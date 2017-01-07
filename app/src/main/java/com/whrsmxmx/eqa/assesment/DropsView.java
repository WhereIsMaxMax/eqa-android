package com.whrsmxmx.eqa.assesment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.whrsmxmx.eqa.R;

/**
 * Created by Max on 22.12.2016.
 */

public class DropsView extends LinearLayout {

    private static final int DROPS_MARGIN_PX = 28;
    private static final int DROPS_SIZE = 64;
    private static final String TAG = DropsView.class.getName();

    DropsInteraction listener;

    public DropsView(Context context, int dropsNumber) {
        super(context);
        listener = (DropsInteraction) context;
        initView(dropsNumber, context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    private void initView(int dropsNumber, Context context) {
        final LinearLayout rootContainer = new LinearLayout(context);
        rootContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        rootContainer.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        lineParams.gravity = Gravity.CENTER_HORIZONTAL;
        LinearLayout topLineContainer = new LinearLayout(context);
        LinearLayout botLineContainer = new LinearLayout(context);
        topLineContainer.setGravity(Gravity.CENTER);
        botLineContainer.setGravity(Gravity.CENTER);
        topLineContainer.setLayoutParams(lineParams);
        botLineContainer.setLayoutParams(lineParams);
        rootContainer.addView(topLineContainer);
        rootContainer.addView(botLineContainer);

//        topLineContainer.setBackgroundColor(Color.BLUE);
//        rootContainer.setBackgroundColor(Color.GREEN);

//        ViewTreeObserver vto1 = rootContainer.getViewTreeObserver();
//        vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Log.i(TAG, "dropsRoot width="+rootContainer.getWidth()+" height="+rootContainer.getHeight());
//                Log.i(TAG, "topLineContainer width="+topLineContainer.getWidth()+" height="+topLineContainer.getHeight());
//
//            }
//        });
//
        int lineCapacity = dropsNumber/2;
        for (int i = 0; i < dropsNumber; i++){
            ImageView dropView = new ImageView(context);
            dropView.setTag(i);
            LayoutParams lp = new LayoutParams(DROPS_SIZE, DROPS_SIZE);
            lp.setMargins(DROPS_MARGIN_PX, DROPS_MARGIN_PX, DROPS_MARGIN_PX, DROPS_MARGIN_PX);
            dropView.setLayoutParams(lp);
            dropView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDropClicked(Integer.valueOf(v.getTag().toString()));
                }
            });
            dropView.setImageDrawable(ContextCompat.getDrawable(context,
                    i==0?R.drawable.ic_drop_selected:R.drawable.ic_drop_unselected));
            if (i<lineCapacity)
                topLineContainer.addView(dropView);
            else
                botLineContainer.addView(dropView);
        }

        addView(rootContainer);
    }
}
