package com.company;


import java.io.Serializable;
import java.util.ArrayList;


public class Formula1Driver extends Drivers implements Serializable {

    // Variables
    private int NoOfRace;
    private int Points;
    ArrayList<Integer> positions = new ArrayList<>();   // Storing the positions in an array
    private int number1;
    private int number2;
    private int number3;


    public Formula1Driver() {  // constructor for Formula1Driver
        this.NoOfRace = 0;
        this.Points   = 0;


    }

    public void CalculatePointsForRace() {
        /* user input the no of race, using for loop get
        the points and position stored in the array and return it to getPosition
         */
        this.number1  = 0;
        this.number2  = 0;
        this.number3  = 0;
        Points = 0;
        for (int i = 0; i < NoOfRace; i++) {
            Points = Points + getPointsForPosition(positions.get(i));


        }
    }

    // Getters for drivers to get positions and the races

    public ArrayList<Integer> getPositions() {  // to return the position to calculate the points for the race.
        return positions;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getNumber3() {
        return number3;
    }

    public int getNoOfRace(){
        return NoOfRace;
    }

    public int getPoints(){
        return Points;
    }

    public int getPointsForPosition(int position) { // using position of the user input to identify the points for each position and return the case value.

        switch (position) {
            case 1:
                number1++;
                return 25;
            case 2:
                number2++;
                return 18;
            case 3:
                number3++;
                return 15;
            case 4:
                return 12;
            case 5:
                return 10;
            case 6:
                return 8;
            case 7:
                return 6;
            case 8:
                return 4;
            case 9:
                return 2;
            case 10:
                return 1;
            default:
                System.out.println("Unknown Position...Try Again \n");
                return 0;
        }
    }

    // Setters for the drivers to get the positions and the races

    public void setPositions(ArrayList<Integer> positions) {   // to set the position into array integer and get the points.
        this.positions = positions;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public void setNoOfRace(int noOfRace){

        this.NoOfRace = noOfRace;
    }

}
