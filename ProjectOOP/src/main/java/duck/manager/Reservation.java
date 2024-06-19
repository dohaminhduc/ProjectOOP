package duck.manager;

import java.io.Serializable;
import java.util.ArrayList;

public class Reservation implements Serializable {
    private static Reservation instance;
    private ArrayList<Flight> flights;

    public static Reservation getInstance() {
        if (instance == null) {
            instance = new Reservation();
        }
        return instance;
    }
    public Reservation() {
        flights = new ArrayList<Flight>();
        flights = (ArrayList<Flight>) Serializer.deserializeFlights("C:\\ProjectOOP\\database\\flights");

    }
    public ArrayList<Flight> getCars() {
        return flights;
    }
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }
    public ArrayList<Flight> getFlightList(){
        return flights;
    }

}
