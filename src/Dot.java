public class Dot {
    private int x_coordinate;
    private int y_coordinate;
    private int value;

    public Dot(int x, int y, int value) {
        x_coordinate = x;
        y_coordinate = y;
        this.value = value;
    }

    public Dot(String[] values) {
        x_coordinate = Integer.parseInt(values[0]);
        y_coordinate = Integer.parseInt(values[1]);
        value = Integer.parseInt(values[2]);
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }

    public int getValue() {
        return value;
    }

    public void printDotInfo() {
        System.out.print("(" + x_coordinate + ",");
        System.out.print(y_coordinate + ")");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Dot)) {
            return false;
        }
        Dot dot = (Dot) obj;
        return dot.x_coordinate == x_coordinate &&
                dot.y_coordinate == y_coordinate &&
                dot.value == value;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x_coordinate;
        result = 31 * result + y_coordinate;
        result = 31 * result + value;
        return result;
    }
}
