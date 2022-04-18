package dev.fonteles.mynotes.data.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.model.Token
import java.lang.Exception
import javax.inject.Inject

class LocalTokenDataSource @Inject constructor(
    @ApplicationContext val appContext: Context
    ) : ILocalTokenDataSource {

//    companion object {
//        @Volatile
//        private var instance: ILocalTokenDataSource? = null
//
//        fun getInstance(app: Context): ILocalTokenDataSource {
//            return instance ?: synchronized(this) {
//                LocalTokenDataSource(app).also {
//                    instance = it
//                }
//            }
//        }
//    }

    private val PREF_NAME = "TOKEN"
    private val USERNAME = "username"
    private val HASH = "hash"

    override fun getToken(): FuncResult<Token> {
        val sharedPreference =  appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val username = sharedPreference.getString(USERNAME, "")
        val hash = sharedPreference.getString(HASH, "")

        if(username?.isEmpty() == false && hash?.isEmpty() == false) {
            return FuncResult.Success(Token(username, hash))
        }
        return FuncResult.Error(Exception("User not logged in"))
    }

    override fun saveToken(token: Token): Boolean {
        if(token.username.isBlank() || token.hash.isEmpty()) {
            throw Exception("Invalid token: $token")
        }
        val sharedPreference =  appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString(USERNAME, token.username)
        editor.putString(HASH, token.hash)

        return editor.commit()
    }

    override fun deleteToken(): Boolean {
        val sharedPreference =  appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.clear()

        return editor.commit()
    }

}