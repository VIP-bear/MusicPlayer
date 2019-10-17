package com.bear.musicplayer.data;

import org.litepal.crud.LitePalSupport;

public class Music extends LitePalSupport {

    private long id;            // 音乐id

    private String title;       // 歌名

    private String aritist;     // 歌手

    private String album;       // 专辑

    private long albumId;       // 专辑id

    private long duration;      // 持续时间

    private String path;        // 音乐文件路径

    private String fileName;    // 音乐文件名

    private long fileSize;      // 音乐文件大小

    private int download;       // 是否下载

    private String songListName;    // 加入的歌单名

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAritist() {
        return aritist;
    }

    public void setAritist(String aritist) {
        this.aritist = aritist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }
}