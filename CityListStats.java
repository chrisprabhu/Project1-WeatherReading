import java.util.Arrays;

/**
 * Represents the statistics for a specified city.
 * @param startingIndex the starting index of the city in the sorted ArrayList.
 * @param count the count of readings for the city.
 * @param years the years for which readings are available.
 */
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
