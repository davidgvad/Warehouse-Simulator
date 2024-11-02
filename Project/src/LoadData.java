
/**
 * Class used for creating arrayLists for the Truck, Warehouse, and shipment objects.
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

public class LoadData
{
    /**
     * Creates the arrayList of the truck objects given in the file.
     * @param filename the file from which we get infromation about trucks.
     * @return returns arrayList of trucks.
     * @throws FileNotFoundException if the file specified by filename does not exist.
     */
    public ArrayList<Truck> loadTrucks(String filename) {
        ArrayList<Truck> truckList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.nextLine(); // Skip header line
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int truckId = Integer.parseInt(data[0]);
                int capacity = Integer.parseInt(data[1]);
                double speed = Double.parseDouble(data[2]);
                double startX = Double.parseDouble(data[3]);
                double startY = Double.parseDouble(data[4]);
                truckList.add(new Truck(truckId, capacity, speed, startX, startY));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found");
        }
        return truckList;
    }

    /**
     * Creates the arrayList of the shipment objects given in the file.
     * @param filename the file from which we get infromation about shipments.
     * @return returns arrayList of shipments.
     * @throws FileNotFoundException if the file specified by filename does not exist.
     */
    public ArrayList<Shipment> loadShipments(String filename) {
        ArrayList<Shipment> shipmentList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.nextLine(); // Skip header line
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int shipmentId = Integer.parseInt(data[0]);
                int sourceId = Integer.parseInt(data[1]);
                int destinationId = Integer.parseInt(data[2]);
                int size = Integer.parseInt(data[3]);
                shipmentList.add(new Shipment(shipmentId, sourceId, destinationId, size));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found");
        }
        return shipmentList;
    }

    /**
     * Creates the arrayList of the warehouse objects given in the file.
     * @param filename the file from which we get infromation about warehouses.
     * @return returns arrayList of warehouses.
     * @throws FileNotFoundException if the file specified by filename does not exist.
     */
    public ArrayList<WareHouse> loadWarehouses(String filename) {
        ArrayList<WareHouse> warehouseList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.nextLine(); // Skip header line
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int warehouseId = Integer.parseInt(data[0]);
                double x = Double.parseDouble(data[1]);
                double y = Double.parseDouble(data[2]);
                int dockCount = Integer.parseInt(data[3]);
                warehouseList.add(new WareHouse(warehouseId, x, y, dockCount));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found");
        }
        return warehouseList;
    }
}
