// ToDo Deal with nulls
/**
 * Represents a weather reading.
 * @param region the region of the reading.
 * @param country the country of the reading.
 * @param state the state of the reading.
 * @param city the city of the reading.
 * @param month the month of the reading.
 * @param day the day of the reading.
 * @param year the year of the reading.
 * @param avgTemperature the average temperature of the reading.
 */
public record WeatherReading(
        String region, String country, String state, String city, int month, int day, int year, double avgTemperature
) implements Comparable <WeatherReading> {
    // country, state, city, year, month, and day (note the omission of region).
    public int compareTo(WeatherReading other) {
        if (!this.country.equals( other.country )) return this.country.compareTo( other.country );// country
        else if (!this.state.equals( other.state )) return this.state.compareTo( other.state ); // state
        else if (!this.city.equals( other.city )) return this.city.compareTo( other.city ); // city
        else if (this.year != other.year) return this.year - other.year; // year
        else if (this.month != other.month) return this.month - other.month; // month
        else if (this.day != other.day) return this.day - other.day; // day
        else return 0;
    }
    
    @Override
    /**
     * @obj the object to compare to.
     * @return true if the objects is the same type and has all the same values.
     */
    public boolean equals(Object obj) {
        //        if (compareTo( (WeatherReading) obj ) != 0) return false;
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WeatherReading other = (WeatherReading) obj;
        if (this.region != other.region) return false;
        if (country != other.country) return false;
        if (state != other.state) return false;
        if (city != other.city) return false;
        if (this.month != other.month) return false;
        if (this.day != other.day) return false;
        if (this.year != other.year) return false;
        if (Double.compare( other.avgTemperature, avgTemperature ) != 0) return false;
        return true;
    }
    /**
     * Compares the country, state, and city of this WeatherReading to another WeatherReading for sorting.
     * @param other the other WeatherReading to compare to.
     * @return a negative integer, zero, or a positive integer as this WeatherReading is less than, equal to, or
     *         greater than the other WeatherReading lexicographically.
     */
    public int compareCountryStateCity(WeatherReading other) {
        if (!this.country.equals( other.country )) return this.country.compareTo( other.country );// country
        else if (!this.state.equals( other.state )) return this.state.compareTo( other.state ); // state
        else if (!this.city.equals( other.city )) return this.city.compareTo( other.city ); // city
        else return 0;
    }
}