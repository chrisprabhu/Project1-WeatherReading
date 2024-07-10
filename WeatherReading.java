// region,
// country,
// state,
// city (all String),
// month,
// day,
// year (all int),
// avgTemperature (double)
public record WeatherReading(
        String region, String country, String state, String city, int month, int day, int year, double avgTemperature
) implements Comparable <WeatherReading> {
    // ToDo Deal with nulls
    // Country, State, City, Year, Month, Day
    @Override
    public int compareTo(WeatherReading other) {
        // Country
        if (!this.country.equals(other.country)) {
            return this.country.compareTo(other.country);
            
        } // State
        else {
            if (!this.state.equals(other.state)) {
                return this.state.compareTo(other.state);
                
            } // City
            else {
                if (!this.city.equals(other.city)) {
                    return this.city.compareTo(other.city);
                    
                } // Year
                else {
                    if (!this.year.equals(other.year)) {
                        return this.year.compareTo(other.year);
                        
                    } // Month
                    else {
                        if (!this.month.equals(other.month)) {
                            return this.month.compareTo(other.month);
                            
                        } // Day
                        else {
                            return this.day.compareTo(other.day);
                        }
                    }
                }
            }
        }
    }
}