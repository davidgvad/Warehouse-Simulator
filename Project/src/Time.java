
/**
 * Ticks the timer for the simulation.
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
public class Time implements Schedule
{
    // Instance variables 
    private ArrayList<Truck> trucks=new ArrayList<>(); // The array of the all trucks presented on the map
    private ArrayList<Shipment> shipments=new ArrayList<>(); // The array of the all shipments presented on the map
    private ArrayList<WareHouse> WareHouses=new ArrayList<>(); // The array of the all WareHouses presented on the map
    /**
     * Constructor of the time class that sets up the environment for the map.
     * @param trucks The array of the trucks that is inputed.
     * @param shipments The array of the shipments that is inputed.
     * @param WareHouses The array of the WareHouses that is inputed.
     */
    public Time(ArrayList<Truck> trucks, ArrayList<Shipment> shipments, ArrayList<WareHouse> WareHouses){
        // Sets the arrays of data for the time class
        this.trucks=trucks;
        this.shipments=shipments;
        this.WareHouses=WareHouses;
        setUp();
    }

    /**
     * Ticks the clock for the simulation, and forces action of the each truck. 
     * 
     */
    public void tick(){
        for(int i=0; i<trucks.size(); i++){
            trucks.get(i).action();
        }
    }

    /**
     * Ticks the clock until all the shipments are delivered in the simulation.
     */
    public void action() {
        boolean allDelivered = false;
        while (!allDelivered) {
            tick();
            allDelivered = true;
            for (int i = 0; i < shipments.size(); i++) {
                if (!shipments.get(i).isDelivered()) {
                    allDelivered = false;
                    break;
                }
            }
        }
        // Denoting the end of the simulation
        log_status();

    }

    /**
     * Logs information into the file that simulation has ended.
     */
    public int log_status(){
        File file = new File("log.txt");
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fw);

            try {
                // Additional information to log after exiting the loop
                writer.newLine(); // Ensure we start on a new line
                writer.write("All shipments have been delivered. The simulation is complete.");
                writer.newLine(); // Move to a new line

            } finally {
                writer.close(); // Make sure to close the writer to save changes
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * Assigns randomly the shipments to the truck.
     */
    public void setUp(){
        // Creating shipments lists, to not modify the main list of the shipments
        ArrayList<Shipment> loads=new ArrayList<>();
        for(int i=0; i<shipments.size(); i++){
            loads.add(shipments.get(i));
        }
        // For each truck assiging the shipment individually, this makes sure that each truck gets at least one shipment
        for(int i=0; i<trucks.size(); i++){
            Random random = new Random();
            int randomInt = random.nextInt(loads.size());
            // Making sure that shipments size isnt bigger than the trucks capacity
            while(trucks.get(i).size<loads.get(randomInt).size){
                randomInt = random.nextInt(loads.size()); 
            }
            trucks.get(i).mainShipments.add(loads.get(randomInt));
            loads.remove(loads.get(randomInt));
        }
        // If there are more shipments left, assign these shipments to the trucks one more time randomly
        if(loads.size()>0){    
            for(int i=0; i<loads.size();i++){
                Random random = new Random();
                int randomInt = random.nextInt(trucks.size());
                // Ensuring the size compatability once again
                while(trucks.get(randomInt).size<loads.get(i).size){
                    randomInt = random.nextInt(trucks.size());
                }
                trucks.get(randomInt).mainShipments.add(loads.get(i));
            }
        }
        // Lastly, creating the mainShipmentsTest arrya, and giving the WareHouse, array to all the trucks 
        for(int i=0; i<trucks.size(); i++){
            for(int k=0; k<trucks.get(i).mainShipments.size(); k++){
                trucks.get(i).mainShipmentsTest.add(trucks.get(i).mainShipments.get(k));
            }
            trucks.get(i).WareHouseInjection(WareHouses);
        }
    }
}
