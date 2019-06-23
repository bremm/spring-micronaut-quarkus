package com.senacor.vienna.spring.rating

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface RatingRepository : ReactiveCrudRepository<Rating, String> {
    fun findAllByItemId(intemId: String): Flux<Rating>
}
