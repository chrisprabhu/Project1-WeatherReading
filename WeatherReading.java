// Purpose: This file is a record class that stores the weather reading data.
public record WeatherReading implements Comparable<WeatherReading>(String country, String state, String city,
                                                                          String month, String day, String year,
                                                                          String avgTemperature) {

    @Override
    public int compareTo(WeatherReading other) {
        if (this.country.compareTo(other.country) !=0) { return this.country.compareTo(other.country); }
        else if (this.state.compareTo(other.state) !=0 ) { return this.state.compareTo(other.state); }
        else return this.city.compareTo(other.city);
    }
}
