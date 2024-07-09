package es.demo.kmpmovies

import android.location.Geocoder
import android.location.LocationProvider
import com.google.android.gms.location.LocationServices
import es.demo.kmpmovies.data.AndroidRegionDataSource
import es.demo.kmpmovies.data.RegionDataSource
import es.demo.kmpmovies.database.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single {
        getDatabaseBuilder(get())
    }
    factory { Geocoder(get()) }
    factory { LocationServices.getFusedLocationProviderClient(androidContext()) }
    factoryOf(::AndroidRegionDataSource) bind RegionDataSource::class
}
