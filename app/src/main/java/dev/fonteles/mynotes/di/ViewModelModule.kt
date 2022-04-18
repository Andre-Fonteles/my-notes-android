package dev.fonteles.mynotes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.fonteles.mynotes.data.datasource.ILocalTokenDataSource
import dev.fonteles.mynotes.data.datasource.ILoginDataSource
import dev.fonteles.mynotes.data.datasource.LocalTokenDataSource
import dev.fonteles.mynotes.data.datasource.LoginRemoteDataSource
import dev.fonteles.mynotes.data.repository.IUserRepository
import dev.fonteles.mynotes.data.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindILoginDataSource(loginDataSource: LoginRemoteDataSource) : ILoginDataSource

    @Binds
    abstract fun bindILocalTokenDataSource(tokenDataSource: LocalTokenDataSource) : ILocalTokenDataSource

    @Binds
    abstract fun bindIUserRepository(userRepository: UserRepository) : IUserRepository

}