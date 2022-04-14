package dev.fonteles.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.datasource.ILoginDataSource
import dev.fonteles.mynotes.data.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            launch {
                val userRep = UserRepository.getInstance()
                val result = userRep.login("adminadmin", "adminadmin")

                if(result is FuncResult.Success) {
                    Log.i("Token", result.data.username)
                } else if(result is FuncResult.Error) {
                    Log.i("Token", result.exception.message.toString())
                    Log.i("Token", result.exception.toString())
                    val e = result.exception
                    if(e is retrofit2.HttpException) {
                        Log.i("My Error Rocks", e.response()?.code().toString())
                    }
                    result.exception.printStackTrace()
                }

            }
        }
    }
}