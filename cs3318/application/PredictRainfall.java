package cs3318.application;

import cs3318.datastore.RainfallDataSource;
import cs3318.datastore.RainfallDataSourceCSV;
import cs3318.datastore.RainfallDataSourceRandom;
import cs3318.exceptions.IllegalRainfallDataSourceException;
/**
 * <h1>PredictRainfall - Is the main Java program used to run the application</h1>
 *
 * <p>PredictRainfall is the main application used to run the prediction application</p>
 * <p>This file prints out the Recording Dates and Precipitation from {@code RainfallDataSourceCSV}</p>
 *
 *
 * @author Colin Kelleher
 * @since 2019-10-30
 * @version 1.0
 *
 */
public class PredictRainfall {
    /**
     * This is the <b>Main</b> function
     *
     * <p>PredictRainfall contains a {@code try} and {@code except} to throw an exception should
     *  there be a problem with the datasource</p>
     *
     * <p>Recording dates and Precipitation are then printed out</p>
     * @throws IllegalRainfallDataSourceException if there is an error with the data source,
     * for example if the data file doesn't exit, or a different name exists
     * @param args passed to the main function
     *
     */
    public static void main(String [] args) throws IllegalRainfallDataSourceException {
        RainfallDataSource corkAirport;

        try {
            corkAirport = new RainfallDataSourceCSV("Cork Airport", "resources/Rainfall-daily-cork-airport-1962-present.csv");
        }
        catch (IllegalRainfallDataSourceException e) {
            corkAirport = new RainfallDataSourceRandom("Cork Airport (dummy)");
        }

        corkAirport.getRecordingDates().forEach(System.out::println);
        corkAirport.getPrecipitation().forEach(System.out::println);
    }
}