package com.company;


import java.io.Serializable;



public abstract class Drivers implements Serializable {
    // Variables for the driver class
   protected String DriverName;
   protected String DriverLocation;
   protected String DriverTeam;

   // Getters and Setters for the driver name , location and team
    public String getDriverName() {
        return DriverName;
    }


    public void setDriverName(String driverName) {
        this.DriverName = driverName;

    }


    public String getDriverLocation() {
        return DriverLocation;
    }


    public void setDriverLocation(String driverLocation) {
        this.DriverLocation = driverLocation;

    }


    public String getDriverTeam() {
        return DriverTeam;
    }


    public void setDriverTeam(String driverTeam) {
        this.DriverTeam = driverTeam;

    }



}
