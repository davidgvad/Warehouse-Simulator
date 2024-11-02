

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TruckTest.
 *
 * @author  David Gvadzabia
 * @version 5.2.1
 */
public class TruckTest
{
    @Test
    public void TruckTest()
    {
        // Based on my created Truck, WareHouse, and Shipments file, i have three trucks, and i assign first three shipments to the first truck, the second three to the second one, and last three
        // to the last truck. Based on this I unit test the simulation and check every aspect of my environment
        LoadData data=new LoadData();
        ArrayList<Truck> trucks=data.loadTrucks("Truck.txt");
        ArrayList<Shipment> shipments=data.loadShipments("Shipment.txt");
        ArrayList<WareHouse> WareHouses=data.loadWarehouses("WareHouse.txt");
        for(int i=0; i<shipments.size(); i++){
            if(i<3){
                trucks.get(0).mainShipments.add(shipments.get(i));
                trucks.get(0).mainShipmentsTest.add(shipments.get(i));
            }
            if(i>2 && i<6){
                trucks.get(1).mainShipments.add(shipments.get(i));
                trucks.get(1).mainShipmentsTest.add(shipments.get(i));
            }
            if(i>5){
                trucks.get(2).mainShipments.add(shipments.get(i));
                trucks.get(2).mainShipmentsTest.add(shipments.get(i));
            }
        }
        trucks.get(0).WareHouseInjection(WareHouses);
        trucks.get(1).WareHouseInjection(WareHouses);
        trucks.get(2).WareHouseInjection(WareHouses);

        // Test 1 testing that first truck chose shipment 2 and also is already loading.
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 2);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(1));
        // Test 2 testing that second truck chose shipment 4 and is still in the road
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(0));
        // Test 3 testing that third truck chose  shipment 4 and is still in the road 
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(0)); 
        // Test 4 testing that first truck still has 2 ship chosen, and is loading
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 2);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(1));
        // Test 5 testing that second truck chose shipment 4 and is still on the road
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(0));
        // Test 6 testing that third truck still chose shipment 8 and is loading
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(1)); 
        // Test 7 testing that first truck chose shipment 3 and is in transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 8 testing that second truck chose shipment 4 and is still on the road
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(0));
        // Test 9 testing that third truck chose  shipment 8 and is loading
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(1)); 
        // Test 10 testing that first truck chose shipment 3 and is transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 12 testing that second truck chose shipment 4 and is loading
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(1));
        // Test 13 testing that third truck chose shipment 9 and is transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 9);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3)); 
        // Test 14 testing that firsttruck chose shipment 3 and is transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 15 testing that second truck chose shipment 4 and is loading
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(1));
        // Test 16 testing that third truck chose shipment 9 and is loading
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 9);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(1)); 
        // Test 17 testing that first truck chose shipment 3 and is in transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 18 testing that second truck chose shipment 5 and is loading
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 5);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(1));
        // Test 19 testing that third truck chose shipment 9 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 9);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3));
        // Test 20 testing that first truck chose shipment 3 and is in transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 21 testing that second truck chose shipment 5 and is in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 5);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 22 testing that third truck chose shipment 9 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 9);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3)); 
        // Test 23 testing that first truck chose shipment 3 and is in loading
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(1));
        // Test 24 testing that second truck chose shipment 5 and is in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 5);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 25 testing that third truck chose shipment 9 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 9);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3)); 
        // Test 26 testing that frist truck chose shipment 3 and is loading
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(1));
        // Test 27 testing that second truck chose shipment 5 and is in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 5);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 28 testing that third truck chose shipment 9 and is unloading 
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 9);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(2)); 
        // Test 29 testing that frist truck chose shipment 3 and is in trasnit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 30 testing that second truck chose shipment 5 and is in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 5);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 31 testing that third truck chose shipment 9 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 9);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3)); 
        // Test 32 testing that frist truck chose shipment 3 and is in transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 33 testing that second truck chose shipment 5 and is unloading
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 5);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(2));
        // Test 34 testing that third truck chose shipment 8 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3)); 
        // Test 35 testing that first truck chose shipment 3 and is unloading
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(2));
        // Test 36 testing that second truck chose shipment 5 and is in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 5);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 37 testing that third truck chose shipment 8 and is in transit with load 
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3)); 
        // Test 38 testing that first truck chose shipment 3 and is in transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 3);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 39 testing that second truck chose shipment 4 and is in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 40 testing that third truck chose shipment 8 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3)); 
        // Test 41 testing that first truck chose shipment 2 and is unloading
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 2);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(2));
        // Test 42 testing that second truck chose shipment 4 and is pending
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(4));
        // Test 43 testing that third truck chose shipment 8 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3)); 
        // Test 44 testing that first truck chose shipment 2 and is in transit
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 2);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(0));
        // Test 45 testing that second truck chose shipment 4 and is unloading
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(2));
        // Test 46 testing that third truck chose shipment 8 and is unloading
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(2));
        // Test 47 testing that first truck chose shipment 1 and is pending
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 1);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(4));
        // Test 48 testing that second truck chose shipment 4 and is in transit 
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 4);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(0));
        // Test 49 testing that third truck chose shipment 8 and is in transit 
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 8);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(0));
        // Test 50 testing that first truck chose shipment 1 and loading
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 1);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(1));
        // Test 51 testing that second truck chose shipment 6 and is in transit
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 6);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(0));
        // Test 52 testing that third truck chose shipment 7 and in transit
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(0)); 
        // Test 53 testing that first truck chose shipment 1 and in transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 1);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 54 testing that second truck chose shipment 6 and loading
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 6);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(1));
        // Test 55 testing that third truck chose shipment 7 and is in transit
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(0)); 
        // Test 56 testing that first truck chose shipment 1 and is in transit with load
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 1);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(3));
        // Test 57 testing that second truck chose shipment 6 and loading
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 6);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(1));
        // Test 58 testing that third truck chose shipment 7 and is in transit
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(0)); 
        // Test 59 testing that first truck chose shipment 1 and unloading
        trucks.get(0).action();
        assertEquals(trucks.get(0).chosenShipment.getShipmentID(), 1);
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(2));
        // Test 60 testing that second truck chose shipment 6 and in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 6);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 61 testing that third truck chose shipment 7 and in transit 
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(0));
        // Test 62 testing that first truck is done
        trucks.get(0).action();
        assertEquals(trucks.get(0).getStatus(), trucks.get(0).statusArray.get(5));
        // Test 63 testing that second truck chose shipment 6 and in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 6);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 64 testing that third truck chose shipment 7 and is loading
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(1)); 
        // Test 65 testing that second truck chose shipment 6 and in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 6);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 66 testing that third truck chose shipment 7 and is loading
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(1));
        // Test 67 testing that second truck chose shipment 6 and is in transit with load
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 6);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(3));
        // Test 68 testing that third truck chose shipment 7 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3));
        // Test 69 testing that second truck chose shipment 6 and is unloading
        trucks.get(1).action();
        assertEquals(trucks.get(1).chosenShipment.getShipmentID(), 6);
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(2));
        // Test 70 testing that third truck chose shipment 7 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3));
        // Test 71 testing that second truck is done
        trucks.get(1).action();
        assertEquals(trucks.get(1).getStatus(), trucks.get(1).statusArray.get(5));
        // Test 72 testing that third truck chose shipment 7 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3));
        // Test 70 testing that third truck chose shipment 7 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3));
        // Test 70 testing that third truck chose shipment 7 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3));
        // Test 70 testing that third truck chose shipment 7 and is in transit with load
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(3));
        // Test 70 testing that third truck chose shipment 7 and is unloading
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(2));
        // Test 70 testing that third truck is done
        trucks.get(2).action();
        assertEquals(trucks.get(2).chosenShipment.getShipmentID(), 7);
        assertEquals(trucks.get(2).getStatus(), trucks.get(2).statusArray.get(5));  
    }
}
