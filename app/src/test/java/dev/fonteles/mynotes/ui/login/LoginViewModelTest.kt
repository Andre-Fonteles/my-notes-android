package dev.fonteles.mynotes.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.fonteles.mynotes.data.model.User
import dev.fonteles.mynotes.data.repository.FakeUserRepository
import dev.fonteles.mynotes.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val user1 = User("username1", "password1")
    private val user2 = User("username2", "password2")
    private val user3 = User("username3", "password3")
    private val user4 = User("username4", "password4")
    private val notRegisteredUser = User("username-1", "password-1")

    private val remoteUsers = listOf(user1, user2, user3, user4)

    private lateinit var userRepository: FakeUserRepository
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun createViewModel() {
        userRepository = FakeUserRepository(remoteUsers.toMutableList(), null)
        loginViewModel = LoginViewModel(userRepository)
    }

    @Test
    fun `formState is valid with valid username & password`() {
        loginViewModel.validateForm("username", "password")
        val value = loginViewModel.formState.getOrAwaitValue()
        Assert.assertNull(value.usernameError)
        Assert.assertNull(value.passwordError)
        Assert.assertTrue(value.isValid())
    }

    @Test
    fun `formState is invalid with empty username`() {
        loginViewModel.validateForm("", "password")
        val value = loginViewModel.formState.getOrAwaitValue()
        Assert.assertNotNull(value.usernameError)
        Assert.assertFalse(value.isValid())
    }

    @Test
    fun `formState is invalid with empty password`() {
        loginViewModel.validateForm("username", "")
        val value = loginViewModel.formState.getOrAwaitValue()
        Assert.assertNotNull(value.passwordError)
        Assert.assertFalse(value.isValid())
    }

    @Test
    fun `formState is invalid with short password`() {
        loginViewModel.validateForm("username", "1234")
        val value = loginViewModel.formState.getOrAwaitValue()
        Assert.assertNotNull(value.passwordError)
        Assert.assertFalse(value.isValid())
    }

    @Test
    fun `formState is invalid with short username`() {
        loginViewModel.validateForm("us", "password")
        val value = loginViewModel.formState.getOrAwaitValue()
        Assert.assertNotNull(value.usernameError)
        Assert.assertFalse(value.isValid())
    }

    @Test
    fun `username and password have errors when both are invalid`() {
        loginViewModel.validateForm("us", "1234")
        val value = loginViewModel.formState.getOrAwaitValue()
        Assert.assertNotNull(value.usernameError)
        Assert.assertNotNull(value.passwordError)
        Assert.assertFalse(value.isValid())
    }
}