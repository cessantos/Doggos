package cess.com.br.doggos.ui.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cess.com.br.doggos.R;
import cess.com.br.doggos.ui.presenters.LoginPresenter;
import cess.com.br.doggos.ui.views.LoginView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        LoginView loginView = new LoginView();

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fl_login_fragment_holder, loginView).commit();
            loginView.setRetainInstance(true);
        }

        LoginPresenter mPresenter = new LoginPresenter(loginView);

        loginView.setPresenter(mPresenter);

    }
}
