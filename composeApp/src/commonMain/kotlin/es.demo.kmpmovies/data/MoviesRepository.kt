package es.demo.kmpmovies.data

class MoviesRepository(private val moviesService: MoviesService) {
    suspend fun fetchPopularMovies(): List<Movie> {
        return moviesService.fetchPopularMovies().results.map { it.toDomainMovie() }
    }

    private fun RemoteMovie.toDomainMovie() = Movie (
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        poster = "https://image.tmdb.org/t/p/w185/$posterPath",
        backdrop = backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" },
        originalTitle = originalLanguage,
        originalLanguage = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        //false
    )

    suspend fun fetchMovieById(id: Int): Movie{
        return moviesService.fetchMovieById(id).toDomainMovie()
    }
}