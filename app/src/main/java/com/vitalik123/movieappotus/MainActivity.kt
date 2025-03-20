package com.vitalik123.movieappotus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import com.vitalik123.core_api.CoreFacadeComponentProviders
import com.vitalik123.database.di.RoomFacadeComponentProviders
import com.vitalik123.feature_details.presentation.DetailsScreen
import com.vitalik123.movieappotus.app.App
import com.vitalik123.movieappotus.navigation.NavGraph
import com.vitalik123.network.di.NetworkFacadeComponentProviders
import com.vitalik123.ui_kit.theme.MovieAppOtusTheme

class MainActivity : ComponentActivity() {

    val coreComponent: CoreFacadeComponentProviders by lazy {
        (application as App).coreFacadeComponent
    }

    val networkComponent: NetworkFacadeComponentProviders by lazy {
        (application as App).networkFacadeComponent
    }

    val roomComponent: RoomFacadeComponentProviders by lazy {
        (application as App).roomFacadeComponent
    }


    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MovieAppOtusTheme {
                NavGraph(
                    coreComponent = coreComponent,
                    networkComponent = networkComponent,
                    roomComponent = roomComponent,
                    modifier = Modifier.statusBarsPadding()
                )
            }
        }
    }
}