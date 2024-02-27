package models

import enums.ReceiverEnum
import io.micronaut.serde.annotation.Serdeable

@Serdeable.Serializable
@Serdeable.Deserializable
data class ReceiverModel(val receiver: ReceiverEnum)
