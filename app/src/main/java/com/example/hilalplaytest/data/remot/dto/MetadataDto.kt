package com.example.hilalplaytest.data.remot.dto

import kotlinx.serialization.SerialName

data class MetadataDto(
    val createdAt: String,
    val id: String,
    @SerialName("private")
    val isPrivate: Boolean
)