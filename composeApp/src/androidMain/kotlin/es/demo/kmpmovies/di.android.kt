package es.demo.kmpmovies

import es.demo.kmpmovies.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModule = module {
    single {
        getDatabaseBuilder(get())
    }
}