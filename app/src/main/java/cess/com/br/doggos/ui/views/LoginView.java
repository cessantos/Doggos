package cess.com.br.doggos.ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cess.com.br.doggos.R;
import cess.com.br.doggos.ui.activities.GalleryActivity;
import cess.com.br.doggos.ui.contracts.LoginContract;

public class LoginView extends Fragment implements LoginContract.View {

    @BindView(R.id.bt_signin)
    Button mButtonSignIn;

    @BindView(R.id.et_username)
    EditText mEditTextUsername;

    @BindView(R.id.ll_signin_holder)
    LinearLayout mSignInHolder;

    @BindView(R.id.pb_signin_loader)
    ProgressBar mSignInProgress;

    @BindView(R.id.rl_root_layout)
    RelativeLayout mRootLayout;

    private LoginContract.Presenter mPresenter;

    @Override
    public void setPresenter(LoginContract.Presenter loginPresenter) {
        mPresenter = loginPresenter;
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

        mButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideLogin();
                showLoader();

                mPresenter.signUp(mEditTextUsername.getText().toString(), getActivity());
            }
        });

    }

    @Override
    public void navigateToGallery() {
        showLogin();
        hideLoader();

        Intent intent = new Intent(getActivity(),GalleryActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorSnackBar(String message) {
        showLogin();
        hideLoader();

        Snackbar snackbar = Snackbar.make(mRootLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showLoader() {
        mSignInProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mSignInProgress.setVisibility(View.GONE);
    }

    @Override
    public void showLogin() {
        mSignInHolder.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLogin() {
        mSignInHolder.setVisibility(View.GONE);
    }
}
