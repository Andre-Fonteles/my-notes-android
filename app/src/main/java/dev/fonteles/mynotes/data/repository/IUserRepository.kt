package dev.fonteles.mynotes.data.repository

import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.datasource.ILoginDataSource
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.model.User

interface IUserRepository {

    suspend fun login(username: String, password: String) : FuncResult<Token>

}