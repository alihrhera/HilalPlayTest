package com.example.util

import kotlinx.coroutines.flow.StateFlow


interface NetworkMonitor {
    val networkStatus: StateFlow<Boolean>

}