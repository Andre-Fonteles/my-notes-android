package dev.fonteles.mynotes.data.datasource

import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.model.User
import java.lang.Exception

class FakeLoginDataSource (var users: MutableList<User>): ILoginDataSource {

    override suspend fun login(user: User): FuncResult<Token> {
        for (u in users) {
            if(u.username == user.username && u.password == user.password) {
                return FuncResult.Success(Token(u.username, u.username, ""))
            }
        }
        return FuncResult.Error(Exception("Failure to log in"))
    }

}