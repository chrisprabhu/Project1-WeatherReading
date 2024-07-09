// Purpose: This file is a record class that stores the weather reading data.
public record WeatherReading(
        String country, String state, String city, String month, String day, String year, String avgTemperature
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