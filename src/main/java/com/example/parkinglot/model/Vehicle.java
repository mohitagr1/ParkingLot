package com.example.parkinglot.model;

import com.example.parkinglot.util.VehicleType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Vehicle {
    private String registrationId;
    private String color;
    private VehicleType type;
    private Timestamp startTime;
    private Timestamp endTime;
    private int parkingSlot;
}
