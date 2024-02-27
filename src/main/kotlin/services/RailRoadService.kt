package services

import enums.DestinationEnum
import enums.ReceiverEnum
import jakarta.inject.Singleton
import models.*

@Singleton
class RailRoadService {

    val railsInput = mutableListOf<RailRoadModel>();
     fun getRailsRoadInfo(): String {
        return "Hello from Service! ${railsInput[1]}"
    }

    fun getRailsRoadOrder(): List<RailRoadModelView> {
        var railRoadSortedList = railsInput.sortedWith(compareBy({it.destination?.city?.priority}, {it.receiver?.receiver?.priority}))
        var railsRoadFinalList = mutableListOf<RailRoadModelView>();
        for(railRoad in railRoadSortedList){
            railsRoadFinalList.add(RailRoadModelView(railRoad.destination?.city?.priority, railRoad.nameOfCar, railRoad.destination?.city?.cityName, railRoad.receiver?.receiver?.receiverName))
        }

        return railsRoadFinalList;
    }

    fun createRailsRoad(railList: List<RailRoadModelView>): List<RailRoadModelView>{
        for (rail in railList){
            val destination = DestinationEnum.entries.find{ it.cityName == rail.destination}
            val receiver = ReceiverEnum.entries.find { it.receiverName == rail.receiver }
            if(rail.nameOfCar != "Engine"){
                railsInput.add(
                    RailRoadModel(
                        rail.nameOfCar,
                        destination?.let { DestinationModel(it) },
                        receiver?.let { ReceiverModel(it) }
                    )
                )
            }
        }
        return getRailsRoadOrder();
    }

}