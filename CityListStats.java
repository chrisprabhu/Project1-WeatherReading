import java.util.Arrays;

public record CityListStats( int startingIndex, int count, int[] years ) {
    @Override
    public String toString() {
        return "CityListStats{" +
               "startingIndex=" + startingIndex +
               ", count=" + count +
               ", years=" + Arrays.toString( years ) +
               '}';
    }
}
