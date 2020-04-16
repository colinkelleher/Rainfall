package cs3318.datastore;

import cs3318.exceptions.IllegalRainfallDataSourceException;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <h1>RainfallDataSource.java - deals with the Data and getting the data from its source</h1>
 * <p>{@code RainfallDataSource} is used to handle the data from its source</p>
 * <p>The <b>precipitation</b> stored in {@code ArrayList<Double>} and the <b>recording dates</b>
 * stored in {@code HashMap<Integer, LocalDate>} are used for future predictions</p>
 * <p>This gets the data from its source using {@code getDataFrom}
 * and processes it using {@code getDataPrecipitation} and {@code getRecordingDates} </p>
 *
 * @author Colin Kelleher
 * @since 2019-10-30
 * @version 1.0
 *
 */
public abstract class RainfallDataSource {
    private final String dataSource;
    private final String station;
    /**
     * An {@code ArrayList<Double>} to hold precipitation data
     */
    protected ArrayList<Double> precipitation;
    /**
     * A {@code HashMap<Integer, LocalDate>} to hold recording dates
     */
    protected HashMap<Integer, LocalDate> recordingDates;
    /**
     * Constructor
     * @param station is a string representing which weather station the data came from (i.e. Cork Airport)
     * @param dataSource is the path to the file in which the data is located (the CSV file)
     * @throws IllegalRainfallDataSourceException is thrown if the data doesn't exist or the wrong path to the
     * data is provided.
     */
    public RainfallDataSource(String station , String dataSource) throws IllegalRainfallDataSourceException {
        this.dataSource = Objects.requireNonNull(dataSource);
        this.station = Objects.requireNonNull(station);
        this.getDataFrom (this.dataSource);

    }

    /**
     * This is a method to return the precipitation
     * @return The precipitation attribute in the form of {@code Collections.unmodifiableList}
     */
    public List<Double> getPrecipitation() {

        return Collections.unmodifiableList(this.precipitation);
    }

    /**
     * This is a method to return the dates of recording the precipitation
     * @return The recording dates in the form of an {@code ArrayList}
     */
    public List<LocalDate> getRecordingDates () {
        return Collections.unmodifiableList(new ArrayList<>(this.recordingDates.values()));
    }

    /**
     * This is a method used to get the data from its source
     * @param source is a string representing the path to the data
     * @throws IllegalRainfallDataSourceException should the source not be correct or
     * not exist, and therefore this Exception is then raised.
     */
    protected abstract void getDataFrom(String source) throws IllegalRainfallDataSourceException;
}
