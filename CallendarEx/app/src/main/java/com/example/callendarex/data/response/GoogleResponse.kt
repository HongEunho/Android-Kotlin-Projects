package com.example.callendarex.data.response

import com.example.callendarex.data.entity.GoogleEntity

data class GoogleResponse(
    // todo response 아래의 코드는 예시
    val id: String,
    val createdAt: Long,
    val productName: String,
    val productPrice: String,
    val productImage: String,
    val productType: String,
    val productIntroductionImage: String
) {
    fun toEntity(): GoogleEntity =
        GoogleEntity(

        )
}
