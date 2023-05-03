package com.example.notes.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.notes.data.Note
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.Exception

class HomeViewModel(private val context: Context): ViewModel() {

    var noteList = mutableListOf<Note>()


    fun saveNotes() {
        val notes = noteList
        val gson = GsonBuilder().create()
        val jsonNotes = gson.toJson(notes)

        var writer: Writer? = null
        try {
            val out = context.openFileOutput(FILEPATH, Context.MODE_PRIVATE)

            writer = OutputStreamWriter(out)
            writer.write(jsonNotes)
        } catch(e: Exception) {
            writer?.close()
        } finally {
            writer?.close()
        }
    }

    fun retrieveNotes(): MutableList<Note> {
        var noteList = mutableListOf<Note>()
        if (context.getFileStreamPath(FILEPATH).isFile) {
            var reader: BufferedReader? = null

            try {
                val fileInput = context.openFileInput(FILEPATH)
                reader =  BufferedReader(InputStreamReader(fileInput))
                val stringBuilder = StringBuilder()

                for (line in reader.readLine()) stringBuilder.append(line)

                if (stringBuilder.isNotEmpty()) {
                    val listType = object : TypeToken<List<Note>>() {}.type
                    noteList = Gson().fromJson(stringBuilder.toString(), listType)
                }
            } catch(e: Exception) {
                reader?.close()
            } finally {
                reader?.close()
            }
        }
        return noteList
    }


    companion object {
        private const val FILEPATH = "notes.json"
    }
}