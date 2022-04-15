package dev.fonteles.mynotes.data.datasource

import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.model.User
import java.lang.Exception

class FakeLocalTokenDataSource (private var user: User? = null): ILocalTokenDataSource {

    override fun getToken(): FuncResult<Token> {
        user?.let {
            return FuncResult.Success(Token(it.username, it.username))
        }
        return FuncResult.Error(Exception("User not logged in"))
    }

    override fun saveToken(token: Token): Boolean {
        user = User(token.username, token.hash)
        return true
    }

    override fun deleteToken(): Boolean {
        user = null
        return true
    }
}