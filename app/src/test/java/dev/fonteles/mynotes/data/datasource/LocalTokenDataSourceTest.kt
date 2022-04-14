package dev.fonteles.mynotes.data.datasource

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.fonteles.mynotes.data.model.Token
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import java.lang.reflect.Field

@RunWith(AndroidJUnit4::class)
class LocalTokenDataSourceTest {

    private lateinit var localTokenDataSource: ILocalTokenDataSource

    @Before
    fun createDataSource() {
        localTokenDataSource = LocalTokenDataSource.getInstance(ApplicationProvider.getApplicationContext())
    }

    @Before
    fun resetSingleton() {
        val instanceField = LocalTokenDataSource::class.java.getDeclaredField("instance")
        instanceField.isAccessible = true
        instanceField.set(null, null)
    }

    @Test
    fun `save and get token`() {
        val token = Token("username", "hash")
        localTokenDataSource.saveToken(token.copy())
        Assert.assertEquals(token, localTokenDataSource.getToken())
    }

    @Test
    fun `get token when none exist`() {
        val token = Token("username", "hash")
        Assert.assertNull(localTokenDataSource.getToken())
    }

    @Test
    fun `add and delete token`() {
        val token = Token("username", "hash")
        localTokenDataSource.saveToken(token.copy())
        localTokenDataSource.deleteToken()
        Assert.assertNull(localTokenDataSource.getToken())
    }

}