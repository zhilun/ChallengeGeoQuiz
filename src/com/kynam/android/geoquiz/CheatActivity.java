package com.kynam.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	public static final String EXTRA_ANSWER_IS_TRUE = "com.kynam.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.kynam.android.gequiz.answer_shown";
	private boolean mAnswerIsTrue;
	private TextView mAswerTextView;
	private Button mShowAnswer;
	
	private static final String TAG = "CheatActivity";
	private static final String KEY_CHEATCLICK = "CheatClick";
	private boolean mIsCheater;
	private int mCurrentIndex;
	
	public static final String EXTRA_INDEX = "com.kynam.android.gequiz.index";
	
	private TextView mApiLevelTextView;
	private String mApiLevel;
	
	
	private void setAnswerShownResult(boolean isAnswerShown, int currentQuestion) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		data.putExtra(EXTRA_INDEX, currentQuestion);
		setResult(RESULT_OK, data);
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		savedInstanceState.putBoolean(KEY_CHEATCLICK, mIsCheater);
	} 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		mIsCheater = false;
		mCurrentIndex = getIntent().getIntExtra(EXTRA_INDEX, 0);
		
		setAnswerShownResult(mIsCheater, mCurrentIndex);		
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mAswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		mApiLevel = "API level is " + Build.VERSION.SDK_INT;
		
		mApiLevelTextView = (TextView)findViewById(R.id.apiLeveTextView);
		mApiLevelTextView.setText(mApiLevel);		
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIsCheater = true;
				
				if (mAnswerIsTrue) {
					mAswerTextView.setText(R.string.true_button);
				} else {
					mAswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(mIsCheater, mCurrentIndex);
				
			}
		});
		
		if (savedInstanceState != null) {
        	mIsCheater = savedInstanceState.getBoolean(KEY_CHEATCLICK, false);
        }
		
		setAnswerShownResult(mIsCheater, mCurrentIndex);
	}

}
