package com.example.hilalplaytest.data.remot.api

import com.example.hilalplaytest.data.remot.dto.JsonbinResponseDto
import com.example.hilalplaytest.data.remot.network.GET_MENU
import retrofit2.http.GET


interface ApiService {

    @GET(GET_MENU)
    suspend fun  getMenu(): JsonbinResponseDto

}