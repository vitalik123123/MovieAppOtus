package com.vitalik123.feature_search.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vitalik123.core.R
import com.vitalik123.ui_kit.theme.LocalColor

@Composable
fun SearchElement(
    searchText: String,
    changeTextSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = searchText,
        placeholder = {
            Text(text = "Поиск")
        },
        onValueChange = changeTextSearch,
        trailingIcon = {
            Icon(
                contentDescription = null,
                modifier = Modifier
                    .padding(6.dp)
                    .clickable {
                        changeTextSearch.invoke("").apply {
                            focusManager.clearFocus()
                        }
                    },
                painter = painterResource(id = R.drawable.ic_close),
                tint = LocalColor.current.blackAlways
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        )
    )
}
