package dev.fonteles.mynotes.data.repository

import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.datasource.FakeLoginDataSource
import dev.fonteles.mynotes.data.datasource.ILoginDataSource
import dev.fonteles.mynotes.data.model.User
import junit.framework.TestCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {

    private val user1 = User("username1", "password1")
    private val user2 = User("username2", "password2")
    private val user3 = User("username3", "password3")
    private val user4 = User("username4", "password4")
    private val notRegisteredUser = User("username-1", "password-1")

    private val remoteUsers = listOf(user1, user2, user3, user4)

    private lateinit var loginRemoteDataSource: ILoginDataSource
    private lateinit var userRepository: UserRepository

    @Before
    fun createRepository() {
        loginRemoteDataSource = FakeLoginDataSource(remoteUsers.toMutableList())
        userRepository = UserRepository(loginRemoteDataSource)
    }

    @Test
    fun `login with good username & password`() = runBlockingTest {
        val funcRes = userRepository.login(user1.username, user1.password) as FuncResult.Success
        TestCase.assertEquals(user1.username, funcRes.data.username)
    }

    @Test
    fun `login with bad username`() = runBlockingTest {
        val funcRes = userRepository.login(notRegisteredUser.username, user2.username)
        TestCase.assertTrue(funcRes is FuncResult.Error)
    }

    @Test
    fun `login with good username & bad password`() = runBlockingTest {
        val funcRes = userRepository.login(user1.username, user1.password + "gibberish")
        TestCase.assertTrue(funcRes is FuncResult.Error)
    }

}