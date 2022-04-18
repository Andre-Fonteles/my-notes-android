package dev.fonteles.mynotes.data.repository

import android.content.Context
import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.datasource.ILocalTokenDataSource
import dev.fonteles.mynotes.data.datasource.ILoginDataSource
import dev.fonteles.mynotes.data.datasource.LocalTokenDataSource
import dev.fonteles.mynotes.data.datasource.LoginRemoteDataSource
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val loginRemoteDataSource: ILoginDataSource,
    private val localTokenDataSource: ILocalTokenDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): IUserRepository {

    override suspend fun login(username: String, password: String) : FuncResult<Token> =
        withContext(ioDispatcher) {
            val tokenRes = loginRemoteDataSource.login(User(username, password))
            if(tokenRes is FuncResult.Success) {
                if(!localTokenDataSource.saveToken(tokenRes.data))
                    return@withContext FuncResult.Error(Exception("Failure to save token locally"))
            }
            return@withContext tokenRes
        }

    override suspend fun getLocalToken(): FuncResult<Token> {
        return localTokenDataSource.getToken()
    }

}