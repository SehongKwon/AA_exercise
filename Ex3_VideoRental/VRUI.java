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
                    vRManager.listCustomers();
                    break;
                case 2:
                    vRManager.listVideos();
                    break;
                case 3:
                    vRManager.registerCustomer();
                    break;
                case 4:
                    vRManager.registerVideo();
                    break;
                case 5:
                    vRManager.rentVideo();
                    break;
                case 6:
                    vRManager.returnVideo();
                    break;
                case 7:
                    vRManager.getCustomerReport();
                    break;
                case 8:
                    vRManager.clearRentals();
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
