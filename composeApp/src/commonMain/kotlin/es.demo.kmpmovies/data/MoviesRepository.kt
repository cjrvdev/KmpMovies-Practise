package es.demo.kmpmovies.data

class MoviesRepository(private val moviesService: MoviesService) {
    suspend fun fetchPopularMovies(): List<Movie> {
        return moviesService.fetchPopularMovies().results.map { it.toDomainMovie() }
    }

    private fun RemoteMovie.toDomainMovie() = Movie (
        id = id,
        title = title,
        poster = "https://image.tmdb.org/t/p/w500$posterPath"
    )

    suspend fun fetchMovieById(id: Int): Movie{
        return moviesService.fetchMovieById(id).toDomainMovie()
    }
}