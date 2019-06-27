package com.senacor.vienna.mn.rating

import javax.inject.Singleton

import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.micronaut.context.annotation.Value
import reactor.core.publisher.*

private val RATING_COLLECTION_NAME = "rating"

@Singleton
class RatingRepository(
        mongoClient: MongoClient,
        @Value("\${mongodb.name:test}") databaseName: String) {

    private val collection: MongoCollection<MutableRating> = mongoClient.getDatabase(databaseName)
            .getCollection(RATING_COLLECTION_NAME, MutableRating::class.java)

    fun findAllByItemId(intemId: String): Flux<Rating> {
        return collection
                .find(eq("itemId", intemId))
                .toFlux()
                .map(::mutableRatingToRating)
    }

    fun save(rating: Rating): Mono<Rating> {
        return collection
                .insertOne(ratingToMutableRatingTo(rating))
                .toMono()
                .map {
                    rating
                }
    }

    fun findById(ratingId: String): Mono<Rating> {
        return collection
                .find(eq("_id", ratingId))
                .toMono()
                .map(::mutableRatingToRating)
    }

    private fun mutableRatingToRating(rating: MutableRating): Rating {
        return Rating(rating.id!!, rating.itemId!!, rating.userId!!, rating.rating!!)
    }

    private fun ratingToMutableRatingTo(rating: Rating): MutableRating {
        return MutableRating(rating.id, rating.itemId, rating.userId, rating.rating)
    }

    data class MutableRating(
            var id: String?,
            var itemId: String?,
            var userId: String?,
            var rating: Int?) {
        constructor() : this(null, null, null, null)
    }
}



