package kyawthetwin.com.musicplayer.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import kyawthetwin.com.musicplayer.R;
import kyawthetwin.com.musicplayer.Utils.ActivityUtils;

public class MusicPlayerActivity extends AppCompatActivity {


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MusicPlayerActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        init();
    }

    public void init() {
        ButterKnife.bind(this);

        MusicPlayerFragment musicPlayerFragment =
                (MusicPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (musicPlayerFragment == null) {
            musicPlayerFragment = MusicPlayerFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    musicPlayerFragment, R.id.content);
        }

    }
}
