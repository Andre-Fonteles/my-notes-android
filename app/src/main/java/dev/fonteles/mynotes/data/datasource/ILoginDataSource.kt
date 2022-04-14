package dev.fonteles.mynotes.data.datasource

import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ILoginDataSource {

    suspend fun login(user: User): FuncResult<Token>

}