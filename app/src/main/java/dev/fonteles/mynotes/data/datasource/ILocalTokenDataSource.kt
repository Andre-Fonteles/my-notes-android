package dev.fonteles.mynotes.data.datasource

import android.app.Application
import dev.fonteles.mynotes.data.model.Token

interface ILocalTokenDataSource {
    fun getToken(): Token?
    fun saveToken(token: Token): Boolean
    fun deleteToken(): Boolean
}