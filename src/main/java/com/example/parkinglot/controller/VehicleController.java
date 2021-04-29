package com.example.parkinglot.controller;

import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/v1")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(path = "/vehicle")
    public String addVehicleToParkingSlot(@RequestBody Vehicle vehicle){
       return vehicleService.addVehicleToParkingSlot(vehicle);
    }

    @DeleteMapping(path = "/vehicle/{id}")
    public String removeVehicleFromParkingSlot(@PathVariable(value = "id") String registrationId){
        return vehicleService.removeVehicleFromParkingSlot(registrationId);
    }

    @GetMapping(path = "/vehicleById/{id}")
    public String getParkingSlotByRegistrationId(@PathVariable(value = "id") String registrationId){
        return vehicleService.getParkingSlotByRegistrationId(registrationId);
    }

    @GetMapping(path = "/vehicleByColor/{color}")
    public List<Integer> getParkingSlotsByColor(@PathVariable(value = "color") String color){
        return vehicleService.getParkingSlotsByColor(color);
    }
}
