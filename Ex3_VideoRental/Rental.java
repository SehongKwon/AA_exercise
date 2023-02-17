import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned (*Comments)
	private Date rentDate ;
	private Date returnDate ; // kj85.kim: temporary field

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == 1 ) { // kj85.kim: temporary field
			this.status = 1;
			returnDate = new Date() ;
		}
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		if ( getDaysRented() <= 2) return limit ;
		return video.getVideoType().getLimit();
	}

	public int getDaysRented() {
		int daysRented;
		if (getStatus() == 1) { // returned Video (*Comments)
			long diff = returnDate.getTime() - rentDate.getTime();
			//magic number
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1; //• Duplicate Code
		} else { // not yet returned (*Comments)
			long diff = new Date().getTime() - rentDate.getTime();
			//magic number
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1; //• Duplicate Code
		}
		return daysRented;
	}
}
