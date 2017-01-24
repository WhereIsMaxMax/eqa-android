package com.whrsmxmx.eqa.assesment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.whrsmxmx.eqa.R;

/**
 * Created by Max on 19.01.2017.
 */

public class DecisionView extends LinearLayout {

    static final int TRANSFER_TAG = 991;
    static final int FREEZE_TAG = 992;
    static final int DISCARD_TAG = 993;
    static final int EMPTY_SELECTION_TAG = 990;

    private int mSelected = EMPTY_SELECTION_TAG;

    private Button transferButton;
    private Button freezeButton;
    private Button discardButton;
    private DecisionInterface mListener;

    public DecisionView(Context context) {
        super(context);
        mListener = (DecisionInterface) context;
        init(context);
    }

    public DecisionView(Context context, AttributeSet as) {
        super(context, as);
        if(context instanceof DecisionInterface)
            mListener = (DecisionInterface) context;
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);

        int butPadding = (int) getResources().getDimension(R.dimen.decision_button_padding);

        transferButton = new Button(context);
        transferButton.setText(R.string.transfer);
        transferButton.setGravity(Gravity.CENTER);
        transferButton.setTag(TRANSFER_TAG);
        transferButton.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        transferButton.setPadding(butPadding, butPadding, butPadding, butPadding);
        transferButton.setOnClickListener(mOnClickListener);

        freezeButton = new Button(context);
        freezeButton.setText(R.string.freeze);
        freezeButton.setGravity(Gravity.CENTER);
        freezeButton.setTag(FREEZE_TAG);
        freezeButton.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        freezeButton.setPadding(butPadding, butPadding, butPadding, butPadding);
        freezeButton.setOnClickListener(mOnClickListener);

        discardButton = new Button(context);
        discardButton.setText(R.string.discard);
        discardButton.setGravity(Gravity.CENTER);
        discardButton.setTag(DISCARD_TAG);
        discardButton.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        discardButton.setPadding(butPadding, butPadding, butPadding, butPadding);
        discardButton.setOnClickListener(mOnClickListener);

        LinearLayout.LayoutParams buttonLP = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0.1f);
        transferButton.setLayoutParams(buttonLP);
        freezeButton.setLayoutParams(buttonLP);
        discardButton.setLayoutParams(buttonLP);

        this.addView(transferButton);
        this.addView(freezeButton);
        this.addView(discardButton);
    }

    View.OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int)v.getTag();
            setSelectionBackgroundAndNumber(tag);
            mListener.onDecisionClick(mSelected);
        }
    };

    private void setSelectionBackgroundAndNumber(int tag) {
        discardSelection();
        switch (tag){
            case TRANSFER_TAG:
                if(mSelected==TRANSFER_TAG){
                    transferButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                    mSelected = EMPTY_SELECTION_TAG;
                }
                else {
                    transferButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    mSelected = TRANSFER_TAG;
                }
                break;
            case FREEZE_TAG:
                if(mSelected==FREEZE_TAG){
                    freezeButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                    mSelected = EMPTY_SELECTION_TAG;
                }
                else {
                    freezeButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    mSelected = FREEZE_TAG;
                }
                break;
            case DISCARD_TAG:
                if(mSelected==DISCARD_TAG){
                    discardButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                    mSelected = EMPTY_SELECTION_TAG;
                }
                else {
                    discardButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    mSelected = DISCARD_TAG;
                }
                break;
            default:
                mSelected = EMPTY_SELECTION_TAG;
        }
    }

    private void discardSelection() {
        discardButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        freezeButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        transferButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    public interface DecisionInterface{
        void onDecisionClick(int decision);
    }

    public void setDecisionSelection(String selection){
        mSelected = EMPTY_SELECTION_TAG;
        if(selection.equals(getResources().getString(R.string.transfer))){
            setSelectionBackgroundAndNumber(TRANSFER_TAG);
        }else if (selection.equals(getResources().getString(R.string.freeze))){
            setSelectionBackgroundAndNumber(FREEZE_TAG);
        }else if (selection.equals(getResources().getString(R.string.discard))){
            setSelectionBackgroundAndNumber(DISCARD_TAG);
        } else
            setSelectionBackgroundAndNumber(EMPTY_SELECTION_TAG);
    }

    public String getDecision(){
        String result;
        if(mSelected == TRANSFER_TAG){
            result = getResources().getString(R.string.transfer);
        }else if(mSelected == DISCARD_TAG){
            result = getResources().getString(R.string.discard);
        }else if(mSelected == FREEZE_TAG){
            result = getResources().getString(R.string.freeze);
        }else{
            result = "";
        }
        return result;
    }

}
