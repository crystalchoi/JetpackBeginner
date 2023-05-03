package com.example.notes.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.notes.R
import com.example.notes.ui.theme.NewNote
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val menuItems = listOf("Item #1", "Item #2")
    val scaffoldState = rememberScaffoldState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            MainBottomBar(navController)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(
                onClick = {
                    // show snackbar as a suspend function
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Snackbar # ${++clickCount}"
                        )
                    }
                }
            ) { Text("Show snackbar") }
        },

    ) {

        NavHost(
            navController = navController, startDestination = "home",
            modifier = Modifier.padding(it)
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("newNote") {
                NewNote(navController)
            }
            composable("settings") {
                Settings(navController)
            }

        }
    }
}


@Composable
fun Settings(navController: NavController) {

}

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = HomeViewModel(LocalContext.current)) {

    LazyColumn {
        items(viewModel.noteList) {note ->
            Text(note.title)
        }
    }

    FloatingActionButton(
        onClick = {
            //OnClick Method
        },
        containerColor = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(16.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add FAB",
            tint = Color.White,
        )
    }
}




@Composable
fun MainBottomBar(navController: NavHostController) {
    BottomNavigation(modifier = Modifier.heightIn(min = 64.dp, max = 128.dp),
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        NoteNavigation.screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(text = stringResource(id = screen.label)
//                        , color = Color.White
                        , color = MaterialTheme.colorScheme.primary
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.label),
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .size(24.dp)
                    )
                },
                alwaysShowLabel = false,
            )
        }
    }
}


sealed class NoteNavigation(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    companion object {
        val screens = listOf(
            Home,
            NewNote,
            Settings
        )

        const val route_home = "home"
        const val route_newNote = "NewNote"
        const val route_settings = "settings"
    }

    private object Home : NoteNavigation(
        route_home,
        R.string.home,
        R.drawable.ic_home
    )

    private object NewNote : NoteNavigation(
        route_newNote,
        R.string.newNote,
        R.drawable.ic_note_add
    )
    private object Settings : NoteNavigation(
        route_settings,
        R.string.settings,
        R.drawable.ic_settings
    )
}
