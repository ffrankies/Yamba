package pack1.yamba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            StatusFragment fragment = new StatusFragment();
            getFragmentManager().beginTransaction().add(
                    android.R.id.content, fragment, fragment.getClass().getSimpleName()).commit();
        }
        setContentView(R.layout.new_activity_status);
    }
}
