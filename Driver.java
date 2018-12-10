import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Driver {

	static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");


	private static FlightRepository flightRepo = new FlightRepository();

	private static PassengerRepository passengerRepo = new PassengerRepository();

	private static ReservationRepository reservationRepo = new ReservationRepository();
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String... args) throws Exception {
		
		for(;;) {
			
			int menuSearch = promptInteger(scanner, "Enter 1 to book flight\nEnter 2 to search for flight\nEnter 3 to list flight(s)");
			int search = 0;
			switch(menuSearch) {
			case 1:
				reserveTripMenu();
				
				search = promptInteger(scanner, "Enter 1 to return to the main menu\nEnter any other number if you're done.");
				
				if(search == 1) {
					continue;
				}
				else
					break;
								
			case 2:
				searchRepository();
				search = promptInteger(scanner, "Enter 1 to return to the main menu\nEnter any other number if you're done.");
				
				if(search == 1) {
					continue;
				}
				else
					break;
			case 3:
				listReservation();
				search = promptInteger(scanner, "Enter 1 to return to the main menu\nEnter any other number if you're done.");
				
				if(search == 1) {
					continue;
				}
				else
					break;
				
			}
					
			break;
			}
			
	}
	
	protected static void listReservation() throws Exception{
		Collection <Reservation> reservation = reservationRepo.getAll();
		Collection <Passenger> passenger = passengerRepo.getAll();
		if(reservation.isEmpty()) {
			System.out.println("Reservation repo is empty");
		}
		reservation.forEach(r ->{
			
			System.out.printf("%s => %s\n", r.getFlight().getDeparture().getName(), r.getFlight().getDestination().getName());
			
		});
		
		passenger.forEach(p ->{
			System.out.printf("Passenger:%n%s%n%s%n%s%n", p.getName(), p.getGender(), p.getPhone());
		});
	}
		
	protected static void searchRepository() throws NoSuchEntityException {
	
						
			for(;;) {
				Collection<Passenger> passengers = passengerRepo.searchByName(promptString(scanner, "Enter your name: "));
				
				System.out.printf("%d matches\n", passengers.size());
				for (Passenger p : passengers) {
					System.out.printf("%s\n", p.getName());
				}

				Collection<Reservation> reservations = reservationRepo.findByName(promptString(scanner, "Re-enter name for verification"));
				for (Reservation r : reservations) {
					
					if(reservations.size() > 0) {
						System.out.printf("%s => %s\n", r.getFlight().getDeparture().getName(), r.getFlight().getDestination().getName());
						
						for(Passenger p : passengers) {
							System.out.printf("Ticket:%n%s%n%s%n%s%n", p.getName(), p.getGender(), p.getPhone());
							}
						}
					}
				
				if(reservations.isEmpty()) {
					System.out.println("Could not find by name");
				}
				
				
				passengers = passengerRepo.searchByPhone(promptString(scanner, "Enter a phone search: "));
						
				Collection<Reservation> reservationsByNumber = reservationRepo.findByNumber(promptString(scanner, "Re-Enter your phone number for verificatipn"));
				for(Reservation r : reservationsByNumber) {
								
					if(reservationsByNumber.size() > 0) {
						System.out.printf("%s => %s\n", r.getFlight().getDeparture().getName(), r.getFlight().getDestination().getName());
						for(Passenger p : passengers) {
							System.out.printf("Ticket:%n%s%n%s%n%s%n", p.getName(), p.getGender(), p.getPhone());
					}
					break;
				}
			}
			
			if(reservationsByNumber.isEmpty()) {
				System.out.printf("No matches by Phone: ");	
			}
			
			break;
			}
		}
	
	
	protected static void reserveTripMenu() throws Exception {
			for (;;) {
			
				int opt = promptInteger(scanner, "Please type 1 for Roundtrip\nPlease type 2 for One-way\n");
				if (opt < 1 || opt > 2) {
					System.out.println("Invalid selection");
					continue;
				}
				boolean roundtrip = opt == 1;

				Passenger passenger = promptPassenger(scanner, "Please fill out the following fields");
				Passenger existing = passengerRepo.findPassenger(passenger);
				if (existing != null) {
					passenger = existing;
				} else {
					passenger = passengerRepo.create(passenger);
				}

				
				Airport departure = promptAirport(scanner, "Enter departure airport code: ");
				System.out.printf("Departure airport: %s\n", departure.getName());

				Airport destination = promptAirport(scanner, "Enter destination airport code: ");
				System.out.printf("Destination airport: %s\n", destination.getName());

				if (departure.equals(destination)) {
					System.out.println("You have selected the same departure and destination airport, please choose different airports");
					continue;
				}

				Date departDate = promptDate(scanner, "Enter departure date");
				Flight departFlight = findOrCreateFlight(departure, destination, departDate);
				if (!departFlight.hasAvailableSeat()) {
					System.out.println("There are no seats available on that flight, please choose another");
					continue;
				}

				Flight returnFlight = null;
				if (roundtrip) {
					for (;;) {
						Date returnDate = promptDate(scanner, "Enter return date");
						returnFlight = findOrCreateFlight(destination, departure, returnDate);
						if (!returnFlight.hasAvailableSeat()) {
							System.out.println("There are no seats available on that flight, please choose another date");
							continue;
						}

						break;
					}
				}
								
				reserveTrip(passenger, departFlight, returnFlight);
				break;
			}
			}
	

	protected static Flight findOrCreateFlight(Airport departure, Airport destination, Date date) {
		Flight flight = flightRepo.searchByAirportsAndDate(departure, destination, date);

		if (flight == null) {
			flight = flightRepo.create(new Flight(departure, destination, date));
		}

		return flight;
	}

	protected static void reserveTrip(Passenger passenger, Flight departFlight, Flight returnFlight) throws Exception {
		departFlight.reserveSeat();
		flightRepo.update(departFlight);
		reservationRepo.create(new Reservation(departFlight, passenger));

		if (returnFlight != null) {
			returnFlight.reserveSeat();
			flightRepo.update(returnFlight);
			reservationRepo.create(new Reservation(returnFlight, passenger));
		}
	}

	//method initializes passenger's attributes
	protected static Passenger promptPassenger(Scanner scanner, String message) {
		for (;;) {
			String name = promptString(scanner, "Enter name: ");

			String genderString = promptString(scanner, "Enter gender (f/m): ");
			Passenger.Gender gender = null;
			try {
				gender = Passenger.Gender.valueOf(genderString.trim().toUpperCase());
			} catch (IllegalArgumentException e) {
				System.out.printf("Invalid gender value: %s\n", genderString);
				continue;
			}

			String phone = promptString(scanner, "Enter phone number: ");

			return new Passenger(name, gender, phone);
		}
	}
		
	protected static Airport promptAirport(Scanner scanner, String message) {
		for (;;) {
			String code = promptString(scanner, message);

			try {
				return Airport.getAirportByCode(code);
			} catch (UnknownAirportException e) {
				System.out.println(e.getMessage());
			}
		}
	}


	protected static Date promptDate(Scanner scanner, String message) {
		for (;;) {
			System.out.print(message + " (MM/DD/YYYY): ");

			try {
				return DATE_FORMAT.parse(scanner.nextLine().trim());
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	protected static int promptInteger(Scanner scanner, String message) {
		for (;;) {
			System.out.print(message);

			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.printf("Invalid integer input: %s\n", scanner.next());
				continue;
			} finally {
				scanner.nextLine();
			}
		}
	}

	protected static String promptString(Scanner scanner, String message) {
		for (;;) {
			System.out.print(message);

			String entry = scanner.nextLine().trim();
			if (!entry.isEmpty()) {
				return entry;
			}

			System.out.println("No value entered, please enter a value");
		}
	}
}
