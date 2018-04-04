package kyawthetwin.com.musicplayer.UI.MusicList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import kyawthetwin.com.musicplayer.R;
import kyawthetwin.com.musicplayer.Utils.ActivityUtils;

public class MusicListActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MusicListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        init();


    }

    private void init() {
        ButterKnife.bind(this);

        MusicListFragment musicListFragment =
                (MusicListFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (musicListFragment == null) {
            musicListFragment = MusicListFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    musicListFragment, R.id.content);
        }

        new MusicListPresenter(musicListFragment);
    }

}
