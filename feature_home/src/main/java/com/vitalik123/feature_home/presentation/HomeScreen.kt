package com.vitalik123.feature_home.presentation

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vitalik123.feature_home.view_model.FeatureHomeViewModel
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun HomeScreen() {

    val viewModel: FeatureHomeViewModel = viewModel()
    val state = viewModel.state.asStateFlow()
    val title = remember(state) { state.value.film?.title }

    Text(text = title.orEmpty(), modifier = Modifier.padding(50.dp))
}