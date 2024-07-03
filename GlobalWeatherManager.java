import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class GlobalWeatherManager implements GlobalWeatherManagerInterface {
    public GlobalWeatherManager(File file) throws FileNotFoundException {
    }

    public int getReadingCount() {
        return 0;
    }

    public WeatherReading getReading(int index) {
        return null;
    }

    public WeatherReading[] getReadings(int index, int count) {
        return null;
    }

    public WeatherReading[] getReadings(int index, int count, int month, int day) {
        return null;
    }

    public CityListStats getCityListStats(String country, String state, String city) {
        return null;
    }

    public Iterator<WeatherReading> iterator() {
        return null;
    }

    public double getTemperatureLinearRegressionSlope(WeatherReading[] readings) {
        return 0;
    }

    public double calcLinearRegressionSlope(Integer[] x, Double[] y) {
        return 0;
    }

    public double getTemperatureLinearRegressionIntercept(WeatherReading[] readings) {
        return 0;
    }
}
