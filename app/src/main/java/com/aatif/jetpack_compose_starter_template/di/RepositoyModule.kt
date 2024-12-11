package com.aatif.jetpack_compose_starter_template.di
import com.aatif.jetpack_compose_starter_template.data.remote.ApiService
import com.aatif.jetpack_compose_starter_template.data.repository.ExampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideExampleRepository(apiService: ApiService): ExampleRepository {
        return ExampleRepository(apiService)
    }
}
