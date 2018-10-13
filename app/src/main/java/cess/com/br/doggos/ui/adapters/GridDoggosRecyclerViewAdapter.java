package cess.com.br.doggos.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import cess.com.br.doggos.GlideApp;
import cess.com.br.doggos.R;

public class GridDoggosRecyclerViewAdapter
        extends RecyclerView.Adapter<GridDoggosRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private Context context;

    public GridDoggosRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GlideApp
                .with(context)
                .load(mData.get(position))
                .centerCrop()
                .override(250, 250)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_doggo_image);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                            .title(" ")
                            .customView(R.layout.dialog_full_image, false)
                            .positiveText("ok")
                            .show();

                    final ProgressBar progressBar =
                            materialDialog.getCustomView().findViewById(R.id.pb_dialog_image_loader);
                    final ImageView imageLoadError =
                            materialDialog.getCustomView().findViewById(R.id.iv_load_error);

                    GlideApp
                            .with(context)
                            .load(getDoggoImgUrl(getAdapterPosition()))
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e,
                                                            Object model,
                                                            Target<Drawable> target,
                                                            boolean isFirstResource) {
                                    imageLoadError.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource,
                                                               Object model,
                                                               Target<Drawable> target,
                                                               DataSource dataSource,
                                                               boolean isFirstResource) {
                                    progressBar.setVisibility(View.GONE);
                                    return false;
                                }
                            })
                            .timeout(15)
                            .into((ImageView) materialDialog.getCustomView().findViewById(R.id.iv_doggo_full_image));
                }
            });
        }
    }

    private String getDoggoImgUrl(int id) {
        return mData.get(id);
    }
}