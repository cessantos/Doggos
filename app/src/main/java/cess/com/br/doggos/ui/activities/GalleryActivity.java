package cess.com.br.doggos.ui.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cess.com.br.doggos.R;
import cess.com.br.doggos.ui.presenters.GalleryPresenter;
import cess.com.br.doggos.ui.views.GalleryView;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GalleryView galleryView = new GalleryView();

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fl_gallery_fragment_holder, galleryView).commit();
            galleryView.setRetainInstance(true);
        }

        GalleryPresenter mPresenter = new GalleryPresenter(galleryView);

        galleryView.setPresenter(mPresenter);



    }
}
