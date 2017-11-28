package com.example.android.vidmeclient.widgets.listeners;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

/**
 * Created by fbrsw on 28.11.2017.
 */

public abstract class ElementsBarScrollListener extends RecyclerView.OnScrollListener {
    private int visibleThreshold = 0;
    private int previousTotal = 0;
    private boolean loading = true;

    private Context context;

    public ElementsBarScrollListener(Context context) {
        this.context = context;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if ((!loading) &&
                ((totalItemCount - visibleItemCount) <= (pastVisibleItems - visibleThreshold))) {
            loading = true;
            action();
        }
    }

    public abstract void action();

}
