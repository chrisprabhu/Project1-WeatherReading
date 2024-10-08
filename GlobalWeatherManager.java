/**
 GlobalWeatherManager.java
 <p>
 This class should implement the GlobalWeatherManagerInterface interface.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;

/**
 * main() creates the GlobalWeatherManager object with the provided dataset.
 * @author Interface from Bill Barry
 * @author Chris Prabhu
 * @version 2024-07-11
 * */
public class GlobalWeatherManager implements GlobalWeatherManagerInterface, Serializable {
    /** The number of readings in the file */
    private       int                        readingCount;
    /** The list of weather readings */
    private final ArrayList <WeatherReading> weatherList = new ArrayList <WeatherReading>();
    
    /**
     * Constructor should take a single parameter, the file of weather data to be read.
     * Reads in the file and store the data in the weatherReadingsArrayList
     * @param file the dataset of weather data to be read
     * @throws FileNotFoundException if the file is not found
     */
    /*
    *  GWM - GENERAL (2.55 OUT OF 3.00):
       -  Xlint - clean?
       -  This class was to be iterable over WR's; that hasn't been done here.  If you have iterator(), you only need a small addition to the class:  implements GlobalWeatherManagerInterface, Iterable<WeatherReading> {
       -  Nice performance on the GWM constructor; quick!
       -  Constructor lacks precondition for null File reference.
       
     */
    public GlobalWeatherManager(File file) throws FileNotFoundException {
        /* The number of readings in the file */
        this.readingCount = 0;
        
        Scanner myReader = new Scanner( file );
        myReader.nextLine();
        
        while (myReader.hasNextLine()) {
            readingCount++;
            String   weatherRow     = myReader.nextLine();
            String[] weatherStrings = weatherRow.split( "," );
            
            WeatherReading
                    weatherReading =
                    new WeatherReading( weatherStrings[0],
                                        weatherStrings[1],
                                        weatherStrings[2],
                                        weatherStrings[3],
                                        Integer.parseInt( weatherStrings[4] ),
                                        Integer.parseInt( weatherStrings[5] ),
                                        Integer.parseInt( weatherStrings[6] ),
                                        Double.parseDouble( weatherStrings[7] ) );
            
            weatherList.add( weatherReading );
        }
        myReader.close();
        
        // WeatherReading objects will need natural ordering to support this ordering, sorting by:
        // country, state, city, year, month, and day (note the omission of region).
        Collections.sort( weatherList );
        
    }
    
    /** Displays the first three and last three rows of the sorted weather readings ArrayList weatherList.
     * Then gives the stats for a city using getCityListStats();
     * @param args the command line arguments
     * @throws FileNotFoundException if the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        GlobalWeatherManager gwm = new GlobalWeatherManager( new File( "city_temperature.csv" ) );
        
        System.out.println( ("Reading from the front: ") );
        for (int i = 0; i < 3; i++) {
            System.out.println( "gwm.weatherReadingsArrayList.get(i): " + gwm.weatherList.get( i ) );
        }
        System.out.println();
        System.out.println( ("Reading from the back: ") );
        for (int i = gwm.weatherList.size() - 1; i > gwm.weatherList.size() - 4; i--) {
            System.out.println( "gwm" + ".weatherReadingsArrayList.get(i): " + gwm.weatherList.get( i ) );
        }
        System.out.println();
        // city state country
        System.out.println( "gwm.getCityListStats( \"US\", \"Texas\", \"Abilene\" ) ); : " +
                            gwm.getCityListStats( "US", "Texas", "Abilene" ) );
        
        // Tests: delete me:
        System.out.println("gwm.getReading( 99999 ): " + gwm.getReading( 99999 ));
    }
    
    @Override
    public int getReadingCount() {
        return readingCount;
    }
    
    @Override
    public WeatherReading getReading(int index) {
        WeatherReading weatherReading = weatherList.get( index );
        return weatherReading;
    }
    
    @Override
    public WeatherReading[] getReadings(int index, int count) {
        return new WeatherReading[0];
    }
    
    @Override
    public WeatherReading[] getReadings(int index, int count, int month, int day) {
        return new WeatherReading[0];
    }
    
    @Override
    public CityListStats getCityListStats(String country, String state, String city) {
        
        int            location   = -1;
        int            min        = 0;
        int            max        = weatherList.size() - 1;
        WeatherReading searchTerm = new WeatherReading( "", country, state, city, -1, -1, -1, -1.0 );
        
        while (min <= max) {
            
            int mid     = (max + min) / 2;
            int compare = weatherList.get( mid ).compareCountryStateCity( searchTerm );
            
            if (compare == 0) {
                location = mid;
                break;  // found it!
            }
            
            else {
                if (compare < 0) {
                    min = mid + 1; // too small
                }
                
                // compare > 0
                else {
                    max = mid - 1; // too large
                }
            }
        }
        
        int                 starting_index = location;
        int                 count          = 1;
        ArrayList <Integer> years          = new ArrayList <Integer>();
        if (location > -1) {
            // Find the leftmost term.
            while (weatherList.get( starting_index ).compareCountryStateCity( searchTerm ) == 0) starting_index--;
            // 2507503
            // Sweep right to find the rightmost term.
            // Late can update this to not have to overlap in the sweep.
            while (weatherList.get( starting_index + count ).compareCountryStateCity( searchTerm ) == 0) {
                count++;
                if (!years.contains( weatherList.get( starting_index + count ).year() )) {
                    years.add( weatherList.get( starting_index + count ).year() );
                }
            }
        }
        int[] yearsArray = new int[years.size()];
        
        for (int i = 0; i < years.size(); i++) {
            yearsArray[i] = years.get( i );
        }
        
        return new CityListStats( starting_index, count, yearsArray );
    }
    
    @Override
    public Iterator <WeatherReading> iterator() {
        return weatherList.iterator();
    }
    
    /**
     @param readings array of readings to analyze.  Should typically be readings for a single day over
     a number of years; larger data sets will likely yield better results.  Ignores
     temperature data of -99.0, a default value indicating no temperature data was present.
     Must not be null and must contain at least two readings.
     
     @return the slope of the best-fit line.
     */
    public double getTemperatureLinearRegressionSlope(WeatherReading[] readings) {
        if (readings == null || readings.length < 2)
            throw new IllegalArgumentException( "readings must not be null and must contain at least two readings" );
        
        Integer[] x = new Integer[readings.length];
        Double[]  y = new Double[readings.length];
        
        for (int i = 0; i < readings.length; i++) {
            if (readings[i].avgTemperature() == -99.0) {
                x[i] = i;
                y[i] = readings[i].avgTemperature();
            }
        }
        return calcLinearRegressionSlope( x, y );
    }
    
    /**
     @param x an array of x values; must not be null and must contain at least two elements.
     @param y an array of y values; must be the same length as the x array and must not be null.
     
     @return the slope of the best-fit line.
     */
    public double calcLinearRegressionSlope(Integer[] x, Double[] y) {
        int n = x.length;
        
        int    Ex  = 0;
        double Ey  = 0.0;
        double Exy = 0.0;
        double Ex2 = 0.0;
        
        for (int i = 0; i < n; i++) {
            
            if (x[i] == null || y[i] == null) {
                throw new IllegalArgumentException( "all x and y values must not be null" );
            }
            
            Ex += x[i];
            Ey += y[i];
            Exy += x[i] * y[i];
            Ex2 += x[i] * x[i];
            
        }
        return (n * Exy - Ex * Ey) / (n * Ex2 - Math.pow( Ex, 2 ));
    }
}