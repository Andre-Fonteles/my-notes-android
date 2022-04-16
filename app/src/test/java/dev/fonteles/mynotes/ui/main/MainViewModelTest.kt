package dev.fonteles.mynotes.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.fonteles.mynotes.data.model.User
import dev.fonteles.mynotes.data.repository.FakeUserRepository
import dev.fonteles.mynotes.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val user1 = User("username1", "password1")
    private val user2 = User("username2", "password2")
    private val user3 = User("username3", "password3")
    private val user4 = User("username4", "password4")
    private val notRegisteredUser = User("username-1", "password-1")

    private val remoteUsers = listOf(user1, user2, user3, user4)

    private lateinit var userRepository: FakeUserRepository
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun createViewModel() {
        userRepository = FakeUserRepository(remoteUsers.toMutableList(), null)
        mainViewModel = MainViewModel(userRepository)
    }

    @Test
    fun `check user is logged in returns true`() {
        runBlocking {
            userRepository.login(user1.username, user1.password)
        }

        mainViewModel.checkUserLoggedIn()
        val value = mainViewModel.isLoggedIn.getOrAwaitValue()
        assertTrue(value)
    }

    @Test
    fun `check user is logged in returns false`() {
        mainViewModel.checkUserLoggedIn()
        val value = mainViewModel.isLoggedIn.getOrAwaitValue()
        assertFalse(value)
    }

}