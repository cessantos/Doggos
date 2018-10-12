package cess.com.br.doggos.ui.contracts;

import android.content.Context;

import cess.com.br.doggos.ui.BasePresenter;
import cess.com.br.doggos.ui.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showLoader();
        void hideLoader();

        void showLogin();
        void hideLogin();

        void navigateToGallery();
        void showErrorSnackBar(String message);
    }

    interface Presenter extends BasePresenter {
        void signUp (String email, Context context);
    }
}
