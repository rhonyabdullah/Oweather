package com.droid.silverbullet.domain.usecase

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-19.
 */
sealed class UseCaseResponse<out T : Any> {
    data class Success<T : Any>(val value: T) : UseCaseResponse<T>()
    data class Error(val message: String, val statusCode: Int) : UseCaseResponse<Nothing>()
}

abstract class UseCase<Type : Any, in Params> {
    abstract suspend fun build(params: Params?): UseCaseResponse<Type>
    suspend fun execute(params: Params? = null): UseCaseResponse<Type> = build(params)
}
