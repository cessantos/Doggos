package cess.com.br.doggos.ui.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cess.com.br.doggos.R;
import cess.com.br.doggos.ui.contracts.LoginContract;

public class LoginView extends Fragment implements LoginContract.View {

    @BindView(R.id.bt_signin)
    Button mButtonSignIn;

    @BindView(R.id.et_username)
    EditText mEditTextUsername;

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
