package ru.captaindmitro.cinemaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.captaindmitro.cinemaapp.data.MovieApi
import ru.captaindmitro.cinemaapp.data.RemoteDataSource
import ru.captaindmitro.cinemaapp.data.RepositoryImpl
import ru.captaindmitro.cinemaapp.domain.Repository
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        movieApi: MovieApi,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): RemoteDataSource = RemoteDataSource.Base(movieApi, dispatcher)

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource
    ): Repository = RepositoryImpl(remoteDataSource)

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher