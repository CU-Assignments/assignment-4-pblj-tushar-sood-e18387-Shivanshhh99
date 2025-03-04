class TicketBookingSystem {
    private int availableSeats = 10;
    private final Object lock = new Object();

    public void bookTicket(String customerType) {
        synchronized (lock) {
            if (availableSeats > 0) {
                System.out.println(customerType + " booking in process...");
                availableSeats--;
                System.out.println(customerType + " booked a ticket. Seats left: " + availableSeats);
            } else {
                System.out.println("No seats available for " + customerType + "!");
            }
        }
    }

    static class RegularBooking extends Thread {
        TicketBookingSystem system;

        RegularBooking(TicketBookingSystem system) {
            this.system = system;
        }

        @Override
        public void run() {
            system.bookTicket("Regular Customer");
        }
    }

    static class VIPBooking extends Thread {
        TicketBookingSystem system;

        VIPBooking(TicketBookingSystem system) {
            this.system = system;
        }

        @Override
        public void run() {
            system.bookTicket("VIP Customer");
        }
    }

    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        RegularBooking regularBooking1 = new RegularBooking(system);
        RegularBooking regularBooking2 = new RegularBooking(system);
        VIPBooking vipBooking1 = new VIPBooking(system);
        VIPBooking vipBooking2 = new VIPBooking(system);

        vipBooking1.setPriority(Thread.MAX_PRIORITY);
        vipBooking2.setPriority(Thread.MAX_PRIORITY);
        regularBooking1.setPriority(Thread.MIN_PRIORITY);
        regularBooking2.setPriority(Thread.MIN_PRIORITY);

        regularBooking1.start();
        regularBooking2.start();
        vipBooking1.start();
        vipBooking2.start();
    }
}
