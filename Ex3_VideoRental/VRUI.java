import java.util.Date;
import java.util.Scanner;


//Large Class
public class VRUI {
    private static Scanner scanner = new Scanner(System.in);
    private static VRManager vRManager = new VRManager();

    public static void main(String[] args) {
        VRUI ui = new VRUI();

        boolean quit = false;
        while (!quit) {
            int command = ui.showCommand();

            //Switch statement
            switch (command) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    ui.listCustomers();
                    break;
                case 2:
                    ui.listVideos();
                    break;
                case 3:
                    ui.registerCustomer();
                    break;
                case 4:
                    ui.registerVideo();
                    break;
                case 5:
                    ui.rentVideo();
                    break;
                case 6:
                    ui.returnVideo();
                    break;
                case 7:
                    ui.getCustomerReport();
                    break;
                case 8:
                    ui.clearRentals();
                    break;
                case -1:
                    ui.init();
                    break;
                default:
                    break;
            }
        }
        System.out.println("Bye");
    }

    private void init() {
        Customer james = new Customer("James");
        Customer brown = new Customer("Brown");
        vRManager.customers.add(james);
        vRManager.customers.add(brown);

        Video v1 = new Video("v1", VideoFactory.create(2), Video.REGULAR, new Date());
        Video v2 = new Video("v2", VideoFactory.create(3), Video.NEW_RELEASE, new Date());
        vRManager.videos.add(v1);
        vRManager.videos.add(v2);

        Rental r1 = new Rental(v1);
        Rental r2 = new Rental(v2);

        james.addRental(r1);
        james.addRental(r2);
    }

    public void listCustomers() {
        System.out.println("List of customers");
        vRManager.listCustomers();
        System.out.println("End of list");
    }

    public void listVideos() {
        System.out.println("List of videos");
        vRManager.listVideos();
        System.out.println("End of list");
    }

    public void registerCustomer() {
        vRManager.registerCustomer(enterCustomerName());
    }

    public void registerVideo() {
        System.out.println("Enter video title to register: ");
        String title = scanner.next();

        System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
        int videoType = scanner.nextInt();

        System.out.println("Enter price code( 1 for Regular, 2 for New Release ):");
        int priceCode = scanner.nextInt();

        vRManager.registerVideo(title, videoType, priceCode);
    }

    public void rentVideo() {
        vRManager.rentVideo(enterCustomerName());
    }

    public void returnVideo() {
        vRManager.returnVideo(enterCustomerName());
    }

    public void getCustomerReport() {
        vRManager.getCustomerReport(enterCustomerName());
    }

    public void clearRentals() {
        vRManager.clearRentals(enterCustomerName());
    }

    private String enterCustomerName() {
        System.out.println("Enter customer name: ");
        return scanner.next();
    }

    public int showCommand() {
        //Substituted Algorithm start
        System.out.println("\nSelect a command !");
        System.out.println("\t 0. Quit");
        System.out.println("\t 1. List customers");
        System.out.println("\t 2. List videos");
        System.out.println("\t 3. Register customer");
        System.out.println("\t 4. Register video");
        System.out.println("\t 5. Rent video");
        System.out.println("\t 6. Return video");
        System.out.println("\t 7. Show customer report");
        System.out.println("\t 8. Show customer and clear rentals");
        //end

        int command = scanner.nextInt();

        return command;

    }
}
