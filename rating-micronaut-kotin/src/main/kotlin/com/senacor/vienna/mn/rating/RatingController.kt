package com.senacor.vienna.mn.rating

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

data class RatingRequest(val userId: String, val rating: Int)

@Controller("/items/{itemId}/ratings")
class RatingController(private val ratingService: RatingService) {

    @Post
    fun createRating(@PathVariable itemId: String, @Body rating: RatingRequest): Mono<HttpResponse<Void>> {
        return ratingService.rateItem(itemId, rating).map {
            val location = URI.create("/items/${itemId}/ratings/${it.id}")
            return@map HttpResponse.created<Void>(location)
        }
    }

    @Get
    fun fetchRatingsForItem(itemId: String): Flux<Rating> {
        return ratingService.fetchRatings(itemId)
    }

    @Get("/{ratingId}")
    fun fetchRating(ratingId: String): Mono<Rating> {
        return ratingService.fetchRating(ratingId)
    }
}



