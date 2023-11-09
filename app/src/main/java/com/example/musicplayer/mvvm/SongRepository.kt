package com.example.musicplayer.mvvm

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicplayer.model.Song

class SongRepository {

    fun getAudioFiles(context: Context) : LiveData<List<Song>>{

        val audiofilesLiveData = MutableLiveData<List<Song>>()

        val audioFiles = mutableListOf<Song>()

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_ID,

            MediaStore.Audio.Media.DATA,
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC}!= 0"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
        val cursor = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,sortOrder)

        cursor?.use {c->
            val idColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val durationColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val albumIdColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val dataColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (c.moveToNext()){

                val id = c.getLong(idColumn)
                val title = c.getString(titleColumn)
                val artist = c.getString(artistColumn)
                val duration = c.getLong(durationColumn)
                val albumId = c.getLong(albumIdColumn)
                val data = c.getString(dataColumn)
                val albumArtUri = getAlbumUri(context,albumId)
                val song = Song(id,title,artist,duration,data,albumArtUri?.toString())

                audioFiles.add(song)
            }
        }
        audiofilesLiveData.postValue(audioFiles)
        return audiofilesLiveData

    }

    private fun getAlbumUri(context: Context, albumId: Long): Uri? {

        val albumArtUri = Uri.parse("content://media/external/audio/albumart")

        return ContentUris.withAppendedId(albumArtUri,albumId)

    }
}