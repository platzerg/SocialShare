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
import com.platzerworld.facebook.utils.actions.Cursor;
import com.platzerworld.facebook.utils.entities.Like;
import com.platzerworld.facebook.utils.fb.SimpleFacebook;
import com.platzerworld.facebook.utils.listeners.OnLikesListener;

import java.util.List;

public class GetLikesFragment extends BaseFragment{

	private final static String EXAMPLE = "Get likes";
	
	private TextView mResult;
	private Button mGetButton;
	private TextView mMore;
	private String mAllPages = "";

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
		mGetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAllPages = "";
				mResult.setText(mAllPages);

				String entityId = "14104316802_522484207864952";
				SimpleFacebook.getInstance().getLikes(entityId, new OnLikesListener() {

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
					public void onComplete(List<Like> response) {
						hideDialog();
						// make the result more readable. 
						mAllPages += "<u>\u25B7\u25B7\u25B7 (paging) #" + getPageNum() + " \u25C1\u25C1\u25C1</u><br>";
						mAllPages += Utils.join(response.iterator(), "<br>", new Utils.Process<Like>() {
							@Override
							public String process(Like like) {
								return "\u25CF " + like.getUser().getName() + " \u25CF";
							}
						});
						mAllPages += "<br>";
						mResult.setText(Html.fromHtml(mAllPages));

						// check if more pages exist
						if (hasNext()) {
							enableLoadMore(getCursor());
						} else {
							disableLoadMore();
						}
					}
				});
			}
		});
		return view;
	}

	private void enableLoadMore(final Cursor<List<Like>> cursor) {
		mMore.setVisibility(View.VISIBLE);
		mMore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mAllPages += "<br>";
				cursor.next();
			}
		});
	}

	private void disableLoadMore() {
		mMore.setOnClickListener(null);
		mMore.setVisibility(View.INVISIBLE);
	}
}
