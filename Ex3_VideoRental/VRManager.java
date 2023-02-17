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

    public void clearRentals(String customerName) {
        Customer foundCustomer = getCustomerName(customerName);

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            listCustomer(foundCustomer);
            foundCustomer.listRentals();

            List<Rental> rentals = new ArrayList<Rental>();
            foundCustomer.setRentals(rentals);
        }
    }

    public void returnVideo(String customerName) {
        Customer foundCustomer = getCustomerName(customerName);
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

    Customer getCustomerName(String customerName) {
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
        for (Video video : videos) {
            System.out.println("Price code: " + video.getPriceCode() + "\tTitle: " + video.getTitle());
        }
    }

    public void listCustomers() {
        for (Customer customer : customers) {
            listCustomer(customer);
            customer.listRentals();
        }
    }

    private static void listCustomer(Customer customer) {
        System.out.println("Name: " + customer.getName() + "\tRentals: " + customer.getRentals().size());
    }

    public void getCustomerReport(String customerName) {
        Customer foundCustomer = getCustomerName(customerName);

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            String result = foundCustomer.getReport();
            System.out.println(result);
        }
    }

    public void rentVideo(String customerName) {
        Customer foundCustomer = getCustomerName(customerName);

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

    public void registerCustomer(String customerName) {
        customers.add(new Customer(customerName));
    }

    public void registerVideo(String title, int videoType, int priceCode) {
        videos.add(new Video(title, VideoFactory.create(videoType), priceCode, new Date()));
    }
}
