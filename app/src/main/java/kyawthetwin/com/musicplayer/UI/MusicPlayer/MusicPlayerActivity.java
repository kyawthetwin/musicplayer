package kyawthetwin.com.musicplayer.UI.MusicPlayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import kyawthetwin.com.musicplayer.R;
import kyawthetwin.com.musicplayer.Utils.ActivityUtils;

public class MusicPlayerActivity extends AppCompatActivity {

    private static final String EXTRA_SONG_POSTION = "kyawthetwin.com.musicplayer.extra_song_position";
    private static final String EXTRA_SONG_LIST = "kyawthetwin.com.musicplayer.extra_song_list";
    private int songListPosition;
    private ArrayList<File> songList;

    public static Intent newIntent(Context context, int songListPosition, ArrayList<File> songList) {
        Intent intent = new Intent(context, MusicPlayerActivity.class);
        intent.putExtra(EXTRA_SONG_LIST,songList);
        intent.putExtra(EXTRA_SONG_POSTION,songListPosition);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        extractExtras();
        MusicPlayerFragment musicPlayerFragment =
                (MusicPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (musicPlayerFragment == null) {
            musicPlayerFragment = MusicPlayerFragment.newInstance(songListPosition,songList);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    musicPlayerFragment, R.id.content);
        }

    }

    private void extractExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            songList = (ArrayList) extras.getParcelableArrayList(EXTRA_SONG_LIST);
            songListPosition = extras.getInt(EXTRA_SONG_POSTION, 0);
        }
    }
}
