package cess.com.br.doggos.ui.presenters;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cess.com.br.doggos.api.RetrofitConfig;
import cess.com.br.doggos.api.RetrofitServices;
import cess.com.br.doggos.models.cache.SharedPreferencesManager;
import cess.com.br.doggos.models.remote.User;
import cess.com.br.doggos.ui.contracts.LoginContract;
import cess.com.br.doggos.ui.views.LoginView;
import retrofit2.Call;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import static cess.com.br.doggos.Constants.DB_NAME;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;

    public LoginPresenter(LoginView loginView) {
        mView = loginView;
    }

    @Override
    public void signUp(String email, final Context context) {

        Map<String, String> body = new HashMap<>();
        body.put("email", email);

        Observable<User> signUpRequest =
                new RetrofitConfig().retrofitServices().requestJson2(body);

        signUpRequest.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {

                    @Override
                    public void onCompleted() {
                        mView.navigateToGallery();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorSnackBar(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        storeUser(user, context);
                    }
                });
    }

    private void storeUser(final User user, Context context) {
        SharedPreferencesManager spm = new SharedPreferencesManager(context);

        spm.setStringValue("token",user.getUser().getToken());
        spm.setStringValue("email",user.getUser().getEmail());
    }

}
