package org.manish07.app;

import org.manish07.model.Bill;
import org.manish07.model.Booking;
import org.manish07.model.Customer;
import org.manish07.model.Room;
import org.manish07.service.impl.BillingServiceImpl;
import org.manish07.service.impl.BookingServiceImpl;
import org.manish07.service.impl.CustomerServiceImpl;
import org.manish07.service.impl.RoomServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class HotelManagementApp {
    
    static Scanner scanner = new Scanner (System.in);
    static int id;
    
    public static void main (String[] args) {
        
        CustomerServiceImpl customerService = new CustomerServiceImpl ();
        RoomServiceImpl roomService = new RoomServiceImpl ();
        BookingServiceImpl bookingService = new BookingServiceImpl ();
        BillingServiceImpl billingService = new BillingServiceImpl ();
        
        while (true) {
            
            System.out.println ("""
                                        \n
                                         ___________________________________________________________________
                                        |___________________ HOTEL MANAGEMENT SYSTEM _______________________|
                                        |___________________________________________________________________|
                                        
                                        1. Customer Operations
                                        2. Room Operations
                                        3. Booking Operations
                                        4. Bill Operations
                                        5. Exit
                                        """);
            
            try {
                
                System.out.print ("Enter Your Choice : ");
                String mainInput = scanner.next ();
                int mainInput2 = Integer.parseInt (mainInput);
                
                switch (mainInput2) {
                    
                    case 1 -> {
                        customerOption (customerService);
                    }
                    
                    case 2 -> {
                        roomOption (roomService);
                    }
                    case 3 -> {
                        bookOption (bookingService);
                    }
                    
                    case 4 -> {
                        billOption (billingService);
                    }
                    
                    case 5 -> {
                        System.exit (0);
                    }
                    
                    default -> System.out.println ("Invalid Option , Try Again");
                }
                
                
            }
            catch (Exception e) {
                System.out.println (e.getMessage ());
            }
            
        }
        
    }
    
    public static void customerOption (CustomerServiceImpl customerService) {
        while (true) {
            
            System.out.println ("""
                                         _________________________________________________
                                        |_____________ Customer Operations _______________|
                                        
                                        1. Register New Customer
                                        2. Get Customer by ID
                                        3. List All Customers
                                        4. Get Customer by Phone
                                        5. Back to Main Menu
                                        6. Exit
                                        """);
            
            System.out.print ("Enter Your Choice : ");
            String customerInput = scanner.next ();
            int customerInput2 = Integer.parseInt (customerInput);
            
            switch (customerInput2) {
                
                case 1 -> {
                    
                    System.out.println (
                            "\n------------------Data is End to End Encrypted----------------");
                    
                    System.out.print ("Enter Your Name : ");
                    scanner.nextLine ();
                    String name = scanner.nextLine ();
                    
                    System.out.print ("Enter Phone Number : ");
                    String phone = scanner.next ();
                    
                    System.out.print ("Enter Email Id : ");
                    String email = scanner.next ();
                    
                    Customer customer = new Customer (id,
                                                      name,
                                                      phone,
                                                      email,
                                                      LocalDateTime.now ()
                                                              .truncatedTo (ChronoUnit.SECONDS)
                    );
                    if (customerService.addCustomer (customer)) {
                        System.out.println ("Customer Added Successfully");
                    } else {
                        System.out.println ("Customer Not Added , Due to some other error");
                    }
                    
                    int customerId = customerService.getCustomerIdByPhone (phone);
                    if (customerId != - 1) {
                        System.out.println ("Your Customer Id is : " + customerId);
                    } else {
                        System.out.println ("Customer Id Didn't generate");
                    }
                    
                }
                
                case 2 -> {
                    
                    System.out.print ("\nEnter Customer Id :");
                    int customerId = scanner.nextInt ();
                    System.out.println (customerService.getCustomerById (customerId));
                }
                
                case 3 -> {
                    System.out.println (customerService.getAllCustomers ());
                }
                
                case 4 -> {
                    System.out.print ("Enter Customer Phone Number :");
                    String customerPhone = scanner.next ();
                    
                    System.out.println (customerService.getCustomerById (customerService.getCustomerIdByPhone (
                            customerPhone)));
                }
                
                case 5 -> {
                    printEnding ("Returning to Main Menu");
                    return;
                }
                
                case 6 -> {
                    System.exit (0);
                }
            }
        }
    }
    
    public static void roomOption (RoomServiceImpl roomService) {
        
        while (true) {
            
            System.out.println ("""
                                          _________________________________________________
                                         |_______________ Room Operations _________________|
                                        
                                         1. Add Hotel Room\s
                                         2. List of Available Rooms\s
                                         3. View Room By Id\s
                                         4. View List of AC Rooms\s
                                         5. View List of Non AC Rooms\s
                                         6. View Single Bed Room\s
                                         7. View Double Bed Room\s
                                         8. Check Balcony Availability by Room Id\s
                                         9. Back to Main Menu\s
                                         0. Exit
                                        """);
            
            System.out.print ("Enter Your Choice : ");
            String customerInput = scanner.next ();
            int customerInput2 = Integer.parseInt (customerInput);
            
            switch (customerInput2) {
                
                case 1 -> {
                    
                    System.out.println ("""
                                                ---------------------------------------------
                                                | Ac_Type |    Bed   |   Balcony  |  Price  |
                                                ---------------------------------------------
                                                |   AC    |  SINGLE  |  AVAILABLE | 3000.00 |
                                                | NON AC  |  SINGLE  |  AVAILABLE | 2600.00 |
                                                |   AC    |  SINGLE  |     N/A    | 2600.00 |
                                                | NON AC  |  SINGLE  |     N/A    | 2000.00 |
                                                ---------------------------------------------
                                                |   AC    |  DOUBLE  |  AVAILABLE | 4000.00 |
                                                | NON AC  |  DOUBLE  |  AVAILABLE | 3500.00 |
                                                |   AC    |  DOUBLE  |     N/A    | 3500.00 |
                                                | NON AC  |  DOUBLE  |     N/A    | 3000.00 |
                                                ---------------------------------------------
                                                
                                                """);
                    
                    System.out.print ("Enter Room Number : ");
                    int roomNo = scanner.nextInt ();
                    
                    System.out.print ("Enter Type (AC/NON AC) : ");
                    scanner.nextLine ();
                    String type = scanner.nextLine ();
                    
                    System.out.print ("Enter Bed(Single/Double) : ");
                    String bed = scanner.nextLine ();
                    
                    System.out.print ("Enter Balcony Availability(A OR N/A) : ");
                    String availability = scanner.nextLine ();
                    
                    System.out.print ("Enter Price Of Room : ");
                    int price = scanner.nextInt ();
                    BigDecimal finalPrice = new BigDecimal (price);
                    
                    if (roomService.addRoom (new Room (id,
                                                       roomNo,
                                                       type,
                                                       finalPrice,
                                                       bed,
                                                       availability
                    ))) {
                        System.out.println ("Room Added Successfully");
                        System.out.println (roomService.getRoomByRooNo (roomNo));
                    } else {
                        System.out.println ("Room Not Added , due to invalid input");
                    }
                }
                
                case 2 -> {
                    System.out.println ("Please Be confirm By putting checkIn-checkOut date while" + " booking");
                    System.out.println (roomService.getAvailableRooms ());
                }
                
                case 3 -> {
                    System.out.print ("Enter Room Id ");
                    int roomId = scanner.nextInt ();
                    
                    if (roomService.getRoomById (roomId) == null) System.out.println (
                            "Wrong Room Id ❌ , Try Again with Valid Input");
                    else System.out.println (roomService.getRoomById (roomId));
                }
                
                case 4 -> System.out.println (roomService.getACRoom ("AC"));
                
                case 5 -> System.out.println (roomService.getNonACRoom ("NON-AC"));
                
                case 6 -> System.out.println (roomService.getSingleBedRoom ("SINGLE"));
                
                case 7 -> System.out.println (roomService.getDoubleBedRoom ("DOUBLE"));
                
                case 8 -> {
                    System.out.print ("Enter Room Id : ");
                    int roomId = scanner.nextInt ();
                    
                    Room room = roomService.getRoomById (roomId);
                    if (room != null) {
                        if (room.getBalcony ().equalsIgnoreCase ("A")) {
                            System.out.println ("Balcony Available at Room Id : " + roomId);
                        } else {
                            System.out.println ("Balcony not Available at Room id : " + roomId);
                        }
                    } else {
                        System.out.println ("Wrong Room Id , Try Again with Valid Input");
                    }
                }
                
                case 9 -> {
                    printEnding ("Returning to Main Menu");
                    return;
                }
                case 0 -> {
                    
                    printEnding ("Quitting, Thanks for using Our Application");
                    System.exit (0);
                }
                default -> System.out.println ("Invalid Input");
            }
        }
    }
    
    public static void bookOption (BookingServiceImpl bookingService) {
        
        while (true) {
            
            System.out.println ("""
                                         _________________________________________________
                                        |_____________ Booking  Operations _______________|
                                        
                                        1. Book Room\s
                                        2. View a booking by Booking ID
                                        3. View List of booking by Customer ID
                                        4. View List of booking by Room ID
                                        5. Back to Main Menu
                                        6. Exit
                                        """);
            
            System.out.print ("Enter Your Choice : ");
            String customerInput = scanner.next ();
            int customerInput2 = Integer.parseInt (customerInput);
            
            switch (customerInput2) {
                
                case 1 -> {
                    
                    System.out.print ("Enter Registered Customer Id : ");
                    int customerId = scanner.nextInt ();
                    
                    System.out.print ("Enter Room Id : ");
                    int roomId = scanner.nextInt ();
                    
                    System.out.println ("Enter Check In Date (Upto 2027)");
                    LocalDate checkIn = inputDate ();
                    
                    System.out.println ("Enter Check Out Date (Upto 2028)");
                    LocalDate checkOut = inputDate ();
                    
                    String bookingStatus = "BOOKED";
                    
                    Booking booking = new Booking (id,
                                                   customerId,
                                                   roomId,
                                                   checkIn,
                                                   checkOut,
                                                   LocalDateTime.now (),
                                                   bookingStatus
                    );
                    if (bookingService.bookRoom (booking)) {
                        System.out.println (bookingService.getBookingByCustomerId (customerId));
                    }
                }
                
                case 2 -> {
                    
                    System.out.print ("Enter Room Id : ");
                    int roomId = scanner.nextInt ();
                    
                    System.out.println (bookingService.getBookingByBookingId (roomId));
                }
                
                case 3 -> {
                    System.out.print ("Enter Customer Id : ");
                    int customerId = scanner.nextInt ();
                    if (bookingService.getListBookingByCustomerId (customerId) != null)
                        System.out.println (bookingService.getListBookingByCustomerId (customerId));
                    else System.out.println ("Booking Id Not Found");
                }
                
                case 4 -> {
                    System.out.print ("Enter Booking Id : ");
                    int roomId = scanner.nextInt ();
                    if (bookingService.getListBookingByCustomerId (roomId) != null)
                        System.out.println (bookingService.getListBookingByCustomerId (roomId));
                    else System.out.println ("Booking Id Not Found");
                }
                
                case 5 -> {
                    printEnding ("Returning to Main Menu");
                    return;
                }
                
                case 6 -> {
                    System.exit (0);
                }
            }
        }
    }
    
    public static void billOption (BillingServiceImpl billingService) {
        
        while (true) {
            
            System.out.println ("""
                                          _________________________________________________
                                         |_____________ Billing  Operations _______________|
                                        \s
                                         1. Generate Bill
                                         2. Get Bill By Booking Id
                                         3. Make Payment\s
                                         4. Back to Main Menu
                                         5. Exit
                                        \s""");
            
            System.out.print ("Enter Your Choice : ");
            String customerInput = scanner.next ();
            int customerInput2 = Integer.parseInt (customerInput);
            
            switch (customerInput2) {
                
                case 1 -> {
                    
                    System.out.print ("Enter Booking Id : ");
                    int bookingId = scanner.nextInt ();
                    
                    Bill bill = new Bill (id, bookingId, new BigDecimal ("100"), "Pending",
                                          LocalDateTime.now ()
                    );
                    System.out.println (billingService.generateBill (bill));
                    
                }
                case 2 -> {
                    
                    System.out.print ("Enter Booking Id : ");
                    int bookingId = scanner.nextInt ();
                    System.out.println (billingService.getBillByBookingId (bookingId));
                }
                case 3 -> {
                    System.out.print ("Enter Booking Id : ");
                    int bookingId = scanner.nextInt ();
                    
                    System.out.print ("Enter Bill Amount : ");
                    BigDecimal amount = scanner.nextBigDecimal ();
                    
                    billingService.makePayment (bookingId, amount);
                }
                case 4 -> {
                    printEnding ("Returning to Main Menu");
                    return;
                }
                case 5 -> {
                    printEnding ("Quitting, Thanks for using Our Application");
                    System.exit (0);
                }
            }
        }
    }
    
    public static void sleep () {
        try {
            Thread.sleep (350);
        }
        catch (InterruptedException e) {
            System.out.println (e.getMessage ());
        }
    }
    
    public static void printEnding (String ending) {
        System.out.print (ending);
        sleep ();
        System.out.print (".");
        sleep ();
        System.out.print (".");
        sleep ();
        System.out.print (".");
        sleep ();
        System.out.print (".");
        sleep ();
        sleep ();
    }
    
    public static LocalDate inputDate () {
        
        System.out.print ("Enter Year : ");
        int year = scanner.nextInt ();
        
        System.out.print ("Enter Month : ");
        int Month = scanner.nextInt ();
        
        System.out.print ("Enter Date : ");
        int date = scanner.nextInt ();
        
        return LocalDate.of (year, Month, date);
    }
}

