public class VideoFactory {
    public static VideoType create(int type) {
        switch (type) {
            case 1:
                return new VHS();
            case 2:
                return new CD();
            case 3:
                return new DVD();
            default:
                return null;
        }
    }
}
