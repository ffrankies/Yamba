package pack1.yamba;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Frank on 1/23/2016.
 */
public class SettingsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            SettingsFragment fragment = new SettingsFragment();
            getFragmentManager().beginTransaction().add(
                    android.R.id.content,fragment,fragment.getClass().getSimpleName()).commit();
        }
    }

}
