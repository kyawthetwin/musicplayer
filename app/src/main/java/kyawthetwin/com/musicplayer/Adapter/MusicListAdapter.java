package kyawthetwin.com.musicplayer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kyawthetwin.com.musicplayer.R;
import kyawthetwin.com.musicplayer.UI.MusicPlayer.MusicPlayerActivity;

/**
 * Created by kyawthetwin on 4/3/18.
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MyViewHolder> {

    ArrayList<File> musicList;

    Context context;

    public MusicListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MusicListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_music_item, parent, false);

        return new MyViewHolder(itemView);
    }

    public void setMusicList(ArrayList<File> musicList) {
        this.musicList = musicList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.MyViewHolder holder, int position) {

        holder.txtSongName.setText(musicList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return musicList != null ? musicList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_songName)
        TextView txtSongName;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(MusicPlayerActivity.newIntent(context,getAdapterPosition(),musicList));
        }
    }
}
