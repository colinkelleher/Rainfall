package cs3318.datastore;

import cs3318.exceptions.IllegalRainfallDataSourceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * <h1>RainfallDataSourceRandom.java - creates random data</h1>
 *
 * @author Colin Kelleher
 * @since 2019-10-30
 * @version 1.0
 *
 */
public class RainfallDataSourceRandom extends RainfallDataSource {
    /**
     * Constructor - used to generate a Random Data Source
     *
     * @param station is the station at which the rainfall was recorded
     * @throws IllegalRainfallDataSourceException should there be an error with the data source  or
     * should the data source not exist.
     */
    public RainfallDataSourceRandom(String station) throws IllegalRainfallDataSourceException {
        super(station, "Random Source");
    }

    /**
     * This method is used to get data from the random data source
     *
     * <p>a precipitation {@code ArrayList<>} of random size is created using the
     * random size integer a {@code HashMap<>}
     * of random size is also created using the random size integer</p>
     * <p>Precipitation and recording dates are randomly generated and then added appropriately</p>
     * @param source is a string representing the path to the data
     */
    @Override
    protected void getDataFrom(String source) {
        Random numberGenerator = new Random();
        int randomSize = 10 + numberGenerator.nextInt(100);
        this.precipitation = new ArrayList<>(randomSize);
        this.recordingDates = new HashMap<>(randomSize);

        for (int i = 0; i < randomSize; i += 1) {
            this.precipitation.add(i, numberGenerator.nextDouble());
            this.recordingDates.put(i, this.generateDate(i));
        }
    }

    /**
     * This method is used to generate a date
     *
     * @param i is a random number used to generate a date
     * @return todays date minus i, where i = the number of days to be subtracted
     */
    private LocalDate generateDate(int i) {
        LocalDate today =  LocalDate.now();
        return today.minusDays(i);
    }
}