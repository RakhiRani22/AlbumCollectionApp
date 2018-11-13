package com.test.app.mytestapp.view;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.test.app.mytestapp.model.DatabaseHandler;
import com.test.app.mytestapp.presenter.MainActivityPresenter;
import com.test.app.mytestapp.R;
import com.test.app.mytestapp.model.Album;
import com.test.app.mytestapp.model.AlbumsAdapter;
import com.test.app.mytestapp.model.LocalDatabaseHandler;
import java.util.List;

public class MainActivity extends Activity implements MainActivityPresenter.View {
    private RecyclerView listView;
    private MainActivityPresenter presenter;
    private ProgressBar progressBar;
    private AlbumsAdapter mAdapter;
    private LocalDatabaseHandler localDatabaseHandler;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (RecyclerView) findViewById(R.id.listViewAlbums);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());

        localDatabaseHandler = new LocalDatabaseHandler(this);
        databaseHandler = new DatabaseHandler(localDatabaseHandler);
        presenter = new MainActivityPresenter(this, databaseHandler);
        initProgressBar();
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        progressBar.setId(R.id.progress_bar_view);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,
                Resources.getSystem().getDisplayMetrics().heightPixels);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
        showProgressBar();
    }

    @Override
    public void updateAlbumsDataList(List<Album> albumsData) {
        mAdapter = new AlbumsAdapter(albumsData);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onErrorOccurred(String errorMessage) {
        Toast.makeText(getApplicationContext(), "Error:"+errorMessage, Toast.LENGTH_LONG).show();
    }
}
