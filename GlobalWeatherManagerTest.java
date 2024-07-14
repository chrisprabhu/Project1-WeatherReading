import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalWeatherManagerTest {
    
    GlobalWeatherManager gwm = new GlobalWeatherManager( new File( "city_temperature.csv" ) );
    
    GlobalWeatherManagerTest() throws FileNotFoundException { }
    
    void constructor() throws FileNotFoundException {
        assertThrows( IllegalAccessException.class, () -> new GlobalWeatherManager( (File) null ) );
        assertThrows( IllegalAccessException.class, () -> new GlobalWeatherManager( new File( "city.csv" ) ) );
    }
    
    @Test
    void getReadingCount() throws FileNotFoundException {
        assertEquals( 2885066 - 2, gwm.getReadingCount() );
    }
    
    @Test
    void getReading() {
        /*
         gwm.getReading( 1 ): WeatherReading[region=Europe, country=Albania, state=, city=Tirana, month=1, day=2, year=1995, avgTemperature=-99.0]
         gwm.getReading( 20 ): WeatherReading[region=Europe, country=Albania, state=, city=Tirana, month=1, day=21, year=1995, avgTemperature=-99.0]
         gwm.getReading( 99999 ): WeatherReading[region=Middle East, country=Bahrain, state=, city=Manama, month=2,
         day=15, year=2015, avgTemperature=68.0]
                WeatherReading test = new WeatherReading(  );
                assertEquals(gwm.getReading( 20 ),  ) ;
        */
        WeatherReading reading = new WeatherReading( "Middle East", "Bahrain", "", "Manama", 2, 15, 2015, 68.0 );

//        assertSame( gwm.getReading( 99999 ), reading);
//        assertEquals(gwm.getReading( 99999 ), reading);
//                    new WeatherReading( "Middle East", "Bahrain", "", "Manama", 2, 15, 2015, 68.0 ) );
        
    }
    
    @Test
    void getReadings() {
    }
    
    @Test
    void testGetReadings() {
    }
    
    @Test
    void getCityListStats() {
    }
    
    @Test
    void iterator() {
    }
    
    @Test
    void getTemperatureLinearRegressionSlope() {
    }
    
    @Test
    void calcLinearRegressionSlope() {
    }
}