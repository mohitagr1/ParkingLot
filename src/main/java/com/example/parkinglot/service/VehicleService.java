package com.example.parkinglot.service;

import com.example.parkinglot.dao.VehicleDao;
import com.example.parkinglot.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VehicleService {

    private final VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public String addVehicleToParkingSlot(Vehicle vehicle) {
        int parkingSlotPosition = vehicleDao.findNearestParkingSlot(vehicle.getType());
        if(parkingSlotPosition == -1) return "Not Available";

        vehicle.setParkingSlot(parkingSlotPosition);

        if(!vehicleDao.putVehicle(vehicle)){
            return "Vehicle Already Exits!!!!!!";
        }
        vehicleDao.fillSlotCapacity(parkingSlotPosition,vehicle.getType().ordinal());
        return "Your Slot is :"+parkingSlotPosition;
    }



    public String removeVehicleFromParkingSlot(String registrationId) {
        Vehicle vehicle = vehicleDao.getVehicleByRegistrationId(registrationId);
        if(vehicle == null) return "Vehicle Doesn't Exits!!!!!!";

        vehicleDao.removeSlotCapacity(vehicle.getParkingSlot(),vehicle.getType().ordinal());

        if(!vehicleDao.removeVehicle(registrationId)){
            return "Vehicle Not Exits!!!!!!";
        }

        return "See you again!!!! Byeee";
    }

    public String getParkingSlotByRegistrationId(String registrationId) {
        Vehicle vehicle = vehicleDao.getVehicleByRegistrationId(registrationId);
        if(vehicle == null) return "Vehicle Doesn't Exits!!!!!!";
        return "Your Vehicle is parked here : " + vehicle.getParkingSlot();
    }

    public List<Integer> getParkingSlotsByColor(String color) {
        return vehicleDao.getParkingSlotsByColor(color);
    }
}
