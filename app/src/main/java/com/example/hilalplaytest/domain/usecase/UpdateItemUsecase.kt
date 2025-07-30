package com.example.hilalplaytest.domain.usecase

import com.example.hilalplaytest.core.BaseResponse
import com.example.hilalplaytest.domain.model.MenuItem
import com.example.hilalplaytest.domain.repos.MenuRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateItemUsecase @Inject constructor(
    private val repo: MenuRepo
) {
    suspend operator fun invoke(item: MenuItem): Flow<BaseResponse<MenuItem>> = repo.updateItem(item)

}