import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GlobalWeatherManager implements GlobalWeatherManagerInterface
{

    // Instance Variables
    private final ArrayList<WeatherReading> weatherReadingsArrayList = new ArrayList<>();
    private       int                       readingCount             = 0;

    // Constructor
    public GlobalWeatherManager(File file) throws FileNotFoundException {
        System.out.println("starting constructor");
        Scanner myReader = new Scanner(file);


        /* int i = 0;
           while (i < 9) { */
        String header = myReader.nextLine();
        while (myReader.hasNextLine())
            {
                readingCount++;
                String   weatherRow     = myReader.nextLine();
                String[] weatherStrings = weatherRow.split(",");

                WeatherReading weatherReading = new WeatherReading(
                        weatherStrings[0],
                        // Region
                        weatherStrings[1],
                        // Country
                        weatherStrings[2],
                        // State
                        weatherStrings[3],
                        // City
                        weatherStrings[4],
                        // Month
                        weatherStrings[5],
                        // Day
                        weatherStrings[6],
                        // Year
                        weatherStrings[7]
                ); // AvgTemperature
                weatherReadingsArrayList.add(weatherReading);

                // System.out.println(ArrayListOfWeatherReadings.get(i)); i++;
            }

        myReader.close();
        /*
                System.out.println("starting for loop");
                for (int k = 0; k < 9; k++) System.out.println(ArrayListOfWeatherReadings.get(k));
        */

        //ToDo Sort the Array! then add multilayer sort!
        // Add a compareTo-type method called that compares only country, state, and city. You’ll need it.
        // Add a full/proper equals override that is harmonious with natural ordering2. Ask if you are unsure


        // Let's start with some pseudocode to make sure that I understand how to do binary sort!
        /*
         * The general idea of Binary Sort is that since the list is ordered, you can narrow your search by approximately 50% with each check (iteration?). First you check the middle (mid point? median?) value to see if it's higher or lower than your search-term. If the middle value is above your search-term, then you know your query must continue on the lower 50% of values. You can ignore the higher 50% of values. From the remaining values you can repeat until you find your search-term. You reduce your search by (about) 50% with each check. I say "about" because I think if the number of terms that you are checking is even odd it will be more or less than 50%. If you run out of values to search without finding your search-term, then your query does not exist in the dataset.
         *
         * In the case that your search-term may have multiple occurrences in the dataset, once you find the search-term, you need to scan left (and right?) of the found-value until you find a value that isn't your search-term.  */

    }

    public static void main(String[] args) throws FileNotFoundException {
        GlobalWeatherManager myWeatherManager  = new GlobalWeatherManager(new File("documents/city_temperature.csv"));
        WeatherReading[]     getReadingsOutput = myWeatherManager.getReadings(0,3);
        System.out.println(getReadingsOutput[0]);
        System.out.println(getReadingsOutput[1]);
        System.out.println(getReadingsOutput[2]);
    }

    /**
     @return
     */
    public int getReadingCount() {
        return readingCount;
    }

    /**
     @param index the index for the desired reading; must be a valid element index.

     @return
     */
    public WeatherReading getReading(int index) {
        return weatherReadingsArrayList.get(index);
    }

    /**
     @param index the index of the first reading; must be a valid index.
     @param count the count of readings to potentially include.  Must be at least 1.  Must imply a valid range;
     index + count must be less than the total reading count.

     @return
     */
    public WeatherReading[] getReadings(
            int index,int count
                                       )
    {

        return weatherReadingsArrayList.subList(index,index + count).toArray(new WeatherReading[0]);
    }

    /**
     @param index the index of the first reading.
     @param count the count of readings to check for potential inclusion.  Must be at least 1.
     Must imply a valid range; index + count must be less than the total reading count.
     @param month the month to filter; must be a valid month (1 to 12).
     @param day   the day to filter; must be a valid day (1 to 31).

     @return
     */
    public WeatherReading[] getReadings(
            int index,int count,int month,int day
                                       )
    {
        return new WeatherReading[0];
    }

    /**
     @param country the country of interest; must not be null or blank.
     @param state   the state of interest; must not be null.
     @param city    the city of interest; must not be null or blank.

     @return
     */
    public CityListStats getCityListStats(
            String country,String state,String city
                                         )
    {
        return new CityListStats(country,state,city);
    }

    /**
     @return
     */
    public Iterator<WeatherReading> iterator() {
        return weatherReadingsArrayList.iterator();
    }

    /**
     @param readings array of readings to analyze.  Should typically be readings for a single day over
     a number of years; larger data sets will likely yield better results.  Ignores
     temperature data of -99.0, a default value indicating no temperature data was present.
     Must not be null and must contain at least two readings.

     @return
     */
    public double getTemperatureLinearRegressionSlope(WeatherReading[] readings) {
        return 0.0;
    }

    /**
     @param x an array of x values; must not be null and must contain at least two elements.
     @param y an array of y values; must be the same length as the x array and must not be null.

     @return
     */
    public double calcLinearRegressionSlope(
            Integer[] x,Double[] y
                                           )
    {
        int n = x.length;

        int    Ex  = 0;
        double Ey  = 0.0;
        double Exy = 0.0;
        double Ex2 = 0.0;

        for (int i = 0; i < n; i++)
            {

                if (x[i] == null || y[i] == null)
                    {throw new IllegalArgumentException("all x and y values must not be null");}

                Ex += x[i];
                Ey += y[i];
                Exy += x[i] * y[i];
                Ex2 += x[i] * x[i];

            }
        return (n * Exy - Ex * Ey) / (n * Ex2 - Math.pow(Ex,2));
    }

}