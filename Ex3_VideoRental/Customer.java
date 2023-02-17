import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private String name;

    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void clearReantals() {
        this.rentals.clear();
    }

    public List<Rental> getRentals() {
        return Collections.unmodifiableList(rentals);
    }

    //Long Method (Code Smell)
    public String getReport() {
        String result = "Customer Report for " + getName() + "\n";

        double totalCharge = 0;
        int totalPoint = 0;

        for (Rental each : rentals) {
            double eachCharge = 0;
            int eachPoint = 0;
            int daysRented = 0;

            daysRented = each.getDaysRented();
            eachCharge = each.getEachCharge(eachCharge, daysRented);
            eachPoint = each.getEachPoint(eachPoint, daysRented);

            result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
                    + "\tPoint: " + eachPoint + "\n";
            totalCharge += eachCharge;
            totalPoint += eachPoint;
        }

        result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

        showFreeCouponInfo(totalPoint);

        return result;
    }

    private static void showFreeCouponInfo(int totalPoint) {
        if (totalPoint >= 10) {//magic number
            System.out.println("Congrat! You earned one free coupon");
        }
        if (totalPoint >= 30) {//magic number
            System.out.println("Congrat! You earned two free coupon");
        }
    }

    void listRentals() {
        for (Rental rental : rentals) {
            System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
            System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
        }
    }
}
