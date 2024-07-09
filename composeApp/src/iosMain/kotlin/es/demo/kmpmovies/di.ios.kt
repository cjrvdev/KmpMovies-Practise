package es.demo.kmpmovies

import es.demo.kmpmovies.data.IosRegionDataSource
import es.demo.kmpmovies.data.RegionDataSource
import es.demo.kmpmovies.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocationManager

actual val nativeModule = module {
    single { getDatabaseBuilder() }
    factoryOf(::IosRegionDataSource) bind RegionDataSource::class
}