package com.example.notes.ui.theme

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.notes.data.Note

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
//@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun NewNote(navController: NavController) {

//    NotesTheme() {
        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ) {
            var openDialog by remember { mutableStateOf(false)  }
            var title by remember { mutableStateOf("") }
            var content by remember { mutableStateOf("") }

            OutlinedTextField(value = title,
                label = { Text("title")},
                onValueChange = {
                title = it
            })

            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = content,
                label = { Text("content")},
                onValueChange = {
                content = it
            })

            OutlinedButton(onClick = {
                openDialog = true
            }) {
                Text("Save")
            }

            if (openDialog) {
                val context = LocalContext.current
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog = false
                    },
                    title = {
                        Text(text = "Save ? $title")
                    },
                    text = {
                        Text("$title ")
                    },
                    confirmButton = {
                        Button(onClick = {
                                openDialog = false
                                if (title.isNotBlank() && content.isNotBlank()) {
                                    val note = Note(title, content)

                                    Toast.makeText(
                                        context,
                                        "Saved successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else {
                                    // TODO:
                                }
                            }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                openDialog = false
                            }) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        }

//    }
}
