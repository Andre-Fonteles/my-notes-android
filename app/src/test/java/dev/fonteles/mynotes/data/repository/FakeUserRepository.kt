package dev.fonteles.mynotes.data.repository

import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.model.User
import java.lang.Exception

class FakeUserRepository(val users: MutableList<User>, var token: Token?): IUserRepository {

    override suspend fun login(username: String, password: String): FuncResult<Token> {
        for(u in users) {
            if(u.username == username && u.password == password) {
                val localToken = Token(username, "hash for $username")
                token = localToken
                return FuncResult.Success(localToken)
            }
        }
        return FuncResult.Error(Exception("Failure to log in"))
    }

    override suspend fun getLocalToken(): FuncResult<Token> {
        token?.let {
            return FuncResult.Success(it)
        }
        return FuncResult.Error(Exception("User not logged in"))
    }

}