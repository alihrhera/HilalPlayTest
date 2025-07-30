package com.example.hilalplaytest.domain.usecase

import com.example.hilalplaytest.domain.model.Menu
import com.example.hilalplaytest.domain.repos.MenuRepo
import com.example.hilalplaytest.core.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuUsecase @Inject constructor(
    private val repo: MenuRepo
) {
    suspend operator fun invoke(): Flow<BaseResponse<Menu>> = repo.getMenu()

}