package com.example.dataStructures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class DataStructures {
    private HashMap<String, String> homeService = new HashMap<>();
    private HashMap<String, String> rentStatus = new HashMap<>();
    private ArrayList<LocalDate> rentCalendar = new ArrayList<>();
    private HashMap<String, ArrayList<LocalDate>> rentTime = new HashMap<>();

    Scanner scanner = new Scanner(System.in);

    public void addProperty() {
        System.out.println("Podaj nazwę obiektu do wynajęcia");
        homeService.put("Nazwa", scanner.next());
        rentStatus.put("Nazwa", homeService.get("Nazwa"));

        System.out.println("Podaj cenę obiektu");
        homeService.put("Cena", String.valueOf(scanner.nextInt()));

        System.out.println("Podaj powierzchnię obiektu");
        homeService.put("Powierzchnia", String.valueOf(scanner.nextInt()));

        System.out.println("Podaj opis obiektu");
        homeService.put("Opis", scanner.next());
    }

    public void addPeople() {
        System.out.println("Podaj imię wynajmującego");
        rentStatus.put("Wynajmujący", scanner.next());

        System.out.println("Podaj imię najmującego");
        rentStatus.put("Najmujący", scanner.next());
    }

    public void makeReservation() {
        System.out.println("Podaj nazwę obiektu, który chcesz wynająć");
        String checkIfPropertyExists = scanner.next();
        if (homeService.get("Nazwa").equals(checkIfPropertyExists)) {
            rentTime.put(checkIfPropertyExists, rentCalendar);
            System.out.println("Podaj początek najmu (DD-MM-YYYY");
            String rent = scanner.next();
            LocalDate rentStartDate = LocalDate.of(Integer.parseInt(rent.substring(6)), Integer.parseInt(rent.substring(3, 5)), Integer.parseInt(rent.substring(0, 2)));
            System.out.println("Podaj liczbę dni pobytu");
            int numberOfDays = scanner.nextInt();
            LocalDate endRentDate = rentStartDate.plusDays(numberOfDays);
            if (rentCalendar.isEmpty()) {
                for (int i = 0; i < numberOfDays; i++) {
                    rentCalendar.add(rentStartDate.plusDays(i));
                }
                addPeople();
            } else {
                Iterator<LocalDate> iter = rentCalendar.iterator();
                boolean reservationAvailable = true;
                while (iter.hasNext()) {
                    LocalDate dateChecker = iter.next();
                    if (dateChecker.isBefore(endRentDate) && dateChecker.isAfter(rentStartDate)) {
                        reservationAvailable = false;
                    }
                }
                if (!reservationAvailable)
                    System.out.println("Niestety obiekt jest zarezerwowany. \nSpróbuj w innym termnie");
            }
        } else {
            System.out.println("Niestety nie mamy takiego obiektu w bazie.");
        }
    }

    public static void main(String[] args) {
        DataStructures starHut = new DataStructures();
        starHut.addProperty();
        starHut.makeReservation();
        starHut.makeReservation();
    }
}
