package com.example.android.vidmeclient.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.vidmeclient.AppVidMe;
import com.example.android.vidmeclient.Constants;
import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.di.component.AppComponent;
import com.example.android.vidmeclient.di.component.DaggerPresentersComponent;
import com.example.android.vidmeclient.di.module.PresentersModule;
import com.example.android.vidmeclient.model.entities.Video;
import com.example.android.vidmeclient.presenters.NewDataPresenter;
import com.example.android.vidmeclient.views.NewContentView;
import com.example.android.vidmeclient.widgets.adapters.VideoContentAdapter;
import com.example.android.vidmeclient.widgets.listeners.ElementsBarScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class NewFragment extends BaseFragment implements NewContentView {

    private VideoContentAdapter adapter;

    private int offset = 0;

    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swiper;

    @Inject
    NewDataPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_content, container, false);
        ButterKnife.bind(this, view);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        swiper.setOnRefreshListener(this::loadFragmentSources);
        loadFragmentSources();
        return view;
    }

    @Override
    public void networkConnected() {
        if (adapter.getItemCount() <= offset)
            presenter.getNewContent(Constants.VIDEO_COUNT_LIMIT, offset);
        else showText(adapter.getItemCount() + " " + offset);
    }

    @Override
    public void showVideoContent(List<Video> videos) {
        adapter.addVideos(videos);
        adapter.notifyDataSetChanged();

        if (swiper.isRefreshing()) {
            swiper.setRefreshing(false);
        }
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    private void loadFragmentSources() {
        offset = 0;
        adapter = new VideoContentAdapter(getContext(), null);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setOnScrollListener(new ElementsBarScrollListener(getContext()) {
            @Override
            public void action() {
                offset += Constants.VIDEO_COUNT_LIMIT;
                presenter.getNewContent(Constants.VIDEO_COUNT_LIMIT, offset);
            }
        });

        presenter.setView(this);
        presenter.getNewContent(Constants.VIDEO_COUNT_LIMIT, 0);
    }


    public AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private AppVidMe getApp() {
        return (AppVidMe) getActivity().getApplication();
    }


    private void showText(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

}
