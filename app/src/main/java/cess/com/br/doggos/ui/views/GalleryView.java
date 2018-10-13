package cess.com.br.doggos.ui.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cess.com.br.doggos.R;
import cess.com.br.doggos.models.cache.SharedPreferencesManager;
import cess.com.br.doggos.ui.adapters.GridDoggosRecyclerViewAdapter;
import cess.com.br.doggos.ui.contracts.GalleryContract;

public class GalleryView extends Fragment implements GalleryContract.View {

    @BindView(R.id.pb_doggos_gallery)
    ProgressBar mGalleryProgress;

    @BindView(R.id.rv_doggos_list)
    RecyclerView mDoggoGridList;

    private GalleryContract.Presenter mPresenter;
    private SharedPreferencesManager spm;

    @Override
    public void setPresenter(GalleryContract.Presenter loginPresenter) {
        mPresenter = loginPresenter;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spm = new SharedPreferencesManager(getActivity());

        hideGallery();
        showLoader();

        mPresenter.getDoggosByCategory("husky", spm.getStringValue("token", ""));
    }

    @Override
    public void populateGallery(List<String> doggos) {
        mDoggoGridList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        GridDoggosRecyclerViewAdapter adapter = new GridDoggosRecyclerViewAdapter(getContext(), doggos);
        mDoggoGridList.setAdapter(adapter);

        hideLoader();
        showGallery();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        hideGallery();
        showLoader();

        switch (item.getItemId()) {
            case R.id.mi_filter_husky:
                mPresenter.getDoggosByCategory("husky", spm.getStringValue("token", ""));
                return true;

            case R.id.mi_filter_hound:
                mPresenter.getDoggosByCategory("hound", spm.getStringValue("token", ""));
                return true;

            case R.id.mi_filter_pug:
                mPresenter.getDoggosByCategory("pug", spm.getStringValue("token", ""));
                return true;
            case R.id.mi_filter_labrador:
                mPresenter.getDoggosByCategory("labrador", spm.getStringValue("token", ""));
                return true;
            case R.id.mi_sign_off:

                SharedPreferencesManager spm = new SharedPreferencesManager(getContext());
                spm.removeStringValue("token");

                getActivity().finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoader() {
        mGalleryProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mGalleryProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showGallery() {
        mDoggoGridList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGallery() {
        mDoggoGridList.setVisibility(View.INVISIBLE);
    }
}
