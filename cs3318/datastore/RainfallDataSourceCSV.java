
package cs3318.datastore;

import cs3318.exceptions.IllegalRainfallDataSourceException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * <h1>RainfallDataSourceCSV.java - deals with the Data and getting the data from the <b>CSV</b> file</h1>
 * <p>{@code RainfallDataSource} is used to handle the data from its source</p>
 * <p>The <b>precipitation</b> stored in {@code ArrayList<Double>} and the <b>recording dates</b>
 * stored in {@code HashMap<Integer, LocalDate>} are used for future predictions</p>
 * <p>This gets the data from its source using {@code getDataFrom}
 * and processes it using {@code getDataPrecipitation} and {@code getRecordingDates} </p>
 *
 * Constructor
 *
 * @author Colin Kelleher
 * @since 2019-10-30
 * @version 1.0
 *
 */
public class RainfallDataSourceCSV extends RainfallDataSource {
    /**
     * RainfallDataSourceCSV extends RainfallDataSource
     * and focuses on CSV files being used as a DataSource.
     *
     * RainfallDataSourceCSV implements methods to read the rainfall data from a CSV file
     *
     * @param station indicates which station the data was recorded at (i.e. Cork Airport)
     * @param dataSource is the path to where the data is stored. The data that was previously recorded
     *                   and will be used for predictions
     * @throws IllegalRainfallDataSourceException should the wrong station or data source be passed
     */
    public RainfallDataSourceCSV(String station, String dataSource) throws IllegalRainfallDataSourceException {
        super(station, dataSource);
    }

    /**
     * {@code getDataFrom} is used to read data from the file using {@code Scanner}
     * and throws an exception if the file doesn't exist.
     * @param source is the data file from which the data will be read
     * @throws IllegalRainfallDataSourceException if the file does not exist, or if there is an error in gaining access
     * to the file/wrong path.
     */
    @Override
    protected void getDataFrom(String source) throws IllegalRainfallDataSourceException {
        try (Scanner scanner = new Scanner(new File(source))){
            this.readCSVDataFrom(scanner);
        } catch (FileNotFoundException e) {
            throw new IllegalRainfallDataSourceException(e,source);
        }
    }

    /**
     * {@code readCSVDataFrom} uses {@param scanner} to read data from the data from the CSV data file
     * Precipitation data is stored in an {@code ArrayLIst<>}
     * Dates of recording is stored in a {@code HashMap<>}
     * @param scanner is used to read the data from the CSV file
     */
    private void readCSVDataFrom (Scanner scanner) {
        Integer recordNumber = 0;
        this.precipitation = new ArrayList<>();
        this.recordingDates = new HashMap<>();
        while (scanner.hasNext()) {
            String record = scanner.next();
            try {
                Double d = this.getPrecipitationFrom(record);
                this.precipitation.add(d);
                this.recordingDates.put(recordNumber++, this.getDateFrom(record));
            } catch (NumberFormatException | DateTimeParseException e ) {
                System.out.println(e);
            }
        }
    }

    /**
     * Getter used for Precipitation
     * This getter is used to return the precipitation value from a file
     *
     * @param line is a line within the file, which will then be parsed accordingly
     * @return a double from the string and this double will be the precipitation
     */
    private Double getPrecipitationFrom (String line) {
        String [] fields = line.split(",");
        return Double.parseDouble(fields[1]);

    }

    /**
     * Getter used for Date.
     * This getter is used to return the date of a record from the file
     * The line/string is parsed on the commas, and dates are formatted using
     * {@code DateTimeFormatter} and are {@code .ofPattern("dd-MMM-yy"}
     *
     * @param line is the line of data from the file
     * @return a single date, parsed from the string (i.e a date of a record)
     */
    private LocalDate getDateFrom(String line) {
        String [] fields = line.split(",");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MMM-yy");
        LocalDate ld = LocalDate.parse(fields[0],f);
        if (ld.isAfter(LocalDate.now()))
            ld = ld.minus(100, ChronoUnit.YEARS);
        return ld;
    }
}
