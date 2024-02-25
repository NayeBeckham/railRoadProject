package models

import enums.DestinationEnum
import enums.ReceiverEnum
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.serde.annotation.Serdeable.Deserializable

@Serdeable.Serializable
@Deserializable
data class RailRoadModel(
    val classificationTrack: Int,
    val nameOfCar: String,
    val destination: DestinationModel? = null,
    val receiver: ReceiverModel? = null,
    )


@Serdeable.Serializable
@Deserializable
data class RailRoadModelView(
    val classificationTrack: Int?,
    val nameOfCar: String,
    val destination: DestinationEnum?,
    val receiver: ReceiverEnum?,
)