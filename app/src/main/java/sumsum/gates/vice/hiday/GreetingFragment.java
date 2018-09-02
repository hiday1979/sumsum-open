package sumsum.gates.vice.hiday;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class GreetingFragment extends Fragment {


    @BindView(R.id.tvUserGreeting)
    TextView tvUserGreeting;
    Unbinder unbinder;
    String userName;
    SharedPreferences sharedPreferences;

    public GreetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_greeting, container, false);
        unbinder = ButterKnife.bind(this, view);

        showUserName();
        return view;
    }
    private void showUserName() {
        sharedPreferences=  getContext().getSharedPreferences("shred" , Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("username", "unknown");
                if (userName != null)
                tvUserGreeting.setText("Welcome " + userName + " !");

            }


       /* Toast.makeText(getContext(), userName, Toast.LENGTH_SHORT).show();
        tvUserGreeting.setText("Welcome " + userName + "! ");*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tvUserGreeting)
    public void onViewClicked() {
    }
}
