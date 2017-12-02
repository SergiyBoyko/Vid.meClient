package com.example.android.vidmeclient.widgets.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

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
    private ViewHolder currentlyPlayed = null;

    private SimpleExoPlayer player;

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

        stopPlaying();
        currentlyPlayed = null;
    }

    @Override
    public int getItemCount() {
        if (videos == null) return 0;
        return videos.size();
    }

    private MediaSource prepareMediaSource(String video) {
        Uri videoUri = Uri.parse(video);

        //Produces DataSource instances through which media data is loaded.
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Vid.meClientAdapter"), null);
        //Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        //FOR LIVESTREAM LINK:
        MediaSource videoSource = new HlsMediaSource(videoUri, dataSourceFactory, 1, null, null);

        return new LoopingMediaSource(videoSource);
    }

    public void stopPlaying() {
        if (currentlyPlayed != null) {
            player.stop();
            player.release();
            currentlyPlayed.videoImage.setVisibility(View.VISIBLE);
            currentlyPlayed.exoPlayerView.setVisibility(View.INVISIBLE);
        }
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
        @BindView(R.id.muted_video)
        SimpleExoPlayerView exoPlayerView;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_video, parent, false));

            ButterKnife.bind(this, itemView);

            View.OnClickListener listener = v -> {
                if (currentlyPlayed != null) {
                    stopPlaying();
                }
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

            playButton.setOnClickListener(v -> {
                if (currentlyPlayed != null) {
                    stopPlaying();
                    if (currentlyPlayed == this) {
                        currentlyPlayed = null;
                        return;
                    }
                }
                videoImage.setVisibility(View.INVISIBLE);
                exoPlayerView.setVisibility(View.VISIBLE);
                // 1. Create a default TrackSelector
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
                TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

                // 2. Create a default LoadControl
                LoadControl loadControl = new DefaultLoadControl();
                player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

                // Bind the player to the view.
                exoPlayerView.setPlayer(player);
                player.setVolume(0);
                player.prepare(prepareMediaSource(videos.get(getAdapterPosition()).getCompleteUrl()));
                player.setPlayWhenReady(true); //run file/link when ready to play.
                currentlyPlayed = ViewHolder.this;
            });

            videoImage.setOnClickListener(listener);

        }
    }
}
