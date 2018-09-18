package kyawthetwin.com.musicplayer.UI.MusicPlayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import kyawthetwin.com.musicplayer.R;


public class MusicPlayerFragment extends Fragment {


    private static final String ARG_SONG_LIST_POSITION = "ARG_SONG_LIST_POSITION";
    private static final String ARG_SONG_LIST = "ARG_SONG_LIST";

    private int songListPosition;
    private ArrayList<File> songList;
    static MediaPlayer mediaPlayer;

    @BindView(R.id.txtFirstSeek)
    TextView txtFirstSeek;
    @BindView(R.id.txtLastSeek)
    TextView txtLastSeek;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.songSeekBar)
    SeekBar songSeekBar;

    Thread updateSongSeekBar;
    int totalSongDuration,currentSongPosition;
    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_player, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public static MusicPlayerFragment newInstance(int songListPosition, ArrayList<File> songList) {
        MusicPlayerFragment fragment = new MusicPlayerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SONG_LIST_POSITION, songListPosition);
        args.putSerializable(ARG_SONG_LIST, songList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            songList = (ArrayList<File>) savedInstanceState.getSerializable(ARG_SONG_LIST);
            songListPosition = savedInstanceState.getInt(ARG_SONG_LIST_POSITION);

        } else {
            songList = (ArrayList<File>) getArguments().getSerializable(ARG_SONG_LIST);
            songListPosition = getArguments().getInt(ARG_SONG_LIST_POSITION);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_SONG_LIST, songList);
        outState.putInt(ARG_SONG_LIST_POSITION, songListPosition);
    }

    private void init() {

        createMediaPlayer();
        setSongSeekBar();
        setSongSeekBarListener();
    }

    private void createMediaPlayer() {
        checkMediaPlayer();
        Uri u = Uri.parse(songList.get(songListPosition).toString());
        mediaPlayer = MediaPlayer.create(getActivity(), u);

    }

    private void setSongSeekBar(){

        songSeekBar.setMax(mediaPlayer.getDuration());
        updateSongSeekBar = new Thread(){
            @Override
            public void run() {
                 totalSongDuration = mediaPlayer.getDuration();
                 currentSongPosition = 0;
                while (currentSongPosition < totalSongDuration){
                    try {
                        sleep(500);
                        currentSongPosition = mediaPlayer.getCurrentPosition();
                        songSeekBar.setProgress(currentSongPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

    }

    private void setSongSeekBarListener(){

        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void mediaStart() {

        mediaPlayer.start();
        btnPlay.setText("PAUSE");
    }

    private void mediaPause() {

        mediaPlayer.pause();
        btnPlay.setText("PLAY");
    }

    @OnClick(R.id.btnPlay)
    public void mediaPlayAndPause() {

        checkMediaPlaying();

    }

    @OnClick(R.id.btnNext)
    public void clickNext() {
        mediaPlayer.stop();
        mediaPlayer.release();
        songListPosition = (songListPosition + 1) % songList.size();
        Uri u = Uri.parse(songList.get(songListPosition).toString());
        mediaPlayer = MediaPlayer.create(getActivity(), u);
        mediaPlayer.start();
        songSeekBar.setMax(mediaPlayer.getDuration());
    }

    @OnLongClick(R.id.btnNext)
    public boolean forwardSong(){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);

        return true;
    }

    @OnLongClick(R.id.btnPre)
    public boolean backwardSong(){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);

        return true;
    }

    @OnClick(R.id.btnPre)
    public void clickPrecious() {
        mediaPlayer.stop();
        mediaPlayer.release();
        songListPosition = (songListPosition - 1 < 0) ? songList.size() - 1 : songListPosition - 1;
        Uri u = Uri.parse(songList.get(songListPosition).toString());
        mediaPlayer = MediaPlayer.create(getActivity(), u);
        mediaPlayer.start();
        songSeekBar.setMax(mediaPlayer.getDuration());
    }

    private void checkMediaPlayer() {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void checkMediaPlaying() {

        if (!mediaPlayer.isPlaying()) {
            mediaStart();

        } else {
            mediaPause();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        checkMediaPlaying();
        updateSongSeekBar.start();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
