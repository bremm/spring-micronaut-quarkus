package com.senacor.vienna.spring.rating

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

data class RatingRequest(val userId: String, val rating: Int)

@RestController
@RequestMapping("/items/{itemId}/ratings")
class RatingController(private val ratingService: RatingService) {

    @PostMapping
    fun createRating(@PathVariable itemId: String, @RequestBody rating: RatingRequest): Mono<ResponseEntity<Void>> {
        return ratingService.rateItem(itemId, rating).map {
            val location = URI.create("/items/${itemId}/ratings/${it.id}")
            return@map ResponseEntity.created(location).build<Void>()
        }
    }

    @GetMapping
    fun fetchRatingsForItem(@PathVariable itemId: String): Flux<Rating> {
        return ratingService.fetchRatings(itemId)
    }

    @GetMapping("/{ratingId}")
    fun fetchRating(@PathVariable ratingId: String): Mono<Rating> {
        return ratingService.fetchRating(ratingId)
    }
}



