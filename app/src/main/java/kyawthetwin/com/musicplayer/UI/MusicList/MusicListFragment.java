package kyawthetwin.com.musicplayer.UI.MusicList;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;
import kyawthetwin.com.musicplayer.Adapter.MusicListAdapter;
import kyawthetwin.com.musicplayer.R;


public class MusicListFragment extends Fragment implements MusicListContract.View{


    @BindView(R.id.rcv_musicList)
    RecyclerView rcvMusicList;

    @Nonnull
    private MusicListContract.Presenter presenter;

    MusicListAdapter musicListAdapter;

    public MusicListFragment() {
        // Required empty public constructor
    }


    public static MusicListFragment newInstance() {
        MusicListFragment fragment = new MusicListFragment();
      /*  Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           /* mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
        ButterKnife.bind(this, view);

        init();

        return view;
    }

    private void init(){
        setMusicListRecyclerView();
        findSong();
    }



    private void setMusicListRecyclerView(){
        musicListAdapter = new MusicListAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rcvMusicList.setLayoutManager(mLayoutManager);
        rcvMusicList.setItemAnimator(new DefaultItemAnimator());
        rcvMusicList.setNestedScrollingEnabled(false);
        rcvMusicList.setAdapter(musicListAdapter);

    }

    private void findSong(){
        presenter.findSong(Environment.getExternalStorageDirectory());
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void showMusicFile(ArrayList<File> file) {
        if (file != null) {
            musicListAdapter.setMusicList(file);
        }

    }

    @Override
    public void setPresenter(MusicListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }
}
