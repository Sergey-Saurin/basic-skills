package com.skillbox.airport;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainAirport
{

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();

        Date dateNow = new Date();
        Calendar dateTwoHoursLater = new GregorianCalendar();
        dateTwoHoursLater.setTime(dateNow);
        dateTwoHoursLater.add(Calendar.HOUR, 2);

        airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE) && f.getDate().after(dateNow) && f.getDate().before(dateTwoHoursLater.getTime()))
                .forEach(f -> {System.out.println(f.getDate() + " \t" + f.getAircraft());});

    }

}
