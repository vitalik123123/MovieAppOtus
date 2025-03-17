package com.vitalik123.movieappotus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.vitalik123.core.di.ViewModelFactory
import com.vitalik123.feature_home.presentation.HomeScreen
import com.vitalik123.movieappotus.app.App
import com.vitalik123.movieappotus.ui.theme.MovieAppOtusTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
//            MovieAppOtusTheme(
//                viewModelFactory = viewModelFactory
//            ) {
//                HomeScreen()
//            }
        }
    }
}