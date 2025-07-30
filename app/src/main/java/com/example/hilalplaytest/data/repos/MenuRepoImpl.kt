package com.example.hilalplaytest.data.repos

import com.example.hilalplaytest.core.BaseRepo
import com.example.hilalplaytest.data.local.MenuDao
import com.example.hilalplaytest.data.mapper.MenuMapper
import com.example.hilalplaytest.data.mapper.MenuMapper.toEntities
import com.example.hilalplaytest.data.remot.api.ApiService
import com.example.hilalplaytest.domain.model.Menu
import com.example.hilalplaytest.domain.repos.MenuRepo
import com.example.hilalplaytest.core.BaseResponse
import com.example.hilalplaytest.data.mapper.toDomain
import com.example.hilalplaytest.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuRepoImpl
@Inject constructor(
    private val apiService: ApiService,
    private val menuMapper: MenuMapper,
    private val menuDao: MenuDao
) : BaseRepo(), MenuRepo {
    override suspend fun getMenu(): Flow<BaseResponse<Menu>> {
        return buildTask {
            val localItems = menuDao.getAllRows()
            if (localItems.isNotEmpty()) {
                return@buildTask menuMapper.toDomain(localItems)
            }
            fetchMenu()
        }
    }

    override suspend fun refreshMenu(): Flow<BaseResponse<Menu>> {
        return flow {
            emit(BaseResponse.Loading)
            try {
                val responseDto = apiService.getMenu()
                menuDao.addRows(responseDto.record.menu.toEntities())
                val updatedItems = menuDao.getAllRows()
                emit(BaseResponse.Success(menuMapper.toDomain(updatedItems)))
            } catch (e: Exception) {
                val localItems = menuDao.getAllRows()
                if (localItems.isNotEmpty()) {
                    emit(BaseResponse.Success(menuMapper.toDomain(localItems)))
                } else {
                    emit(BaseResponse.Error(e))
                }
            }
        }
    }

    override suspend fun updateItem(item: MenuItem): Flow<BaseResponse<MenuItem>> {
        return buildTask {
            menuDao.updateFavoriteState(item.id, item.isFavorite)
            val updatedItem = menuDao.getItemRow(id = item.id)
            updatedItem.toDomain()
        }
    }


    private suspend fun fetchMenu(): Menu {
        val responseDto = apiService.getMenu()
        menuDao.addRows(responseDto.record.menu.toEntities())
        val updatedItems = menuDao.getAllRows()
        return menuMapper.toDomain(updatedItems)
    }
}
