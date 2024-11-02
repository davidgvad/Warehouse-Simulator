/**
 * Shipment class, that creates shipment objects for the simulation.
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
class Shipment{
    /**
     * Enums to denote current state of the shipment.
     */
    public enum ShipmentStatus {
        in_route,
        delivered;
    }
    // Instance variables
    public int shipmentId;
    public int sourceId;
    public int destinationId;
    public int size; // Size of the shipment
    public ShipmentStatus status=ShipmentStatus.in_route;
    /**
     * Constructor of the shipment class, which creates shipment objects based on the parameters.
     * @param shipmentId the id we assign to the shipment.
     * @param sourceId the ID of the warehouse where shipment resides.
     * @param destinationId the ID of the warehouse into which shipment must be delivered.
     * @param size the size of the shipment.
     */
    public Shipment(int shipmentId, int sourceId, int destinationId, int size) {
        this.shipmentId = shipmentId;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.size = size;
    }

    /**
     * Gets the size of the shipment.
     * @return the size of the shipment.
     */
    public int getSize(){
        return size;
    }

    /**
     * Gets the ID of the shipment.
     * @return the ID of the shipment.
     */
    public int getShipmentID() {
        return shipmentId;
    }

    /**
     * Gets the ID of the warehouse that shipment resides in.
     * @return the ID of the source warehouse.
     */
    public int getSourceID() {
        return sourceId;
    }

    /**
     * Gets the ID of the warehouse that shipment needs to be delivered to.
     * @return the ID of the destination warehouse.
     */
    public int getDestinationID() {
        return destinationId;
    }

    /**
     * Gets the status of the shipment.
     * @return returns status of the shipment, delivered if delivered, if not status is in_route.
     */
    public ShipmentStatus getStatus(){ 
        return status;
    }

    /**
     * Sets the status of the shipment as delivered.
     */
    public void delivered(){
        status=ShipmentStatus.delivered;
    }

    /**
     * Checks if the status of the shipment is delivered.
     * @return returns true if the shipment is delivered, false otherwise.
     */
    public boolean isDelivered(){
        if(status==ShipmentStatus.delivered){
            return true;
        }
        else{
            return false;
        }
    }

}