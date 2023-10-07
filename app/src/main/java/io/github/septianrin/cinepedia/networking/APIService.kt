package io.github.septianrin.cinepedia.networking

import io.github.septianrin.cinepedia.feature.detailscreen.models.MovieInfo
import io.github.septianrin.cinepedia.feature.homescreen.models.ListMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("discover/movie")
    suspend fun getPopularMovieList(
        @Header("Authorization") apiKey : String,
        @Query("page") pages : Int
    ) : Response<ListMovies>

    @GET("movie/{movieId}?append_to_response=videos,reviews")
    suspend fun getMovieById(
        @Header("Authorization") apiKey : String,
        @Path("movieId") movieId : String,
    ) : Response<MovieInfo>
}