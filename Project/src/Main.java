import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main
{
    /**
     * Either takes the files as inputs, for creating trucks, warehouses, and shipments arrays, or generates them randomly.
     */
    public static void main(String args[]) throws java.io.IOException {

        try {
            LoadData data = new LoadData();
            // If there is input present read these files and create the arrays based on these files
            if (args.length != 0) {
                ArrayList<Truck> trucks = data.loadTrucks(args[0]);
                ArrayList<Shipment> shipments = data.loadShipments(args[1]);
                ArrayList<WareHouse> WareHouses = data.loadWarehouses(args[2]);
                Time clock = new Time(trucks, shipments, WareHouses);
                clock.action();
            } else {
                // If there were no inputs create everything randomly
                Random random = new Random();
                File wareHouseFile = new File("RandomWareHouse.txt");
                int numberOfWarehouses = 2 + random.nextInt(10); // Generate a random number of warehouses between 1 and 10

                FileWriter anotherWriter = new FileWriter(wareHouseFile);
                BufferedWriter wareHouseWriter = new BufferedWriter(anotherWriter);
                // Write the header to the file
                wareHouseWriter.write("warehouseId,x,y,dockCount\n");

                for (int warehouseId = 1; warehouseId <= numberOfWarehouses; warehouseId++) {
                    int x = random.nextInt(26); // Random x between 0 and 25
                    int y = random.nextInt(26); // Random y between 0 and 25
                    int dockCount = 1 + random.nextInt(3); // Random dock count between 1 and 3

                    // Format and write the data in CSV format
                    wareHouseWriter.write(String.format("%d,%d,%d,%d\n", warehouseId, x, y, dockCount));
                }
                wareHouseWriter.close();
                File truckFile = new File("RandomTruck.txt");
                int numberOfTrucks = 1 + random.nextInt(10); // Generate a random number of trucks between 1 and 10
                int maxCapacity = 0; // Variable to track the maximum capacity encountered

                FileWriter newWriter = new FileWriter(truckFile);
                BufferedWriter filer = new BufferedWriter(newWriter);
                // Write the header to the file
                filer.write("truckId,capacity,speed,startX,startY\n");

                for (int truckId = 1; truckId <= numberOfTrucks; truckId++) {
                    int capacity = 2 + random.nextInt(4); // Random capacity between 2 and 5
                    if (capacity > maxCapacity) {
                        maxCapacity = capacity; // Update maxCapacity if current capacity is larger
                    }
                    int speed = 6 - capacity; // Speed calculated as 6 - capacity
                    int startX = random.nextInt(25); // Random startX between 0 and 24
                    int startY = random.nextInt(25); // Random startY between 0 and 24

                    // Format and write the data in CSV format
                    filer.write(String.format("%d,%d,%d,%d,%d\n", truckId, capacity, speed, startX, startY));
                }
                filer.close(); 
                File shipmentFile = new File("RandomShipment.txt");
                int size=0;
                int numberOfShipments = 1 + random.nextInt(11); // Generate a random number of shipments between 1 and 10
                while(numberOfShipments<numberOfTrucks){
                    numberOfShipments = 1 + random.nextInt(11); // Making sure the number of shipments are always greater than equal to trucks
                }
                FileWriter shipmentWriter = new FileWriter(shipmentFile);
                BufferedWriter shipmentBuffer = new BufferedWriter(shipmentWriter);
                // Write the header to the file
                shipmentBuffer.write("shipmentId,sourceId,destinationId,size\n");
                for (int shipmentId = 1; shipmentId <= numberOfShipments; shipmentId++) {
                    int sourceId = 1 + random.nextInt(numberOfWarehouses); // Random sourceId between 1 and the number of warehouses
                    int destinationId = 1 + random.nextInt(numberOfWarehouses); // Random destinationId between 1 and the number of warehouses
                    // Ensure sourceId and destinationId are not the same
                    while (sourceId == destinationId) {
                        destinationId = 1 + random.nextInt(numberOfWarehouses);
                    }
                    size = random.nextInt(3); // Random size between 1 and 3

                    // Format and write the data in CSV format
                    shipmentBuffer.write(String.format("%d,%d,%d,%d\n", shipmentId, sourceId, destinationId, size));
                }
                shipmentBuffer.close();
                // Close the BufferedWriter to ensure all data is written and resources are released
                // Creating the files 
                ArrayList<Truck> trucks = data.loadTrucks("RandomTruck.txt");
                ArrayList<Shipment> shipments = data.loadShipments("RandomShipment.txt");
                ArrayList<WareHouse> WareHouses = data.loadWarehouses("RandomWareHouse.txt");
                Time clock = new Time(trucks, shipments, WareHouses);
                clock.action();
            }

        } catch (java.io.IOException e) {
            // Handle IO Exception here
            e.printStackTrace();
        }

    }
}