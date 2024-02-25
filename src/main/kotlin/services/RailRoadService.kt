package services

import enums.DestinationEnum
import enums.ReceiverEnum
import jakarta.inject.Singleton
import models.*

@Singleton
class RailRoadService {

    val railsInput = mutableListOf(
            RailRoadModel(0, "Engine"),
            RailRoadModel(0, "Box Car 1", createDestinationWithPriority(DestinationEnum.HOUSTON), createReceiverWithPriority(ReceiverEnum.FEDEX) ),
            RailRoadModel(0, "Box Car 2", createDestinationWithPriority(DestinationEnum.CHICAGO), createReceiverWithPriority(ReceiverEnum.FEDEX)),
            RailRoadModel(0, "Box Car 3", createDestinationWithPriority(DestinationEnum.HOUSTON), createReceiverWithPriority(ReceiverEnum.UPS)),
            RailRoadModel(0, "Box Car 4", createDestinationWithPriority(DestinationEnum.LA), createReceiverWithPriority(ReceiverEnum.OLD_DOMINION)),
            RailRoadModel(0, "Box Car 5", createDestinationWithPriority(DestinationEnum.LA), createReceiverWithPriority(ReceiverEnum.FEDEX)),
            RailRoadModel(0, "Box Car 6", createDestinationWithPriority(DestinationEnum.HOUSTON), createReceiverWithPriority(ReceiverEnum.OLD_DOMINION)),
    )
     fun getRailsRoadInfo(): String {
        return "Hello from Service! ${railsInput[1]}"
    }

    fun getRailsRoadOrder(): List<RailRoadModelView> {
        var railRoadSortedList = railsInput.sortedWith(compareBy({it.destination?.priority}, {it.receiver?.priority}))
        var railsRoadFinalList = mutableListOf<RailRoadModelView>();
        for(railRoad in railRoadSortedList){
            if(railRoad.nameOfCar !== "Engine"){
                railsRoadFinalList.add(RailRoadModelView(railRoad.destination?.priority, railRoad.nameOfCar, railRoad.destination?.city, railRoad.receiver?.receiver))
            }
        }

        return railsRoadFinalList;
    }
}