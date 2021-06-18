package entities;

import java.util.*;

public class Ticket {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    private String departure_date;
    private String departure_time;
    private String arrival_date;
    private String arrival_time;
    private String carrier;
    private long stops;
    private long price;

    public Ticket(String origin, String origin_name, String destination, String destination_name, String departure_date, String departure_time, String arrival_date, String arrival_time, String carrier, long stops, long price) {
        this.origin = origin;
        this.origin_name = origin_name;
        this.destination = destination;
        this.destination_name = destination_name;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    public Ticket() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return stops == ticket.stops && price == ticket.price && origin.equals(ticket.origin) && origin_name.equals(ticket.origin_name) && destination.equals(ticket.destination) && destination_name.equals(ticket.destination_name) && departure_date.equals(ticket.departure_date) && departure_time.equals(ticket.departure_time) && arrival_date.equals(ticket.arrival_date) && arrival_time.equals(ticket.arrival_time) && carrier.equals(ticket.carrier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, origin_name, destination, destination_name, departure_date, departure_time, arrival_date, arrival_time, carrier, stops, price);
    }

    private Calendar getDepartureCalendar() {
        return new GregorianCalendar(Integer.parseInt(departure_date.split("\\.")[2]),
                Integer.parseInt(departure_date.split("\\.")[1]),
                Integer.parseInt(departure_date.split("\\.")[0]),
                Integer.parseInt(departure_time.split(":")[0]),
                Integer.parseInt(departure_time.split(":")[1]));
    }

    private Calendar getArrivalCalendar() {
        return new GregorianCalendar(Integer.parseInt(arrival_date.split("\\.")[2]),
                Integer.parseInt(arrival_date.split("\\.")[1]),
                Integer.parseInt(arrival_date.split("\\.")[0]),
                Integer.parseInt(arrival_time.split(":")[0]),
                Integer.parseInt(arrival_time.split(":")[1]));
    }


    public long getFlightTime() {
        Calendar departureCalendar = getDepartureCalendar();
        Calendar arrivalCalendar = getArrivalCalendar();
        long flightDurationInMillis = arrivalCalendar.getTimeInMillis() - departureCalendar.getTimeInMillis();
        long difference_In_Hours = (flightDurationInMillis / (1000 * 60 * 60)) % 24;
        long difference_In_Minutes = (flightDurationInMillis / (1000 * 60)) % 60;
        long flightDurationInMinutes = (flightDurationInMillis / (1000 * 60));

        return flightDurationInMinutes;
    }

    public void calculateAverageFlightTime(List<Ticket> ticketsList) {
        List<Long> listOfFlightsDuration = new ArrayList<>();

        for (Ticket ticket : ticketsList) {
            listOfFlightsDuration.add(ticket.getFlightTime());
        }
        OptionalDouble averageFlightDuration = listOfFlightsDuration.stream().mapToLong(l -> l).average();
        System.out.println("Average flight time is " + (int) averageFlightDuration.getAsDouble() / 60 + " hours, " + (int) averageFlightDuration.getAsDouble() % 60 + " minutes.");

    }

    public long calculatePercentile(List<Ticket> ticketList, int percent) {

        ArrayList flightDurationList = new ArrayList();

        for (Ticket ticket : ticketList) {
            flightDurationList.add(ticket.getFlightTime());
        }

        flightDurationList.sort(Comparator.naturalOrder());

        int index = (percent * ticketList.size()) / 100;

        long percentileInMinutes = (long) flightDurationList.get(index - 1);

        System.out.println(percent + "th flight time percentile is = " + percentileInMinutes / 60 + " hours " + percentileInMinutes % 60 + "minutes.");
        return (long) flightDurationList.get(index - 1);
    }
}
