package es.demo.kmpmovies

import androidx.room.RoomDatabase
import es.demo.kmpmovies.data.MoviesRepository
import es.demo.kmpmovies.data.RegionRepository
import es.demo.kmpmovies.data.remote.MoviesService
import es.demo.kmpmovies.data.database.MoviesDao
import es.demo.kmpmovies.data.database.MoviesDatabase
import es.demo.kmpmovies.ui.screens.detail.DetailViewModel
import es.demo.kmpmovies.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single(named("apiKey")) { BuildConfig.API_KEY }
    single<MoviesDao> {
        val dbBuilder = get<RoomDatabase.Builder<MoviesDatabase>>()
        dbBuilder.build().moviesDao()
    }
}

val dataModule = module {
    factoryOf(::MoviesRepository)
    factoryOf(::RegionRepository)
    factoryOf(::MoviesService)
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    parameters.append("api_key", BuildConfig.API_KEY)
                }
            }
        }
    }
}

val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin() {
        config?.invoke(this)
        modules(appModule, dataModule, viewModelsModule, nativeModule)
    }
}