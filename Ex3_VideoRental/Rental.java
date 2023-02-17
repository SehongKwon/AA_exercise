import java.util.Date;

public class Rental {
    private Video video;
    private int status; // 0 for Rented, 1 for Returned (*Comments)
    private Date rentDate;
    private Date returnDate; // kj85.kim: temporary field

    public Rental(Video video) {
        this.video = video;
        status = 0;
        rentDate = new Date();
    }

    public double getEachCharge(double eachCharge, int daysRented) {
        switch (getVideo().getPriceCode()) {
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

    public int getEachPoint(int eachPoint, int daysRented) {
        eachPoint++;

        if ((getVideo().getPriceCode() == Video.NEW_RELEASE))
            eachPoint++;

        if (daysRented > getDaysRentedLimit())
            eachPoint -= Math.min(eachPoint, getVideo().getLateReturnPointPenalty());
        return eachPoint;
    }

    public Video getVideo() {
        return video;
    }

    public int getStatus() {
        return status;
    }

    public void returnVideo() {
        if (status == 1) { // kj85.kim: temporary field
            this.status = 1;
            returnDate = new Date();
        }
    }

    public int getDaysRentedLimit() {
        int limit = 0;
        if (getDaysRented() <= 2) return limit;
        return video.getVideoType().getLimit();
    }

    public int getDaysRented() {
        int daysRented;
        if (getStatus() == 1) { // returned Video (*Comments)
            daysRented = calcDaysRented(returnDate.getTime());
        } else { // not yet returned (*Comments)
            daysRented = calcDaysRented(new Date().getTime());
        }
        return daysRented;
    }

    private int calcDaysRented(long returnTime) {
        int daysRented;
        long diff = returnTime - rentDate.getTime();
        //magic number
        daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1; //• Duplicate Code
        return daysRented;
    }
}
