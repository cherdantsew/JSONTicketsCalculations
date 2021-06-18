import entities.Ticket;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Main {

    public static final String JSON_FILE_PATH = "D:\\программирование\\tickets.json";

    public static void main(String[] args) throws ParseException, IOException {
        List<Ticket> ticketList = new MyJSONParser().getTickets(JSON_FILE_PATH);
        Ticket ticket = new Ticket();
        ticket.calculateAverageFlightTime(ticketList);
        ticket.calculatePercentile(ticketList, 90);
    }
}
