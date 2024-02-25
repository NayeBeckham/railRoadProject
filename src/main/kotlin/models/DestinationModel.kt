package models

import enums.DestinationEnum
import io.micronaut.serde.annotation.Serdeable

@Serdeable.Serializable
@Serdeable.Deserializable
data class DestinationModel(val city: DestinationEnum, var priority: Int? = 0)

    fun createDestinationWithPriority(city: DestinationEnum): DestinationModel {
        var classificationTrack = when (city) {
            DestinationEnum.HOUSTON -> 1
            DestinationEnum.CHICAGO -> 2
            DestinationEnum.LA -> 3
        }
        return DestinationModel(city, classificationTrack)
    }


