package com.senacor.vienna.spring.rating

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RatingService (private val repository: RatingRepository) {

    fun rateItem(itemId: String, rating: RatingRequest): Mono<Rating> {
        return repository.save(Rating(null, itemId, rating.userId, rating.rating))
    }

    fun fetchRatings(itemId: String): Flux<Rating> {
        return repository.findAllByItemId(itemId)
    }

    fun fetchRating(ratingId: String): Mono<Rating> {
        return repository.findById(ratingId)
    }

}

