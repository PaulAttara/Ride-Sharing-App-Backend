package ca.mcgill.ecse321.driver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {


    public MyProfileFragment() {
        // Required empty public constructor
    }

    Button btnChangeUsername;
    Button btnchangePassord;
    TextView txtAddress;
    Button btnChangeAddress;
    Button btnDeleteAccount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("My Profile");

        final View myProfileView = inflater.inflate(R.layout.fragment_my_profile, null);
        TextView txtusername = myProfileView.findViewById(R.id.txtmyprofileusername);
        txtusername.setText(MainActivity.getUsername());
        btnChangeUsername = myProfileView.findViewById(R.id.btnchangeusername);
        btnchangePassord = myProfileView.findViewById(R.id.btnchangeusername);
        btnChangeAddress = myProfileView.findViewById(R.id.btnchangeusername);
        btnDeleteAccount = myProfileView.findViewById(R.id.btndeleteaccount);

        btnChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUsername(v);
            }
        });
        btnchangePassord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword(v);
            }
        });
        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAddress(v);
            }
        });
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount(v);
            }
        });

        return myProfileView;
    }

    private void changeUsername(View v) {
    }

    private void changePassword(View v) {
    }

    private void changeAddress(View v) {
    }

    private void deleteAccount(View v) {
    }


}
