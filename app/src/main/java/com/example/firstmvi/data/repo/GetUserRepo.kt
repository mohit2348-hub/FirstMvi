package com.example.firstmvi.data.repo

import com.example.firstmvi.data.api.APIService

class GetUserRepo(val apiService: APIService) {

    suspend fun getPosts()=apiService.getPosts()


}