package com.vitalik123.ui_kit.extentions

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey

inline fun <T : Any> LazyGridScope.itemsPaging(
    items: LazyPagingItems<T>,
    noinline key: ((item: T) -> Any)? = null,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T) -> Unit
) = items(
    count = items.itemCount,
    key = if (key != null)  items.itemKey {
        key(it)
    }  else null
) {
    val item = items[it] ?: return@items
    itemContent(item)
}