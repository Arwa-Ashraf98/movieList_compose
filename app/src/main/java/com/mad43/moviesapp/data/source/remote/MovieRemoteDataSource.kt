package com.mad43.moviesapp.data.source.remote

import coil.network.HttpException
import com.mad43.moviesapp.data.models.response.MovieResponse
import com.mad43.moviesapp.domain.ResourceResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class MovieRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineDispatcher
) : IMovieRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): ResourceResult<MovieResponse?> {
        return withContext(ioDispatcher) {
            try {
                val response = moviesApi.getMovieDetails(movieId)
                if (response.isSuccessful) {
                    ResourceResult.SUCCESS(response.body())
                } else {
                    ResourceResult.ERROR(Throwable(response.message()))
                }
            } catch (ioException: IOException) {
                ResourceResult.ERROR(ioException)
            } catch (httpException : HttpException){
                ResourceResult.ERROR(httpException)
            } catch (e : Exception){
                ResourceResult.ERROR(e)
            }
        }
    }
}