package com.bear.musicplayer.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import com.bear.musicplayer.R;
import com.bear.musicplayer.data.Music;

import java.util.List;

public class GetMusicInfo {

    // 扫描歌曲
    public static void scanMusic(Context context, List<Music> musicList){
        musicList.clear();
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        BaseColumns._ID,
                        MediaStore.Audio.AudioColumns.IS_MUSIC,
                        MediaStore.Audio.AudioColumns.TITLE,
                        MediaStore.Audio.AudioColumns.ARTIST,
                        MediaStore.Audio.AudioColumns.ALBUM,
                        MediaStore.Audio.AudioColumns.ALBUM_ID,
                        MediaStore.Audio.AudioColumns.DATA,
                        MediaStore.Audio.AudioColumns.DISPLAY_NAME,
                        MediaStore.Audio.AudioColumns.SIZE,
                        MediaStore.Audio.AudioColumns.DURATION
                }, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor == null){
            return;
        }
        while (cursor.moveToNext()){
            // 是否为音乐
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            if (isMusic == 1){
                Music music = new Music();
                long id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
                String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE)));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
                String album = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM)));
                long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));
                String fileName = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME)));
                long fileSize = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                music.setId(id);
                music.setTitle(title);
                music.setAritist(artist);
                music.setAlbum(album);
                music.setAlbumId(albumId);
                music.setDuration(duration);
                music.setPath(path);
                music.setFileName(fileName);
                music.setFileSize(fileSize);
                music.setDownload(1);
                music.setSongListName("本地音乐");
                musicList.add(music);
            }
        }
        cursor.close();
    }

    // 专辑id转bitmap
    public static Bitmap getAlbumArt(int albumId, Context content) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = content.getContentResolver().query(Uri.parse(mUriAlbums + "/" + albumId), projection, null, null, null);
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
            bm = BitmapFactory.decodeResource(content.getResources(), R.drawable.add);
        }
        return bm;
    }

}
