package dev.fonteles.mynotes.data.datasource

import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.model.User
import java.lang.Exception

class LoginRemoteDataSource: ILoginDataSource {

    val loginApi = ILoginApi.getInstance()

    override suspend fun login(user: User): FuncResult<Token> {
        try {
            val token = loginApi.login(user)
            return FuncResult.Success(token)
        } catch (e: Exception) {
            return FuncResult.Error(e)
        }
    }

}