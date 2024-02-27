package models

import enums.DestinationEnum
import io.micronaut.serde.annotation.Serdeable

@Serdeable.Serializable
@Serdeable.Deserializable
data class DestinationModel(val city: DestinationEnum)



