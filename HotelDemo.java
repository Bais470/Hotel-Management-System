public class HotelDemo {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Grand Hotel", 10);

        Guest g1 = new Guest("Walid");
        Guest g2 = new Guest("Khan");
        Guest g3 = new Guest("Ahmad");

        hotel.bookRoom(g1);
        hotel.bookRoom(g2);
        hotel.bookRoom(g3);

        hotel.getAvailableRooms();
        hotel.cancelBookingByGuest(g2);

        hotel.getAvailableRooms();
    }
}
class Hotel {
    private String name;
    private Room[] rooms;
    private static int totalBookings = 0;

    public Hotel(String name, int numberOfRooms) {
        this.name = name;
        rooms = new Room[numberOfRooms];
        for (int i = 0; i < numberOfRooms; i++) {
            rooms[i] = new Room(i + 1, 1000.0);
        }
    }
    public void bookRoom(Guest g) {
        for (Room r : rooms) {
            if (r.isBooked() && r.getGuest().getName().equals(g.getName())) {
                System.out.println("Guest " + g.getName() + " already has a room.");
                return;
            }
        }
        for (Room r : rooms) {
            if (!r.isBooked()) {
                r.setGuest(g);
                totalBookings++;
                System.out.println("Room " + r.getRoomNumber() + " booked for " + g.getName());
                return;
            }
        }
        System.out.println("No rooms available!");
    }
    public void cancelBookingByGuest(Guest g) {
        for (Room r : rooms) {
            if (r.isBooked() && r.getGuest().getName().equals(g.getName())) {
                System.out.println("Booking cancelled for " + g.getName() + " (room " + r.getRoomNumber() + ")");
                r.setGuest(null);
                totalBookings--;
                return;
            }
        }
        System.out.println("No booking found for " + g.getName());
    }
    public void getAvailableRooms() {
        int count = 0;
        for (Room r : rooms) {
            if (!r.isBooked()) count++;
        }
        System.out.println("Available rooms: " + count);
    }
    public static int getTotalBookings() {
        return totalBookings;
    }
}

class Room {
    private final int roomNumber;
    private double price;
    private Guest guest;

    public Room(int roomNumber, double price) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.guest = null;
    }
    public boolean isBooked() {
        return guest != null;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}

class Guest {
    private final String name;

    public Guest(String name) {
        this.name = (name == null) ? "" : name;
    }

    public String getName() {
        return(name);
    }
}
