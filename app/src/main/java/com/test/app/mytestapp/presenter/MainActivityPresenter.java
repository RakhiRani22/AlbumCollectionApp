package com.test.app.mytestapp.presenter;

import android.util.Log;
import com.test.app.mytestapp.Api;
import com.test.app.mytestapp.model.Album;
import com.test.app.mytestapp.model.DatabaseHandler;
import com.test.app.mytestapp.model.LocalDatabaseHandler;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPresenter implements DatabaseHandler.Presenter{
    private View view;
    private final String TAG = "MainActivityPresenter";

    public MainActivityPresenter(View view, DatabaseHandler databaseHandler) {
        this.view = view;
        databaseHandler.getAlbumsData(this);
    }

    @Override
    public void onDatabaseReceived(List<Album> albumList) {
        if(null != albumList && !albumList.isEmpty()) {
            //Sorting the data based on title name
            Collections.sort(albumList, albumNameComparator);
            view.hideProgressBar();
            view.updateAlbumsDataList(albumList);
        }
    }

    //Used to sort the albums based on title name
    private Comparator<Album> albumNameComparator = new Comparator<Album>() {

        public int compare(Album album1, Album album2) {
            String firstAlbumName = album1.getTitle().toUpperCase();
            String secondAlbumName = album2.getTitle().toUpperCase();
            return firstAlbumName.compareTo(secondAlbumName);
        }
    };

    @Override
    public void onErrorOccurred(String errorMessage) {
        view.hideProgressBar();
        Log.d(TAG,"onFailure message:"+errorMessage);
        view.onErrorOccurred(errorMessage);
    }

    public interface View{
        void updateAlbumsDataList(List<Album> albumsData);
        void showProgressBar();
        void hideProgressBar();
        void onErrorOccurred(String errorMessage);
    }
}
