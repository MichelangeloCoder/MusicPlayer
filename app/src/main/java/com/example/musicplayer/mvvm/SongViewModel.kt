package com.example.musicplayer.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.musicplayer.model.Song

class SongViewModel (private val repository: SongRepository) : ViewModel() {

    fun showtheList(context: Context): LiveData<List<Song>> {

        return repository.getAudioFiles(context)
    }
}