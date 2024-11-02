/**
 * Class that creates instances of trucks.
 * 
 * @author David Gvadzabia
 * @version 5.2.1
 */
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
class Truck implements Schedule{
    /**
     * Enums to denote the status of the truck.
     */
    public enum ShipmentStatus {
        PENDING,
        IN_TRANSIT,
        LOADING,
        UNLOADING,
        IN_TRANSIT_WITH_LOAD,
        DONE
    }

    // Instance variables
    public int truckId; // Denotes the id of the truck
    public double speed; // The speed of the truck
    public double currentX; // Current X coordinate of the truck
    public double currentY; // Current Y coordainte of the truck

    public ArrayList<Shipment> mainShipments=null; // The Shipments assigned to the current truck
    public ShipmentStatus status = ShipmentStatus.IN_TRANSIT; // The status, or in other words what the truck is doing currently
    public boolean headingToDestination = false; // Boolean which checks if the truck is headed towards the destination, or the source
    public double distanceToSource = 0; // Denotes the distance to the source
    public double distanceToDestination = 0; // Denotes the distance to the destination
    public int size; // Denotes the size of the current truck
    public Shipment chosenShipment; // Denotes the currently chosen shipment towards which the truck is heading
    public ArrayList <Shipment> loadedShipments; // The array of the shipments that are currently on the truck
    public ArrayList <WareHouse> WareHouse; // The array of the WareHouses present on the map
    public int time = 0; // Indicates what time is it
    public ArrayList <Shipment> mainShipmentsTest; // Analog to the mainShipments array which stores the assigned shipments, however is used to modify list without modify the mainShipments
    public int takenSpace; // Indicates the taken space of the truck
    public boolean onRoad = false; // Indicates if the truck has just came of the road to the warehouse
    public ArrayList <ShipmentStatus> statusArray; // The array of Enums (used for the unit test class)

    /**
     * The constructor of the Truck class, which initaites the truck objects based on the parameters.
     * @param TruckId The ID that is assigned to the truck.
     * @param Capacity The size of the current truck.
     * @param Speed The speed of the truck.
     * @param StartX The starting X coordinate of the truck.
     * @param StartY the starting Y coordinate of the truck.
     */

    public Truck(int TruckId, int Capacity, double Speed, double StartX, double StartY){
        this.truckId = TruckId;
        this.size = Capacity;
        this.speed = Speed;
        this.currentX = StartX;
        this.currentY = StartY;
        // Instantiating the array of the shipments
        this.mainShipments = new ArrayList<>();
        this.loadedShipments = new ArrayList<>();
        this.mainShipmentsTest = new ArrayList<>();
        // Creating enums array to be accessed outside of the class
        this.statusArray = new ArrayList<>();
        statusArray.add(ShipmentStatus.IN_TRANSIT);
        statusArray.add(ShipmentStatus.LOADING);
        statusArray.add(ShipmentStatus.UNLOADING);
        statusArray.add(ShipmentStatus.IN_TRANSIT_WITH_LOAD);
        statusArray.add(ShipmentStatus.PENDING);
        statusArray.add(ShipmentStatus.DONE);
        // Assigning values to the mainShipmentsTest indirectly so that the changes on it wont affect the mainShipments itself
        for(int i = 0; i<mainShipments.size(); i++){
            mainShipmentsTest.add(mainShipments.get(i));
        }
    }

    /**
     * Gives the truck array of the available Warehouses on the map.
     * @param WareHouse The array containing all Warehouses.
     */
    public void WareHouseInjection(ArrayList <WareHouse> WareHouse){
        this.WareHouse = WareHouse;
    }

    /**
     * Makes the neccesary actions that trucks needs to do.
     */
    public void action(){
        // If the truck is in transit without any shipments enter this if statement
        if(status == ShipmentStatus.IN_TRANSIT){
            // If you are arent on the road, which means this is your start, choose the closest shipment source and go towards it
            if(onRoad == false){
                chooseSourceShip(mainShipments);
                log_status();
                // As information was stored continue the path
                move();
            }
            // If you are already on the road, means you didnt get to the source, so log the information and continue the path
            else{
                log_status();
                move();
            }
        }
        // If status is loading enter this if statement
        else if(status == ShipmentStatus.LOADING){
            // Count used to denote if we just loaded shipment
            int count = 0;
            for(int i = 0; i<mainShipmentsTest.size(); i++){
                // If there is the shipment that has same Source as chosen one and it fits the truck load it and make it chosen shipment based on LIFO
                if(mainShipmentsTest.get(i).getSourceID() == chosenShipment.getSourceID() && size >= takenSpace+mainShipmentsTest.get(i).size){
                    takenSpace += mainShipmentsTest.get(i).size;
                    loadedShipments.add(mainShipmentsTest.get(i));
                    chosenShipment = mainShipmentsTest.get(i);
                    distanceToSource =0 ;
                    distanceToDestination = Math.sqrt((SourceWareHouse(chosenShipment).getX()-DestinationWareHouse(chosenShipment).getX())*(SourceWareHouse(chosenShipment).getX()-DestinationWareHouse(chosenShipment).getX())+(SourceWareHouse(chosenShipment).getY()-DestinationWareHouse(chosenShipment).getY())*(SourceWareHouse(chosenShipment).getY()-DestinationWareHouse(chosenShipment).getY()));
                    // Remove this shipment from the shipments list and re-enter the loop to find more shipments that are on this source
                    mainShipmentsTest.remove(mainShipmentsTest.get(i));
                    count++;
                    break;
                }
            }
            if(count == 1){
                // Reseting count to check if there are more shipments left
                count = 0;
                // Saving information that some shipment was just loaded
                log_status();
            }
            else{
                // If count is 0 means there are no more shipments left on this source, which means status now becomes in transit with load
                status = ShipmentStatus.IN_TRANSIT_WITH_LOAD;
                // Free up docks because its done loading and embarks on the road
                SourceWareHouse(chosenShipment).incDocks();
                // Since we got shipment head to destination
                headingToDestination = true;
                chooseShip(loadedShipments);
                // Save the information
                log_status();
            }
        }
        // If the status if in transit with load enter this if statement
        else if(status == ShipmentStatus.IN_TRANSIT_WITH_LOAD){
            // If coming from the road you have to choose your next plan. Either unload the current chosen shipment, or load another one which is closer, if it fits
            if(onRoad == false){
                // Choosing next shipment and move towards it 
                chooseShip(loadedShipments);
                move();
                // ChooseShip updates your status and once move moves you towards it, if the status changed to loading, means you went for another shipment to pick up so load it into the loaded shipments.
                if(status == ShipmentStatus.LOADING){

                    loadedShipments.add(chosenShipment);

                    takenSpace += chosenShipment.size;

                    mainShipmentsTest.remove(chosenShipment);
                    // Since we have just arrived to pick up at source
                    distanceToSource = 0;
                }
                // Save the information
                log_status();     
            }
            // If you still on the road continue path, and save the information
            else{
                move();
                log_status();
            }
        }
        // If the status is unloading, enter this if statement
        else if(status == ShipmentStatus.UNLOADING){
            // Denote the chosen shipment as delivered
            chosenShipment.delivered();
            // Remove it from the arrays
            mainShipments.remove(chosenShipment);
            loadedShipments.remove(chosenShipment);
            // Free up docks, since it has just unloaded
            DestinationWareHouse(chosenShipment).incDocks();
            // Update the available space on the truck
            takenSpace -= chosenShipment.size;
            // If there are more shipments on the truck, next status would be in transit with load
            if(loadedShipments.size()>0){
                status = ShipmentStatus.IN_TRANSIT_WITH_LOAD;
                // Update status and save information
                log_status();
            }
            else{
                // If there are no more shipments on the truck, go on the road without shipments and choose the closest source that contains trucks potential shipments
                status = ShipmentStatus.IN_TRANSIT;
                // Heading to source
                headingToDestination = false;
                if(mainShipmentsTest.size() != 0){
                    // Saves information
                    log_status();
                }
            }
            // If there are no more shipments on the mainShipments list means, the truck is done.
            if(mainShipments.size() == 0){          
                status = ShipmentStatus.DONE;
            }
        }
        // If truck is waiting at the warehouse enter this if statement
        else if(status == ShipmentStatus.PENDING){
            // If truck isnt heading to destination means its at the source ware house
            if(headingToDestination == false){
                // Check if there are spaces available for the truck to embark on loadment
                if(SourceWareHouse(chosenShipment).availableDocks()>0){
                    if(SourceWareHouse(chosenShipment).loadTruck(this) == true){
                        // If true the status becomes loading
                        this.status = ShipmentStatus.LOADING;
                    }
                    // So truck loads its chosen shipment, and saves the information
                    loadedShipments.add(chosenShipment);
                    takenSpace += chosenShipment.size;
                    mainShipmentsTest.remove(chosenShipment);
                    // Distance to source is zero, since truck is at the source
                    distanceToSource = 0;
                    log_status();
                }
                else{
                    // If there are no spaces, just save the infromation that truck is waiting
                    log_status();
                }
            }
            else{
                // If the truck was headed towards destination means its waiting at the destination
                if(DestinationWareHouse(chosenShipment).availableDocks()>0){
                    // Check if there are spaces available for the truck to embark on unloadment
                    if(DestinationWareHouse(chosenShipment).unloadTruck(this) == true){
                        // If true the status becomes unloading
                        this.status = ShipmentStatus.UNLOADING;
                    };
                    // Truck saves information, and transmits to unloading shipment if statement on the next clock tick 
                    log_status();
                }
                else{
                    // If there are no spaces wait at the destination
                    log_status();
                }
            }
        }

    }

    /**
     * Moves the truck on the map and updates its status.
     */
    public void move(){
        if(headingToDestination){
            // If the speed is enough to travel the distance enter the if statement
            if(speed >= distanceToDestination){
                // Update on road which means truck isnt on the road anymore
                onRoad = false;
                // If there are avaialble docks unload truck since, it was heading towards destination
                if(DestinationWareHouse(chosenShipment).availableDocks() > 0){
                    // Update its status and coordinates
                    status = ShipmentStatus.UNLOADING;
                    currentX = DestinationWareHouse(chosenShipment).getX();
                    currentY = DestinationWareHouse(chosenShipment).getY();
                    distanceToDestination = 0;
                    // Manage warehouse queue
                    DestinationWareHouse(chosenShipment).unloadTruck(this);
                }
                else{
                    // If there are no available docks, status becomes pending
                    status = ShipmentStatus.PENDING;
                    // Update the coordaintes, and process the truck at the warehouse
                    currentX = DestinationWareHouse(chosenShipment).getX();
                    currentY = DestinationWareHouse(chosenShipment).getY();
                    distanceToDestination = 0;
                    DestinationWareHouse(chosenShipment).unloadTruck(this);
                }
            }
            else{
                // If the speed and time isnt enough, truck stays on the road, but the distance decreases
                onRoad = true;
                this.distanceToDestination = distanceToDestination-speed;
            }
        }
        else{
            // In this case truck is heading towards the source
            if(speed >= distanceToSource){
                // Update on road since truck has embarked on the loadment
                onRoad = false;
                if(SourceWareHouse(chosenShipment).availableDocks()>0){
                    // Change status to loading and update coordaintes aswell
                    status = ShipmentStatus.LOADING;
                    currentX = SourceWareHouse(chosenShipment).getX();
                    currentY = SourceWareHouse(chosenShipment).getY();
                    // Process truck at the warehouse
                    SourceWareHouse(chosenShipment).loadTruck(this);
                    distanceToSource = 0;
                }
                else{
                    // If no spaces available, truck is waiting at the source
                    status = ShipmentStatus.PENDING; 
                    currentX = SourceWareHouse(chosenShipment).getX();
                    currentY = SourceWareHouse(chosenShipment).getY();
                    SourceWareHouse(chosenShipment).loadTruck(this);
                    distanceToSource = 0;
                }
            }
            else{
                // If the speed and time isnt enough, truck is still on the way to source, but distance decreases
                onRoad = true;
                this.distanceToSource -= speed;
            }
        }
    }

    /**
     * Based on the shipment identifies the source warehouse that shipment is contained within.
     * @param ship The shipment which source warehouse program is looking for.
     * @return Returns the source warehouse.
     */
    public WareHouse SourceWareHouse(Shipment ship){
        WareHouse output = null;
        for(int i = 0; i<WareHouse.size(); i++){
            if(ship.getSourceID() == WareHouse.get(i).getID()){
                output = WareHouse.get(i);            
            }
        }
        return output;
    }

    /**
     * Based on the shipment identifies the destination warehouse that shipment is contained within.
     * @param ship The shipment which destination warehouse program is looking for.
     * @return Returns the destination warehouse.
     */

    public WareHouse DestinationWareHouse(Shipment ship){
        WareHouse output = null;
        for(int i = 0; i<WareHouse.size(); i++){
            if(ship.getDestinationID() == WareHouse.get(i).getID()){
                output = WareHouse.get(i);            
            }
        }
        return output;
    }

    /**
     * Chooses the shipment which has its source warehouse closest to the truck.
     * @param shipment The array of the shipments from which program identifies the closest shipment's source.
     * 
     */
    public void chooseSourceShip(ArrayList <Shipment> shipment){    
        double minDistance = Double.MAX_VALUE;
        Shipment ship = null;
        for(int i = 0; i<shipment.size(); i++){
            if(distance(SourceWareHouse(shipment.get(i)).getX(), SourceWareHouse(shipment.get(i)).getY())<minDistance){
                minDistance = distance(SourceWareHouse(shipment.get(i)).getX(), SourceWareHouse(shipment.get(i)).getY());
                ship = shipment.get(i);
            }
            else if(distance(SourceWareHouse(shipment.get(i)).getX(), SourceWareHouse(shipment.get(i)).getY()) == minDistance){
                if(shipment.get(i).getShipmentID()<ship.getShipmentID()){
                    ship = shipment.get(i);
                }
            }
        }
        this.distanceToDestination = Math.sqrt((SourceWareHouse(ship).getX()-DestinationWareHouse(ship).getX())*(SourceWareHouse(ship).getX()-DestinationWareHouse(ship).getX())+(SourceWareHouse(ship).getY()-DestinationWareHouse(ship).getY())*(SourceWareHouse(ship).getY()-DestinationWareHouse(ship).getY()));
        chosenShipment = ship;
        this.distanceToSource = minDistance;
    }

    /**
     * Determines whether to unload on board existing shipments or to go for the loadment of the new shipment based on the proximity and space on the truck.
     * @shipment The array of the shipments that are on the truck.
     */
    public void chooseShip(ArrayList <Shipment> shipment){    
        Shipment ship = null;
        int leftSpace = size-takenSpace;
        // The last shipment on the truck 
        ship = loadedShipments.get(loadedShipments.size()-1);
        // Calucalting the distance from truck to the destination of the last shipment
        double distance = distance(DestinationWareHouse(ship).getX(), DestinationWareHouse(ship).getY());
        ArrayList <Shipment> restShipments = new ArrayList <>();
        int count = 0;
        // Creating the array of the rest shipments assigned to the truck that are not on the truck 
        for(int i = 0; i<mainShipments.size(); i++){
            for(int k = 0; k<loadedShipments.size(); k++){
                if (loadedShipments.get(k) == mainShipments.get(i)){
                    count++;
                }   
            }
            if(count == 0){

                restShipments.add(mainShipments.get(i));
            }
            else{
                count = 0;
            }
        }

        if(restShipments.size() != 0){
            chooseSourceShip(restShipments);
            // Checking if there are shipments that are not on the board, which are closer to the truck than the destination of the last embarked shipment.
            while(chosenShipment.size > leftSpace || distanceToSource >= distance){
                restShipments.remove(chosenShipment);
                if(restShipments.size() == 0){
                    break;
                }
                chooseSourceShip(restShipments);
            }
            // If the size of the rest of the shipments array is zero means program couldnt find the closer shipment, so the truck goes for the unloadment of the current shipment on board
            if(restShipments.size() == 0){
                distanceToSource = 0;
                distanceToDestination = distance;     
                chosenShipment = ship;
            }
            // If size is not zero, means there is source ware house that is closer than the destination of the last shipment, which means truck goes for the loadment of that shipment
            else{
                headingToDestination = false;
                restShipments.remove(chosenShipment);
            }
        }
        // If there are no rest shipments, just go for the unloadment of the last shipment on the truck 
        else{
            distanceToSource = 0;
            distanceToDestination = distance;     
            chosenShipment = ship;
        }

    }
    /**
     * Calculates distance between truck and certain x, y coordinates.
     * @param x The inputed x coordinate.
     * @param y The inputed y coordinate.
     * @return Returns the distance between truck and the point.
     */
    public double distance(double x, double y){
        double output = (currentX - x)*(currentX - x) + (currentY - y)*(currentY - y);
        return Math.sqrt(output);
    }

    /**
     * Gets the current status of the truck.
     * @return The status of the truck.
     */
    public ShipmentStatus getStatus(){
        // Used for unit test purposes
        return status;
    }

    /**
     * Logs the infomration into the file, after every clock tick.
     * @return Purposeless integer.
     */
    public int log_status() {
        // Increment clock tick after every log, since every truck logs once per tick
        time++;
        // Creating the file to log into 
        File file = new File("log.txt");
        try {
            boolean isNewFile = file.createNewFile(); // This will create a new file if it doesn't exist and returns true if the file was created.
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fw);

            if (isNewFile || file.length() == 0) { // Check if the file was just created or is empty
                writer.write("Truck ID, Action, Warehouse ID, Chosen Shipment ID, Number of Shipments, Time");
                writer.newLine();
            }
            writer.write("" + "\n");
            // Based on different conditions, loging the information into the file 
            try {
                if (this.status == ShipmentStatus.IN_TRANSIT && !headingToDestination) {
                    writer.write(truckId + ", " + "going to source" + ", " + "Warehouse " + SourceWareHouse(chosenShipment).getID() + ", " + chosenShipment.getShipmentID() + ", " + loadedShipments.size() + ", " + time + " hours ");
                    writer.newLine();
                }
                if (this.status == ShipmentStatus.IN_TRANSIT && headingToDestination) {
                    writer.write(truckId + ", " + "going to destination" + ", " + "Warehouse " + DestinationWareHouse(chosenShipment).getID() + ", " + chosenShipment.getShipmentID() + ", " + loadedShipments.size() + ", " + time + " hours ");
                    writer.newLine();
                }
                if (this.status == ShipmentStatus.UNLOADING) {
                    writer.write(truckId + ", " + "unloading" + ", " + "Warehouse " + DestinationWareHouse(chosenShipment).getID() + ", " + chosenShipment.getShipmentID() + ", " + loadedShipments.size() + ", " + time + " hours ");
                    writer.newLine();
                }
                if (this.status == ShipmentStatus.LOADING) {
                    writer.write(truckId + ", " + "loading" + ", " + "Warehouse " + SourceWareHouse(chosenShipment).getID() + ", " + chosenShipment.getShipmentID() + ", " + loadedShipments.size() + ", " + time + " hours ");
                    writer.newLine();
                }
                if (this.status == ShipmentStatus.PENDING && !headingToDestination) {
                    writer.write(truckId + ", " + "waiting at source" + ", " + "Warehouse " + SourceWareHouse(chosenShipment).getID() + ", " + chosenShipment.getShipmentID() + ", " + loadedShipments.size() + ", " + time + " hours ");
                    writer.newLine();
                }
                if (this.status == ShipmentStatus.PENDING && headingToDestination) {
                    writer.write(truckId + ", " + "waiting at destination" + ", " + "Warehouse " + DestinationWareHouse(chosenShipment).getID() + ", " + chosenShipment.getShipmentID() + ", " + loadedShipments.size() + ", " + time + " hours ");
                    writer.newLine();
                }
                if (this.status == ShipmentStatus.IN_TRANSIT_WITH_LOAD && headingToDestination) {
                    writer.write(truckId + ", " + "going to destination with load" + ", " + "Warehouse " + DestinationWareHouse(chosenShipment).getID() + ", " + chosenShipment.getShipmentID() + ", " + loadedShipments.size() + ", " + time + " hours ");
                    writer.newLine();
                }
                if (this.status == ShipmentStatus.IN_TRANSIT_WITH_LOAD && !headingToDestination) {
                    writer.write(truckId + ", " + "going to source with load" + ", " + "Warehouse " + SourceWareHouse(chosenShipment).getID() + ", " + chosenShipment.getShipmentID() + ", " + loadedShipments.size() + ", " + time + " hours ");
                    writer.newLine();
                }
                if(this.status==ShipmentStatus.DONE){
                    writer.write("Simulation is complete");
                    writer.newLine();

                }
            } finally {
                writer.close();
            }
            return 0;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

}