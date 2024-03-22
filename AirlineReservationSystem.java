import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int availableSeats;
    private double price;

    public Flight(String flightNumber, String origin, String destination, int availableSeats, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }
}

class Booking {
    private String bookingId;
    private Flight flight;
    private String passengerName;
    private int numPassengers;
    private double totalPrice;

    public Booking(String bookingId, Flight flight, String passengerName, int numPassengers, double totalPrice) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.passengerName = passengerName;
        this.numPassengers = numPassengers;
        this.totalPrice = totalPrice;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

public class AirlineReservationSystem {
    private static List<Flight> flights = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        addSampleFlights();

        System.out.println("Welcome to the Airline Reservation System");

        while (true) {
            System.out.println("\n1. View Flights");
            System.out.println("2. Book a Flight");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewFlights();
                    break;
                case 2:
                    bookFlight();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the Airline Reservation System");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void addSampleFlights() {
        flights.add(new Flight("FL123", "New York", "London", 100, 500.0));
        flights.add(new Flight("FL456", "Paris", "Tokyo", 150, 700.0));
    }

    private static void viewFlights() {
        System.out.println("\nAvailable Flights:");
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber() +
                    ", Origin: " + flight.getOrigin() +
                    ", Destination: " + flight.getDestination() +
                    ", Available Seats: " + flight.getAvailableSeats() +
                    ", Price: $" + flight.getPrice());
        }
    }

    private static void bookFlight() {
        System.out.print("Enter flight number to book: ");
        String flightNumber = scanner.nextLine();

        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight != null) {
            System.out.print("Enter passenger name: ");
            String passengerName = scanner.nextLine();
            System.out.print("Enter number of passengers: ");
            int numPassengers = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (selectedFlight.getAvailableSeats() >= numPassengers) {
                double totalPrice = selectedFlight.getPrice() * numPassengers;
                Booking newBooking = new Booking("B001", selectedFlight, passengerName, numPassengers, totalPrice);
                bookings.add(newBooking);
                selectedFlight.setAvailableSeats(selectedFlight.getAvailableSeats() - numPassengers);
                System.out.println("Booking successful. Total Price: $" + totalPrice);
            } else {
                System.out.println("Not enough seats available for booking.");
            }
        } else {
            System.out.println("Flight not found.");
        }
    }

    private static void viewBookings() {
        System.out.println("\nBookings:");
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId() +
                    ", Passenger Name: " + booking.getPassengerName() +
                    ", Flight Number: " + booking.getFlight().getFlightNumber() +
                    ", Num Passengers: " + booking.getNumPassengers() +
                    ", Total Price: $" + booking.getTotalPrice());
        }
    }
}
