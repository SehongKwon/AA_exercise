import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VRManager {
    Scanner scanner = new Scanner(System.in);
    List<Customer> customers = new ArrayList<Customer>();
    List<Video> videos = new ArrayList<Video>();

    public VRManager() {
    }

    public void clearRentals() {
        Customer foundCustomer = getCustomerName();

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            listCustomer(foundCustomer);
            foundCustomer.listRentals();

            List<Rental> rentals = new ArrayList<Rental>();
            foundCustomer.setRentals(rentals);
        }
    }

    public void returnVideo() {
        Customer foundCustomer = getCustomerName();
        if (foundCustomer == null) return;

        System.out.println("Enter video title to return: ");
        String videoTitle = scanner.next();

        List<Rental> customerRentals = foundCustomer.getRentals();
        for (Rental rental : customerRentals) {
            if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
                rental.returnVideo();
                rental.getVideo().setRented(false);
                break;
            }
        }
    }

    Customer getCustomerName() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.next();

        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }
        return foundCustomer;
    }

    public void listVideos() {
        System.out.println("List of videos");

        for (Video video : videos) {
            System.out.println("Price code: " + video.getPriceCode() + "\tTitle: " + video.getTitle());
        }
        System.out.println("End of list");
    }

    public void listCustomers() {
        System.out.println("List of customers");
        for (Customer customer : customers) {
            listCustomer(customer);
            customer.listRentals();
        }
        System.out.println("End of list");
    }

    private static void listCustomer(Customer customer) {
        System.out.println("Name: " + customer.getName() + "\tRentals: " + customer.getRentals().size());
    }

    public void getCustomerReport() {
        Customer foundCustomer = getCustomerName();

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            String result = foundCustomer.getReport();
            System.out.println(result);
        }
    }

    public void rentVideo() {
        Customer foundCustomer = getCustomerName();

        if (foundCustomer == null) return;

        System.out.println("Enter video title to rent: ");
        String videoTitle = scanner.next();

        Video foundVideo = null;
        for (Video video : videos) {
            if (video.getTitle().equals(videoTitle) && video.isRented() == false) {
                foundVideo = video;
                break;
            }
        }

        if (foundVideo == null) return;

        Rental rental = new Rental(foundVideo);
        foundVideo.setRented(true);

        List<Rental> customerRentals = foundCustomer.getRentals();
        customerRentals.add(rental);
        foundCustomer.setRentals(customerRentals);
    }

    public void registerCustomer() {
        System.out.println("Enter customer name: ");
        customers.add(new Customer(scanner.next()));
    }

    public void registerVideo() {
        System.out.println("Enter video title to register: ");
        String title = scanner.next();

        System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
        int videoType = scanner.nextInt();

        System.out.println("Enter price code( 1 for Regular, 2 for New Release ):");
        int priceCode = scanner.nextInt();

        videos.add(new Video(title, videoType, priceCode, new Date()));
    }
}