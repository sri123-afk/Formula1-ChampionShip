package com.company;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class Formula1ChampionShipManager implements ChampionShipManager{

    public ArrayList<Drivers> drivers = new ArrayList<>(); // Creating an object named driver to access the Driver abstract class
    DefaultTableModel tableModel;



    public void formula1GUI() {
        updateTable();

        JTable table = new JTable(tableModel);

        JFrame frame = new JFrame("Formula 1 Championship ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(null);
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBounds(0,0,850,550);

        tablePanel.add(tablePane);

        JPanel RaceButtonPane = new JPanel();
        RaceButtonPane.setBounds(850,50, 150, 40);
        JButton StartRace = new JButton("Start Race");

        JPanel checkBoxAscend = new JPanel();
        checkBoxAscend.setBounds(850, 110, 150, 10);
        JButton checkBox1 = new JButton("Descend");

        JPanel checkBoxDescend = new JPanel();
        checkBoxDescend.setBounds(850, 120, 150, 200);
        JButton checkBox2 = new JButton("Ascend");

        //https://stackoverflow.com/questions/28823670/how-to-sort-jtable-in-shortest-way

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        checkBox2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<RowSorter.SortKey> sortKeys = new ArrayList<>();

                sortKeys.add(new RowSorter.SortKey(8, SortOrder.ASCENDING));
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
            }
        });
        checkBoxDescend.add(checkBox2);


        checkBox1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<RowSorter.SortKey> sortKeys = new ArrayList<>();

                sortKeys.add(new RowSorter.SortKey(8, SortOrder.DESCENDING));
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
            }
        });
        checkBoxDescend.add(checkBox1);



        StartRace.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Race();
                updateTable();
                tableModel.fireTableDataChanged();
                table.setModel(tableModel);

            }
        });
        RaceButtonPane.add(StartRace);


        tablePanel.add(RaceButtonPane);
        tablePanel.add(checkBoxAscend);
        tablePanel.add(checkBoxDescend);


        frame.add(tablePanel);

        frame.setSize(1000,450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public void updateTable(){
        /* Creating a default table model to display the table in the graphical user interface
        java swing.
         */
        Date date = new Date();
        new SimpleDateFormat("dd-MMM-yyyy").format(date);

        //https://stackoverflow.com/questions/31561550/java-jtable-defaulttablemodel-java-lang-arrayindexoutofboundsexception

        tableModel = new DefaultTableModel(new String[]{"Date","Driver Name", "Driver Team", "Driver Location", "Total Race", "1st Place", "2nd Place", "3rd Place", "Total Points"},0);
        for(Drivers driver : drivers){

            Formula1Driver driverSet = (Formula1Driver) driver;

            tableModel.addRow(new Object[]{
                    date,driverSet.getDriverName(),
                    driverSet.getDriverTeam(), driverSet.getDriverLocation(),
                    driverSet.getNoOfRace(), driverSet.getNumber1(),
                    driverSet.getNumber2(), driverSet.getNumber3(),
                    driverSet.getPoints()
            });
        }


    }


    public void displayMenu() {
        readFile();  // checking whether user input data is there or not.
        boolean stop = false;

        while (!stop) {    // Creating a menu for the formula 1 championship
            System.out.println("--------Menu For Formula1ChampionShip-------------\n \f" +
                    " Press 'A' to Add a Driver \n \f" + " Press 'D' to Delete a Driver \n \f" +
                    " Press 'C' to Change Driver team \n \f" + " Press 'V' to View Stats of Driver \n \f" +
                    " Press 'T' to see Table of Drivers \n \f" + " Press 'R' to Start a Race \n \f" + " Press 'S' to Store the details \n \f" +
                    " Press 'G' to Open GUI \n \f" + " Press 'E' to Exit the Program ");


            Scanner input = new Scanner(System.in);
            System.out.println("Please select an option.....");
            String select = input.next().toUpperCase();


            switch (select) {
                case "A":
                    addDriver();
                    System.out.println("\n *************DRIVER ADDED :) ************************\n");
                    break;
                case "D":
                    deleteDriver();
                    System.out.println("\n **********DRIVER DELETED :( ***************************\n");
                    break;
                case "C":
                    changeDriver();
                    System.out.println("\n*********************************************************\n");
                    break;
                case "V":
                    statDriver();
                    System.out.println("\n ****************STAT SHOWED*******************\n");
                    break;
                case "T":
                    TableDriver();
                    System.out.println("\n *******************************************************\n");
                    break;
                case "R":
                    Race();
                    System.out.println("\n ************Race<><>Done*******************\n");
                    break;
                case "S":
                    saveFile();
                    System.out.println("\n *******************DETAILS SAVED******************\n");
                    break;
                case "G":
                    formula1GUI();
                    System.out.println("\n **********Initializing Window*************\n");
                    break;
                case "E":
                    System.out.println("You are about to exit the program...");
                    System.out.println("\n ************************************");
                    stop = true;
                    break;
                default:
                    System.out.println("Wrong letter");
                    System.out.println("\n ************************************");


            }
        }

    }



    public boolean compareTeamName(String teamName) {   // identify unique user input which is stored in the drivers array.

        for (Drivers driver : drivers) {
            if (teamName.equals(driver.getDriverTeam().toUpperCase())) {
                return true;
            }
        }
        return false;
    }


    public void addDriver() {  // Creating a object to access the formula1driver class.
        Formula1Driver formula1Driver = new Formula1Driver();
        boolean check = true;

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the driver Name");
        String name_ = input.nextLine().toUpperCase();
        System.out.println("Teams that Already Exist Please enter a different one \n");

        for (Drivers driver : drivers) {  // Using for each loop to get the array inputs of the user to bytes and display it.
            System.out.println(driver.getDriverTeam());
        }
        while (check) {
            System.out.println("\nPlease enter the team name of the driver ");
            String teamName_ = input.nextLine().toUpperCase();

            if (compareTeamName(teamName_)) {
                System.out.println("Already there... try another team name...\n");
            } else {
                check = false;
                System.out.println("Please enter the location of the driver..");
                String location_ = input.nextLine().toUpperCase();
                System.out.println("Please enter the number of races the driver participated..");

                int noOfRace_ = input.nextInt();
                ArrayList<Integer> positions = new ArrayList<>();  // Using array list to store the position and return it to the formula1Driver class.

                for (int i = 1; i <= noOfRace_; i++) {
                    System.out.println("Enter the position of " + i + " Race -- ");
                    int position_ = input.nextInt();

                    if (position_ > 11) {
                        System.out.println("Unknown Position");
                        break;
                    } else {
                        positions.add(position_);
                    }
//                    formula1Driver.PositionAndPoints(position_);
                }
                // Setting all the driver details

                formula1Driver.setDriverName(name_);
                formula1Driver.setDriverTeam(teamName_);
                formula1Driver.setDriverLocation(location_);
                formula1Driver.setNoOfRace(noOfRace_);
                formula1Driver.setPositions(positions);
                formula1Driver.CalculatePointsForRace();
                // formula1Driver.setNoOfRace(formula1Driver.getNoOfRace() + noOfRace_);


                drivers.add(formula1Driver);

                System.out.println("Driver details have been recorded.. \n ******************************* ***********\n");
            }
        }

    }


    public void deleteDriver() {
        /*
        Asking for input a number to delete the driver details
        Output of driver get driver name.
         */
        int number = 1;
        Scanner input = new Scanner(System.in);

        System.out.println("Driver Teams and Names\n");

        for (Drivers driver : drivers) {
            System.out.println("Driver " + number + " Name - " + driver.getDriverName());
            number++;

        }

        System.out.println("\nEnter driver number to be deleted ");
        int numberOfDriver = input.nextInt();

        while(numberOfDriver < 1 || numberOfDriver > number){
            System.out.println("Try Again");
            System.out.println("Enter driver number to be deleted ");
            numberOfDriver = input.nextInt();

        }
        Drivers delete = drivers.remove(numberOfDriver - 1);    // using in built method to remove the driver details
        System.out.println("Driver " + numberOfDriver + " " + delete.DriverName + " Deleted \n");

    }

    public ArrayList<Drivers> sortArray() {

        ArrayList<Drivers> arraySort = new ArrayList<>();
        ArrayList<Drivers> arrayUnSort = (ArrayList<Drivers>) drivers.clone();  //https://howtodoinjava.com/java/collections/arraylist/arraylist-clone-deep-copy/
        if (drivers.size() >= 1 ){

            for (int j = 1; j < drivers.size();j++){
                int highestIndex =0;
                for (int i = 0; i < arrayUnSort.size(); i++) {
                    Formula1Driver driverSetCurrent = (Formula1Driver) arrayUnSort.get(i);
                    if (i==0) {
                        highestIndex = i;
                    } else {
                        Formula1Driver driverSetHighest = (Formula1Driver)  arrayUnSort.get(highestIndex);
                        if(driverSetCurrent.getPoints() > driverSetHighest.getPoints()) {
                            highestIndex = i;
                        }else if (driverSetCurrent.getPoints() == driverSetHighest.getPoints()) {
                            if (driverSetCurrent.getNumber1()>driverSetHighest.getNumber1())
                            {
                                highestIndex = i;
                            }
                        }
                    }
                }
                arraySort.add(arrayUnSort.get(highestIndex));
                arrayUnSort.remove(highestIndex);
            }
        }
        arraySort.add(arrayUnSort.get(arrayUnSort.size()-1));
        return arraySort;
    }

    public void TableDriver() throws IndexOutOfBoundsException{
        /* To access the driver class a for each loop is used and also
           to access the points and the race the formula1driver is being set as variable.
           converting them to descending order.
         */

        int number = 1;

        try {
            for (Drivers driver : sortArray()) {
                if (number == 1) {
                    System.out.println("\n|Driver No || Driver Name  || Driver Team  || Driver Location  || No of Race || Total Point |");
                }
                Formula1Driver driverSet = (Formula1Driver) driver;   //  setting an object to access both driver and formula1driver class.
                String AlignFormat = "%3s%20s%18s%18s%16s%16s";
                System.out.printf(AlignFormat, number, driverSet.getDriverName(), driverSet.getDriverTeam()
                        , driverSet.getDriverLocation(), driverSet.getNoOfRace(), driverSet.getPoints() + "\n");
                number++;

            } System.out.println("\n ****************TABLE SHOWED********************\n");
        }catch (IndexOutOfBoundsException e){
            System.out.println("\n******************************* No Drivers to Display  **************************************\n");

        }


    }

    public void changeDriver() {
        /* Showing driver number, name and team before changing  using a boolean to run the loop again
        if the user enters the same vehicle from another driver.
         */
        Scanner input = new Scanner(System.in);
        int number = 1;

        System.out.println("Driver Teams and Names\n");
        for (Drivers driver : drivers) {
            System.out.println(number + "  " + driver.getDriverName() + " " + driver.getDriverTeam());
            number++;
        }
        boolean check=true;
        System.out.println("\nPlease enter the Driver Team ");
        String driverTeam=input.nextLine().toUpperCase();

        for (Drivers driver: drivers) {
            if(driver.getDriverTeam().equals(driverTeam)){
                System.out.println("Please enter the name of new driver ");
                String driverName=input.nextLine().toUpperCase();
                System.out.println("Please enter the location of new driver ");
                String driverLocation=input.nextLine().toUpperCase();

                driver.setDriverName(driverName);
                driver.setDriverLocation(driverLocation);
                check=false;
                System.out.println("The driver details for " + driverTeam +" have been updated");
                System.out.println("\n *************DRIVER CHANGED***********************\n");
            }
        }if (check){
            System.out.println("Please try again it's wrong\n");
            System.out.println("\n ~~~~~~~~~~~~~~~~~~TRY AGAIN~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        }



    }



    public void statDriver() {
        /* Showing the details including the no of race he has gone to what position did he get.
        To bring that first the array should be turned into object. To make the program small created a Arraylist named
        position to get the drivers position and used a for loop to get the no of races and position the user has selected
        driver name on. And points in normal foreach loop.
         */
        Scanner input = new Scanner(System.in);
        int number = 1;

        System.out.println("Driver Teams and Names\n");

        for (Drivers driver : drivers) {
            System.out.println("Driver Number "
                    + number + " Driver Name  " + driver.getDriverName());
            number++;
        }
        System.out.println("\nPlease enter the Driver No to get the stats..");

        int select = input.nextInt();
        Formula1Driver driverSet = (Formula1Driver) drivers.get(select - 1);
        System.out.println("Driver Name - " + driverSet.getDriverName());
        System.out.println("No of Races Driver Participated - " + driverSet.getNoOfRace());

        ArrayList<Integer> positions = driverSet.getPositions();

        for (int i = 0; i < positions.size(); i++) {
            System.out.println("Race " + (i+1) + " - " + positions.get(i) + " Position");
            // System.out.println("Driver Position" + driverSet.getPositions().toString());
        }
        System.out.println("Driver Points - " + driverSet.getPoints());
        System.out.println("********************************************\n");

    }

    public void Race(){

        // Showing the date format
        Date date = new Date();
        System.out.println("---------------------------------------------------------------------\n");
        System.out.println("New Race on "+new SimpleDateFormat("dd-MMM-yyyy").format(date));

        /* Creating an integer array to store the driver array size and using collections method to shuffle the the positions randomly and giving
        the user points according to the position that the drivers got
        */

        Integer[] arr = new Integer[drivers.size()];     // integer array to store the driver array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));   // shuffling it using in built method
        System.out.println("-----------------------------------------------------------------------");
//        System.out.println(Arrays.toString(arr));
        int index =0;
        for (Drivers driver : drivers) {
            Formula1Driver driverGet = (Formula1Driver) driver;
            ArrayList<Integer> positions_ = driverGet.getPositions();

            positions_.add(arr[index]+1);

            driverGet.setPositions(positions_);
            driverGet.setNoOfRace(driverGet.getNoOfRace() + 1);
            driverGet.CalculatePointsForRace();

            index++;
        }


    }


    public void saveFile(){
        try {
            FileOutputStream fo = new FileOutputStream(new File("DriverFile.txt"));
            ObjectOutputStream ob = new ObjectOutputStream(fo);

            // Saving objects inside the file to store the arrays

            ob.writeObject(drivers);

            ob.close();
            fo.close();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }


    public void readFile(){
        try {

            FileInputStream fs = new FileInputStream(new File("DriverFile.txt"));
            ObjectInputStream os = new ObjectInputStream(fs);

            // Read the files which are saved in the object

            drivers = (ArrayList<Drivers>) os.readObject();

            os.close();
            fs.close();

        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Error ClassNotFoundException");
        }
    }



}


// https://stackoverflow.com/questions/1893349/dynamically-converting-java-object-of-object-class-to-a-given-class-when-class-n
// https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/