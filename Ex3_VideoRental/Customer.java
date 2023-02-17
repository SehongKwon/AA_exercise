import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

    public void clearReantals(){
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
            eachCharge = getEachCharge(each, eachCharge, daysRented);
            eachPoint = getEachPoint(each, eachPoint, daysRented);

            result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
                    + "\tPoint: " + eachPoint + "\n";
            totalCharge += eachCharge;
            totalPoint += eachPoint;
        }

        result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";


        if (totalPoint >= 10) {//magic number
            System.out.println("Congrat! You earned one free coupon");
        }
        if (totalPoint >= 30) {//magic number
            System.out.println("Congrat! You earned two free coupon");
        }
        return result;
    }

    private static double getEachCharge(Rental each, double eachCharge, int daysRented) {
        switch (each.getVideo().getPriceCode()) {
            case Video.REGULAR: //Feature Envy
                eachCharge += 2;
                if (daysRented > 2)
                    eachCharge += (daysRented - 2) * 1.5; // magic number
                break;
            case Video.NEW_RELEASE: //Feature Envy
                eachCharge = daysRented * 3;
                break;
        }
        return eachCharge;
    }

    private static int getEachPoint(Rental each, int eachPoint, int daysRented) {
        eachPoint++;

        if ((each.getVideo().getPriceCode() == Video.NEW_RELEASE))
            eachPoint++;

        if (daysRented > each.getDaysRentedLimit())
            eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty());
        return eachPoint;
    }

    void listRentals() {
        for (Rental rental : rentals) {
            System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
            System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
        }
    }
}
