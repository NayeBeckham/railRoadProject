package models

import enums.ReceiverEnum
import io.micronaut.serde.annotation.Serdeable

@Serdeable.Serializable
@Serdeable.Deserializable
data class ReceiverModel(val receiver: ReceiverEnum, val priority: Int?)

fun createReceiverWithPriority(receiver: ReceiverEnum): ReceiverModel {
    var receiverPriority = when (receiver) {
        ReceiverEnum.UPS -> 1
        ReceiverEnum.FEDEX -> 2
        ReceiverEnum.OLD_DOMINION -> 3
    }
    return ReceiverModel(receiver, receiverPriority)
}