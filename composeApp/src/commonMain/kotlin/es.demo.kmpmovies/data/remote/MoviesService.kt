package es.demo.kmpmovies.data.remote

import es.demo.kmpmovies.data.RegionRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MoviesService(
    private val client: HttpClient
) {
    suspend fun fetchPopularMovies(region: String): RemoteResult {
        return client.get("/3/discover/movie") {
            parameter("sort_by", "popularity.desc")
            parameter("region", region)
        }
            .body<RemoteResult>()
    }

    suspend fun fetchMovieById(id: Int): RemoteMovie {
        return client.get("/3/movie/$id")
            .body<RemoteMovie>()
    }
}