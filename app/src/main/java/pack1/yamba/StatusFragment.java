package pack1.yamba;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatusFragment extends Fragment implements View.OnClickListener {

    private Button buttonTweet;
    private EditText editStatus;
    private TextView textCounter;
    private int defaultTextColor;

    private static final String TAG = "StatusActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_status, container, false);

        buttonTweet = (Button) view.findViewById(R.id.button_tweet);
        editStatus = (EditText) view.findViewById(R.id.edit_status);
        textCounter = (TextView) view.findViewById(R.id.text_count);

        defaultTextColor = textCounter.getTextColors().getDefaultColor();
        editStatus.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = 140 - editStatus.length();
                textCounter.setText(Integer.toString(count));
                if(count < 0)
                    textCounter.setTextColor(Color.RED);
                else if(count < 10)
                    textCounter.setTextColor(Color.YELLOW);
                else
                    textCounter.setTextColor(defaultTextColor);
            }
        });

        buttonTweet.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String status = editStatus.getText().toString();
        Log.d(TAG, "onClickedWithStatus: " + status);

        new PostTask().execute(status);
    }

    private class PostTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            YambaClient yambaCloud = new YambaClient("student", "password");
            try {
                yambaCloud.postStatus(params[0]);
                return "Successfully posted";
            } catch (YambaClientException e) {
                e.printStackTrace();
                return "Failed to post to yamba service";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Toast.makeText(StatusFragment.this.getActivity(), result, Toast.LENGTH_LONG).show();
        }

    }

}
