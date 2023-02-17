public abstract class VideoType {
//case Video.VHS: limit = 5 ; break ;
//			case Video.CD: limit = 3 ; break ;
//			case Video.DVD: limit = 2 ; break ;
    private int limit;
    private int penalty;


    public VideoType() {

    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
