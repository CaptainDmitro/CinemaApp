package ru.captaindmitro.cinemaapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ru.captaindmitro.cinemaapp.ui.theme.CinemaAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaAppTheme {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    CinemaAppTheme {
        MainScreen()
    }
}