package com.senacor.vienna.mn.rating

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.senacor.vienna.mn.rating")
                .mainClass(Application.javaClass)
                .start()
    }
}