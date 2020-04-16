
package cs3318.model;

import cs3318.datastore.RainfallDataSource;
import cs3318.exceptions.IllegalRainfallPredictionDateException;
import java.time.LocalDate;
import java.util.Objects;
/**
 * <h1>Rainfall Prediction - Used to predict Rainfall</h1>
 *
 *
 * @author Colin Kelleher
 * @since 2019-10-30
 * @version 1.0
 *
 */
public abstract class RainfallPrediction {
    /**
     * Constructor for Data source
     */
    protected RainfallDataSource data;

    /**
     * RainfallPrediction is used to make a prediction requiring data
     *
     * @param source is the rainfall data that is used to make the prediction
     */
    public RainfallPrediction(RainfallDataSource source) {

        this.data = Objects.requireNonNull(source);
    }

    /**
     * This method is used to make a prediction using a date
     * @param date is passed to this function to make the prediction
     * @throws IllegalRainfallPredictionDateException if there is an error with the date (i.e. format / invalid date etc..)
     * @return the IllegalRainfallPredictionDateException
     */
    public abstract Double predict(LocalDate date) throws IllegalRainfallPredictionDateException;
}
