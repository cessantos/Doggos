package cess.com.br.doggos.ui.contracts;

import java.util.List;

import cess.com.br.doggos.ui.BasePresenter;
import cess.com.br.doggos.ui.BaseView;

public interface GalleryContract {
    interface View extends BaseView<GalleryContract.Presenter> {
        void showLoader();
        void hideLoader();

        void showGallery();
        void hideGallery();

        void populateGallery(List<String> doggos);
    }

    interface Presenter extends BasePresenter {
        void getDoggosByCategory(String category, String token);
    }
}
