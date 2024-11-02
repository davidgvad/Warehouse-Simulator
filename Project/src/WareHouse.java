/**
 * Warehouse class which creates the warehous objects for the simulation.
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
class WareHouse{
    //instance variables
    public int wareHouseId;
    public double x;
    public double y;
    public int dockCount;
    public Queue<Truck> truckQueue; 
    /**
     * Constructor of the warehouse class, which creates certain warehouse objects.
     * @param warehouseid the id we assign to specific warehouse.
     * @param x the x coordinate of the warehouse.
     * @param y the y coordainte of the warehouse.
     * @param dockCount the number of available docks in the warehouse.
     */
    public WareHouse(int warehouseId, double x, double y, int dockCount) {
        this.wareHouseId = warehouseId;
        this.x=x;
        this.y=y;
        this.dockCount = dockCount;
        this.truckQueue = new Queue();
    }
    /**
     * Loads truck into the available docks at the warehouse.
     * @param truck the truck we are loading.
     * @return returns true if it took the spot of the availble dock, false otherwise.
     */
    public boolean loadTruck(Truck truck) {
        if(availableDocks()>0){
            dockCount--;
            deQueue();
            return true;
        }
        else{
            truckQueue.add(truck);
            return false;
        }
    }
    /**
     * Tells the number of available docks.
     * @return returns number of available docks.
     */
    public int availableDocks() {
        return dockCount;
    }
    /**
     * unLoads truck into the available docks at the warehouse.
     * @param truck the truck we are loading.
     * @return returns true if it took the spot of the availble dock, false otherwise.
     */
    public boolean unloadTruck(Truck truck){
        if(availableDocks()>0){
            dockCount--;
            deQueue();
            return true;
        }
        else{
            truckQueue.add(truck);
            return false;
        }
    }
    /**
     * Incrementing the number of avaialble docks.
     */
    public void incDocks(){
        dockCount++;
    }
    /**
     * Removing the first truck waiting in the queue.
     */
    public void deQueue(){
        truckQueue.poll();
    }
    /**
     * Gets the ID of the warehouse object.
     * @return returns ID of the warehouse object.
     */
    public int getID() {
        return wareHouseId;
    }
    /**
     * Gets the X coordinate of the warehouse object
     * @return returns the X coordinate of the warehouse
     */
    public double getX() {
        return x;
    }
    /**
     * Gets the Y coordinate of the warehouse object
     * @return returns the Y coordinate of the warehouse
     */
    public double getY() {
        return y;
    }
}
