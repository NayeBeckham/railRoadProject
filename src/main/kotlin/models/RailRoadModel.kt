package models

import enums.DestinationEnum
import enums.ReceiverEnum
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.serde.annotation.Serdeable.Deserializable

@Serdeable.Serializable
@Deserializable
data class RailRoadModel(
    val nameOfCar: String,
    val destination: DestinationModel? = null,
    val receiver: ReceiverModel? = null,
    val classificationTrack: Int? = 0,
    )


@Serdeable.Serializable
@Deserializable
data class RailRoadModelView(
    val classificationTrack: Int?,
    val nameOfCar: String,
    val destination: String? = null,
    val receiver: String? = null,
)