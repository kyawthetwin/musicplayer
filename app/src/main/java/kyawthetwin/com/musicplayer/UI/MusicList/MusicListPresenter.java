package kyawthetwin.com.musicplayer.UI.MusicList;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kyawthetwin on 4/3/18.
 */

public class MusicListPresenter implements MusicListContract.Presenter {


    MusicListContract.View view;

    public MusicListPresenter(MusicListContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public ArrayList<File> findSong(File root) {
        ArrayList<File> musicFile = new ArrayList<>();
        File[] files = root.listFiles();
        for(File singleFile : files){
            if (singleFile.isDirectory() && !singleFile.isHidden()){

                musicFile.addAll(findSong(singleFile));

            }else {
                if (singleFile.getName().endsWith(".mp3")){

                    musicFile.add(singleFile);
                }

            }
        }
        view.showMusicFile(musicFile);
        return musicFile;

    }
}
