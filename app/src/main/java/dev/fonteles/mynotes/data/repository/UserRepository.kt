package dev.fonteles.mynotes.data.repository

import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.datasource.ILoginDataSource
import dev.fonteles.mynotes.data.datasource.LoginRemoteDataSource
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.model.User

class UserRepository (val loginRemoteDataSource: ILoginDataSource): IUserRepository {

    companion object {
        @Volatile
        private var instance: IUserRepository? = null

        fun getInstance(): IUserRepository {
            return instance ?: synchronized(this) {
                UserRepository(LoginRemoteDataSource()).also {
                    instance = it
                }
            }
        }
    }

    override suspend fun login(username: String, password: String) : FuncResult<Token> {
        return loginRemoteDataSource.login(User(username, password))
    }

}