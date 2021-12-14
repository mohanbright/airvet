package com.app.airvet.repository

import com.app.airvet.api.ApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getRandomUsers(page: Int, maxCount: Int, gender: String) =
        apiService.getRandomUsers(page, maxCount, gender)
}