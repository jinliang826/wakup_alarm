package app.tabsample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import alarmModels.AlarmSetting;

/**
 * Created by Alex on 6/14/2016.
 */
public class AlarmTitleInputFragment extends Fragment {

    private View view;
    private TextView txtTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alarm_title_input,container,false);

        txtTitle = (TextView)view.findViewById(R.id.text_alarm_title);
        txtTitle.setText(AlarmSetting.strAlarmName);
        txtTitle.setSelectAllOnFocus(true);

        txtTitle.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                    AlarmMainActivity.txtAdd.setVisibility(View.VISIBLE);
                    AlarmSetting.strAlarmName = txtTitle.getText().toString();

                    Fragment frag = new AlarmEditFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.alarm_fragment, frag);
                    fragmentTransaction.commit();

                    return true;
                }
                return false;
            }
        });

        return view;
    }
}
