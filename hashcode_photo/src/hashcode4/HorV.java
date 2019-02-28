package hashcode4;

public enum HorV {
    HORIZONTAL, VERTICAL;


    public public static HorV(String part) {
        if (part.equalsIgnoreCase("V")) {
            return HorV.VERTICAL;
        } else {
            return HorV.HORIZONTAL;
        }
    }
}