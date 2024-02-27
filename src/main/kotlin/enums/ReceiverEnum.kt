package enums

enum class ReceiverEnum(val receiverName: String, val priority: Int) {
    FEDEX("FedEx", 2),
    UPS("UPS", 1),
    OLD_DOMINION("Old Dominion", 3),
}