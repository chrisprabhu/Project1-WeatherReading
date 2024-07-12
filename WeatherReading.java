// region,
// country,
// state,
// city (all String),
// month,
// day,
// year (all int),
// avgTemperature (double)
// ToDo Deal with nulls
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
    
    public int compareCountryStateCity(WeatherReading other) {
        if (!this.country.equals( other.country )) return this.country.compareTo( other.country );// country
        else if (!this.state.equals( other.state )) return this.state.compareTo( other.state ); // state
        else if (!this.city.equals( other.city )) return this.city.compareTo( other.city ); // city
        else return 0;
    }
}