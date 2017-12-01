package com.example.android.vidmeclient.widgets.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.android.vidmeclient.common.Constants;
import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.model.entities.Video;
import com.example.android.vidmeclient.ui.activities.VideoPlayerActivity;

import java.util.ArrayList;
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
        this.videos = videos != null ? videos : new ArrayList<Video>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.videoName.setText(videos.get(position).getTitle());
        holder.videoLikesCount.setText(String.valueOf(videos.get(position).getLikesCount()));

        Glide
                .with(context)
                .load(videos.get(position).getThumbnailUrl())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.videoImage);

    }

    @Override
    public int getItemCount() {
        if (videos == null) return 0;
        return videos.size();
    }

    public void addVideos(List<Video> videos) {
        this.videos.addAll(videos);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_text)
        TextView videoName;
        @BindView(R.id.card_image)
        ImageView videoImage;
        @BindView(R.id.like_count)
        TextView videoLikesCount;
        @BindView(R.id.progress)
        ProgressBar progressBar;
        @BindView(R.id.action_button)
        Button playButton;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_video, parent, false));

            ButterKnife.bind(this, itemView);

            View.OnClickListener listener = v -> {
                Context context1 = v.getContext();
                try {
                    int index = getAdapterPosition();
                    Intent intent = new Intent(context1, VideoPlayerActivity.class);
                    intent.putExtra(Constants.VIDEO_ID, videos.get(index).getCompleteUrl());
                    context1.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(context1, "Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            };
            videoImage.setOnClickListener(listener);
            playButton.setOnClickListener(listener);
        }
    }
}
