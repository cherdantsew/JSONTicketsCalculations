import entities.Ticket;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyJSONParser {
    public List getTickets(String path) throws IOException, ParseException {
        List<Ticket> ticketsList = new ArrayList();

        JSONParser parser = new JSONParser();

        JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(path)));

        JSONArray ticketsJSONArray = (JSONArray) jsonObject.get("tickets");

        Iterator iterator = ticketsJSONArray.iterator();

        while (iterator.hasNext()) {

            JSONObject jo = (JSONObject) iterator.next();

            String origin = (String) jo.get("origin");
            String origin_name = (String) jo.get("origin_name");
            String destination = (String) jo.get("destination");
            String destination_name = (String) jo.get("destination_name");
            String departure_date = (String) jo.get("departure_date");
            String departure_time = (String) jo.get("departure_time");
            String arrival_date = (String) jo.get("arrival_date");
            String arrival_time = (String) jo.get("arrival_time");
            String carrier = (String) jo.get("carrier");
            long stops = (long) jo.get("stops");
            long price = (long) jo.get("price");

            ticketsList.add(new Ticket(origin, origin_name, destination, destination_name, departure_date,
                    departure_time, arrival_date, arrival_time, carrier, stops, price));
        }
        return ticketsList;


    }

}
