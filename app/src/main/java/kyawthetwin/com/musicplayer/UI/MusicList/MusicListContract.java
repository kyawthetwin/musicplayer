package kyawthetwin.com.musicplayer.UI.MusicList;

import java.io.File;
import java.util.ArrayList;

import kyawthetwin.com.musicplayer.BasePresenter;
import kyawthetwin.com.musicplayer.BaseView;

/**
 * Created by kyawthetwin on 4/3/18.
 */

public class MusicListContract {

    interface View extends BaseView<Presenter> {

        void showMusicFile(ArrayList<File> file);
    }

    interface Presenter extends BasePresenter {

        ArrayList<File> findSong(File root);
    }
}
