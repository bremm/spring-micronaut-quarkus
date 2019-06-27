package com.senacor.vienna.mn.rating

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import javax.inject.Singleton

@Singleton
class RatingService (private val repository: RatingRepository) {

    fun rateItem(itemId: String, rating: RatingRequest): Mono<Rating> {
        return repository.save(Rating(UUID.randomUUID().toString(), itemId, rating.userId, rating.rating))
    }

    fun fetchRatings(itemId: String): Flux<Rating> {
        return repository.findAllByItemId(itemId)
    }

    fun fetchRating(ratingId: String): Mono<Rating> {
        return repository.findById(ratingId)
    }

}

