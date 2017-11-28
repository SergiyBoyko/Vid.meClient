package com.example.android.vidmeclient.widgets.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.model.entities.Video;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fbrsw on 28.11.2017.
 */

public class VideoContentAdapter extends RecyclerView.Adapter<VideoContentAdapter.ViewHolder> {

    private Context context;

    private List<Video> videos = null;

    public VideoContentAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (videos == null) return 0;
        return videos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_text)
        TextView videoName;
        @BindView(R.id.card_image)
        ImageView videoImage;
        @BindView(R.id.favourite_count)
        TextView videoLikeCount;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_video, parent, false));

            ButterKnife.bind(this, itemView);
        }
    }
}
