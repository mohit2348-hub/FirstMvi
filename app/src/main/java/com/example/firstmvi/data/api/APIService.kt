package com.example.firstmvi.data.api

import com.example.firstmvi.data.model.FakeDTO
import retrofit2.http.GET

interface APIService {

   @GET("posts")
   suspend fun getPosts():List<FakeDTO>

}