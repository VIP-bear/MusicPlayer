package com.bear.musicplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bear.musicplayer.data.SongList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder>
        implements View.OnClickListener{

    private static final String TAG = "SongListAdapter";

    private Context mContext;

    private FragmentActivity fragmentActivity;

    private List<SongList> mSongList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView songListImage;
        TextView songListName;
        TextView songListInfo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.songListImage = itemView.findViewById(R.id.songlist_image);
            this.songListName = itemView.findViewById(R.id.songlist_name);
            this.songListInfo = itemView.findViewById(R.id.songlist_info);
        }
    }

    public SongListAdapter(List<SongList> mSongList, FragmentActivity fragmentActivity) {
        this.mSongList = mSongList;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null){
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.songlist_item,
                viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        // item的点击监听器
        holder.songListImage.setOnClickListener(this);
        holder.songListInfo.setOnClickListener(this);
        holder.songListName.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SongList songList = mSongList.get(i);
        viewHolder.songListImage.setImageBitmap(getAlbumArt((int)songList.getImageId()));
        viewHolder.songListName.setText(songList.getName());
        viewHolder.songListInfo.setText(songList.getInfo());
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.songlist_image:
            case R.id.songlist_info:
            case R.id.songlist_name:
                Intent intent = new Intent(fragmentActivity, SongListActivity.class);
                fragmentActivity.startActivity(intent);
                break;
                default:
        }
    }

    private Bitmap getAlbumArt(int albumId) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = fragmentActivity.getContentResolver().query(Uri.parse(mUriAlbums + "/" + Integer.toString(albumId)), projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        Bitmap bm = null;
        if (album_art != null) {
            bm = BitmapFactory.decodeFile(album_art);
        } else {
            bm = BitmapFactory.decodeResource(fragmentActivity.getResources(), R.drawable.add);
        }
        return bm;
    }
}
