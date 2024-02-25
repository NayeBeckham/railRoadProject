package services

import jakarta.inject.Singleton

//@Singleton
interface RaildRoadOpService {

    fun getRailsRoadInfo(): String {
        return "Hello from Service!"
    }
}