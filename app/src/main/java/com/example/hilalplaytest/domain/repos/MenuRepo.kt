package com.example.hilalplaytest.domain.repos

import com.example.hilalplaytest.domain.model.Menu
import com.example.hilalplaytest.core.BaseResponse
import com.example.hilalplaytest.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuRepo {
    suspend fun getMenu(): Flow<BaseResponse<Menu>>
    suspend fun refreshMenu(): Flow<BaseResponse<Menu>>
    suspend fun updateItem(item: MenuItem): Flow<BaseResponse<MenuItem>>

}