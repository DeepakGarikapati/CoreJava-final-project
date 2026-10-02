
//===================================Movie Ticket Booking System=================================

package FinalProject;

import java.util.Scanner;

public class MovieTicketBookingSystem {

    static Scanner sc = new Scanner(System.in);

    static boolean[][] seats = new boolean[5][5];

    static double ticketPrice = 200;

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("======/=/=/=/= MOVIE TICKET BOOKING SYSTEM /=/=/=/=/=/======");
            System.out.println("1. Show Available Seats");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Apply Discount Coupon");
            System.out.println("5. Weekend Pricing");
            System.out.println("6. Give Movie Rating");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showSeats();
                    break;

                case 2:
                    bookTicket();
                    break;

                case 3:
                    cancelTicket();
                    break;

                case 4:
                    applyCoupon();
                    break;

                case 5:
                    weekendPrice();
                    break;

                case 6:
                    giveRating();
                    break;

                case 7:
                    System.out.println("Thank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);
    }

    // Show seats
    static void showSeats() {

        System.out.println("----- SEAT STATUS -----");

        for (int i=0;i<seats.length;i++) {

            for (int j=0;j<seats[i].length;j++) {

                if (seats[i][j] == false) {
                    System.out.print("[A] ");
                } else {
                    System.out.print("[B] ");
                }
            }

            System.out.println();
        }

        System.out.println("A = Available");
        System.out.println("B = Booked");
    }

    // To Book the ticket
    static void bookTicket() {

        sc.nextLine();

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter number of tickets: ");
        int numberOfTickets = sc.nextInt();

        if (numberOfTickets <= 0) {
            System.out.println("Invalid number of tickets.");
            return;
        }

        showSeats();

        double totalAmount = 0;

        for (int i=1; i<=numberOfTickets; i++) {

            System.out.println("Ticket " + i);

            System.out.print("Enter row number (1-5): ");
            int row = sc.nextInt();

            System.out.print("Enter seat number (1-5): ");
            int seat = sc.nextInt();

            if (row < 1 || row > 5 || seat < 1 || seat > 5) {

                System.out.println("Invalid seat. Please try again.");
                i--;
                continue;
            }

            if (seats[row - 1][seat - 1]) {

                System.out.println("Seat is already booked. Please select another seat.");
                i--;
                continue;
            }

            seats[row - 1][seat - 1] = true;

            totalAmount = totalAmount + ticketPrice;

            System.out.println("Seat booked successfully.");
        }

        System.out.println("----- BOOKING DETAILS -----");
        System.out.println("Customer Name: " + name);
        System.out.println("Number of Tickets: " + numberOfTickets);
        System.out.println("Ticket Price: Rs" + ticketPrice);
        System.out.println("Total Amount: Rs" + totalAmount);
    }

    // Cancel ticket
    static void cancelTicket() {

        System.out.print("Enter row number (1-5): ");
        int row = sc.nextInt();

        System.out.print("Enter seat number (1-5): ");
        int seat = sc.nextInt();

        if (row < 1 || row > 5 || seat < 1 || seat > 5) {

            System.out.println("Invalid seat number.");

        } else if (seats[row - 1][seat - 1] == false) {

            System.out.println("This seat is not booked.");

        } else {

            seats[row - 1][seat - 1] = false;

            System.out.println("Ticket cancelled successfully.");
        }
    }

    // Discount coupon
    static void applyCoupon() {

        System.out.print("Enter ticket amount: ");
        double amount = sc.nextDouble();

        System.out.print("Enter coupon code: ");
        String coupon = sc.next();

        if (coupon.equalsIgnoreCase("Deepak99")) {

            double discount = amount * 0.10;
            double finalAmount = amount - discount;

            System.out.println("Coupon applied successfully.");
            System.out.println("Discount: Rs" + discount);
            System.out.println("Final Amount: Rs" + finalAmount);

        } else {

            System.out.println("Invalid coupon.");
        }
    }

    // To display Weekend pricing
    static void weekendPrice() {

        double weekendPrice = ticketPrice + 50;

        System.out.println("\nNormal Ticket Price: Rs" + ticketPrice);
        System.out.println("Weekend Ticket Price: Rs" + weekendPrice);
    }

    // To display Movie rating
    static void giveRating() {

        System.out.print("Give movie rating (1-5):");
        int rating = sc.nextInt();

        if (rating >= 1 && rating <= 5) {

            System.out.println("Thank you for rating the movie " + rating + "/5.");

        } else {

            System.out.println("Please enter rating between 1 and 5.");
        }
    }
}
