package kyawthetwin.com.musicplayer.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kyawthetwin.com.musicplayer.R;
import kyawthetwin.com.musicplayer.UI.MusicList.MusicListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnMusicList)
    public void goToMusicList() {
        startActivity(MusicListActivity.newIntent(getApplicationContext()));
    }
}
