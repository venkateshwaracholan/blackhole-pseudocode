

round 1
LongestSubstringWithoutRepeatingChar

round 2
NextGreatestElementToRight
removeElementOutOfRangeBST

round 3
PrintBinaryTreeWithProperSpacing
Question_answer.md



/*
Parking Lot - Low Level Design

N levels

X rows

Y spots

Car -> 2 spots
Bike -> 1 spot
Bus -> 4 spots


Microservices/modules:

Authentication
Payment


APIS:
admin config
getParkingSpace
register(after payment)


database



abstract class Vehicle{
 private String vehicleNo;
 private int space;
 private ParkingSpace firstSpot;
  
 public getVehicleNo();
 public SetVehicleNo();
}


class Car extends Vehicle{
  public getVehicleNo(){
    return this.vehicleNo;
  }
  
  public SetVehicleNo(String vehicleNo){
    this.vehicleNo = vehicleNo;
  }
}



class ParkingLot{
  private int MaxLevel, maxRows, maxSpots;
  private int filledLevel;
  private List<Level> parkingSpace;
  private Map<ParkingSpace, Vehicle> parkingMap;
  private Map<String, Vechicle> VechicleMap;
  

  public String getParkingSpace(Vehicle v){
  	 
  }

  public sizeValidation(vehicle v){
   	if(v.size>maxSpots)
      return false;
  }
  
  public boolean isAvailable(ParkingSpace space, vehicle v){
    
  }

}

class Level{
 // has many rows
 private List<Row> rows;
 private int filledRow;
}

class Row{
 // 
  private List<Integer> spots;
  private FilledSpot;
}


class ParkingSpace{
 	private int level, row, spot; 
}


//for spot size 4
c1c1,B1,B2




*/
