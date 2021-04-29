package com.example.parkinglot.dao;

import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.util.VehicleType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VehicleDao {
    private static final List<Integer> parkingSlots = new ArrayList<>();
    private static final Map<String,Vehicle> vehicles = new HashMap<>();

    public static Map<String,Vehicle> getVehicles() {
        return vehicles;
    }

    public static List<Integer> getParkingSlots() {
        return parkingSlots;
    }

    public int findNearestParkingSlot(VehicleType type) {
        for (int i = 0; i < parkingSlots.size(); i++){
            if(4 - parkingSlots.get(i) >= type.ordinal()){
                return i;
            }
        }
        return -1;
    }

    public boolean putVehicle(Vehicle vehicle){
        Map<String, Vehicle> vehicles = VehicleDao.getVehicles();
        if(vehicles.containsKey(vehicle.getRegistrationId())) return false;
        vehicles.put(vehicle.getRegistrationId(),vehicle);
        return true;
    }

    public void fillSlotCapacity(int parkingSlotPosition, int vehicleSize) {
        int newSlotCapacity =  parkingSlots.get(parkingSlotPosition) + vehicleSize;
        parkingSlots.set(parkingSlotPosition,newSlotCapacity);
    }

    public Vehicle getVehicleByRegistrationId(String registrationId) {
        if(!vehicles.containsKey(registrationId)) return null;
        return vehicles.get(registrationId);
    }

    public void removeSlotCapacity(int parkingSlotPosition, int vehicleSize) {
        int newSlotCapacity =  parkingSlots.get(parkingSlotPosition) - vehicleSize;
        parkingSlots.set(parkingSlotPosition,newSlotCapacity);
    }

    public boolean removeVehicle(String registrationId) {
        if(vehicles.containsKey(registrationId)) return false;
        vehicles.remove(registrationId);
        return true;
    }

    public List<Integer> getParkingSlotsByColor(String color) {
        List<Integer> newParkingSlots = new ArrayList<>();
        for(String registrationId: vehicles.keySet()){
            Vehicle vehicle = vehicles.get(registrationId);
            if(vehicle.getColor().equalsIgnoreCase(color)){
                newParkingSlots.add(vehicle.getParkingSlot());
            }
        }
        return newParkingSlots;
    }
}
