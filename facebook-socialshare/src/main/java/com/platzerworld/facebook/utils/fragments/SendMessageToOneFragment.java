package com.platzerworld.facebook.utils.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.platzerworld.facebook.R;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.fb.SimpleFacebook;
import com.platzerworld.facebook.utils.listeners.OnInviteListener;

import java.util.List;
import java.util.Random;

public class SendMessageToOneFragment extends Fragment {

	private final static String EXAMPLE = "Send message - one recipient";

	private Button mButton;
	private TextView mResult;
	private static String MESSAGE_TEXT = "The is my message for you ";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(EXAMPLE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_invite_all, container, false);
		mResult = (TextView) view.findViewById(R.id.result);
		mButton = (Button) view.findViewById(R.id.button);
		mButton.setText(EXAMPLE);
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				OnInviteListener onInviteListener = new OnInviteListener() {
					@Override
					public void onFail(String reason) {
						mResult.setText(reason);
					}

					@Override
					public void onException(Throwable throwable) {
						mResult.setText(throwable.getMessage());
					}

					@Override
					public void onComplete(List<String> recipientFriends, String requestId) {
						String print = "<u><b>Requet Id</b></u><br>" + requestId + "<br><br>";
						print += String.format("<u><b>Recipient Ids (%d)</b></u><br>", recipientFriends.size());
						print += Utils.join(recipientFriends.iterator(), "<br>");
						mResult.setText(Html.fromHtml(print));
					}

					@Override
					public void onCancel() {
						mResult.setText(Html.fromHtml("<u><b>Result</b></u>	<br>Canceled sending message"));
					}
				};

				String id = "10152408430924362";
				String message = MESSAGE_TEXT + getRand();
				SimpleFacebook.getInstance().invite(id, message, onInviteListener, null);
			}
		});
		return view;
	}

	private static int getRand() {
		Random random = new Random();
		return random.nextInt(1000);
	}
}
