package com.example.android.vidmeclient.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.vidmeclient.AppVidMe;
import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.di.component.AppComponent;
import com.example.android.vidmeclient.di.component.DaggerPresentersComponent;
import com.example.android.vidmeclient.di.module.PresentersModule;
import com.example.android.vidmeclient.model.entities.Video;
import com.example.android.vidmeclient.presenters.FeaturedDataPresenter;
import com.example.android.vidmeclient.views.FeaturedContentView;
import com.example.android.vidmeclient.widgets.adapters.VideoContentAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class FeaturedFragment extends Fragment implements FeaturedContentView {

    private VideoContentAdapter adapter;


    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    @Inject
    FeaturedDataPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.featured_fragment, container, false);
        ButterKnife.bind(this, view);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        adapter = new VideoContentAdapter(getContext(), null);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.setView(this);
        presenter.getFeaturedContent(10, 0);
        return view;
    }

    @Override
    public void showVideoContents(List<Video> videos) {
        adapter.setVideos(videos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }


    public AppComponent getAppComponent() {
        return ((AppVidMe) getActivity().getApplication()).appComponent();
    }
}
