package com.platzerworld.facebook.utils.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.platzerworld.facebook.R;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Profile;
import com.platzerworld.facebook.utils.fb.SimpleFacebook;
import com.platzerworld.facebook.utils.listeners.OnProfileListener;

public class GetProfileFragment extends BaseFragment{

	private final static String EXAMPLE = "Get profile";
	
	private TextView mResult;
	private Button mGetButton;
	private TextView mMore;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(EXAMPLE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_example_action, container, false);
		mResult = (TextView) view.findViewById(R.id.result);
		mMore = (TextView) view.findViewById(R.id.load_more);
		mMore.setPaintFlags(mMore.getPaint().getFlags() | Paint.UNDERLINE_TEXT_FLAG);
		mGetButton = (Button) view.findViewById(R.id.button);
		mGetButton.setText(EXAMPLE);
		disableLoadMore();
		mGetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				SimpleFacebook.getInstance().getProfile(new OnProfileListener() {

					@Override
					public void onThinking() {
						showDialog();
					}

					@Override
					public void onException(Throwable throwable) {
						hideDialog();
						mResult.setText(throwable.getMessage());
					}

					@Override
					public void onFail(String reason) {
						hideDialog();
						mResult.setText(reason);
					}

					@Override
					public void onComplete(Profile response) {
						hideDialog();
						String str = Utils.toHtml(response);
						mResult.setText(Html.fromHtml(str));
					}
				});
			}
		});
		return view;
	}

	private void disableLoadMore() {
		mMore.setOnClickListener(null);
		mMore.setVisibility(View.INVISIBLE);
	}
}
