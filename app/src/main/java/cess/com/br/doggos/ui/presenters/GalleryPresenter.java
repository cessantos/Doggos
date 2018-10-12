package cess.com.br.doggos.ui.presenters;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import cess.com.br.doggos.api.RetrofitConfig;
import cess.com.br.doggos.models.cache.SharedPreferencesManager;
import cess.com.br.doggos.models.remote.Doggo;
import cess.com.br.doggos.models.remote.User;
import cess.com.br.doggos.ui.contracts.GalleryContract;
import cess.com.br.doggos.ui.contracts.LoginContract;
import cess.com.br.doggos.ui.views.LoginView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GalleryPresenter implements GalleryContract.Presenter {

    private final GalleryContract.View mView;

    public GalleryPresenter(GalleryContract.View galleryContract) {
        mView = galleryContract;
    }

    @Override
    public void getDoggosByCategory(String category, String token) {
        Observable<Doggo> doggoFeedRequest =
                new RetrofitConfig().retrofitServices().getDoggosByCategory(token, category);


        doggoFeedRequest.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Doggo>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Doggo doggo) {
                        mView.populateGallery(doggo.getDoggoPictureList());
                    }
                });
    }
}
