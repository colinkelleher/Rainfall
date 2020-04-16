
package cs3318.model;
import cs3318.datastore.RainfallDataSource;
import cs3318.exceptions.IllegalRainfallPredictionDateException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * <h1>AveragingPrediction.java</h1>
 *
 * <p>This implements a simple averaging prediction model, in which the rainfall for the corresponding
 * date over a client specified (or the default three) previous years is used to calculate
 * a prediction for the rainfall</p>
 * <p>AveragingPrediction extends RainfallPrediction
 *  to make a rainfall prediction based on a
 *  number of samples </p>
 *
 * @author Colin Kelleher
 * @since 2019-10-30
 * @version 1.0
 *
 *
 */
public class AveragingPrediction extends RainfallPrediction {
    /**
     * This is setting the number of samples to be 3
     */
    private int numberOfSamples = 3;

    /**
     * This method is used to assign the source of the data used to
     * make the prediction
     * @param source is the data source from which the prediction will be made
     */
    public AveragingPrediction(RainfallDataSource source) {

        super(source);
    }

    /**
     * SETTER - This method is used to set the number of samples
     * @param n is the number of samples to be set
     */
    public void setNumberOfSamples(int n) {

        this.numberOfSamples = n;
    }

    /**
     * This function is used to make a rainfall prediction based on a {@code numberOfSamples}
     * containing data recorded from previous years
     * @param date is the date passed to predict to make a prediction
     * @return the prediction data or else {@code NaN}
     * @throws IllegalRainfallPredictionDateException should there be an error with the prediction date,
     * for example, if the date was in the wrong format, or didn't exist
     */
    @Override
    public Double predict(LocalDate date) throws IllegalRainfallPredictionDateException {
        Double [] predictionData = new Double[this.numberOfSamples];
        LocalDate sampleYear = date.minus(1, ChronoUnit.YEARS);
        for (int i = 0; i < this.numberOfSamples; i += 1) {
            if (!data.getRecordingDates().contains(sampleYear))
                throw new IllegalRainfallPredictionDateException(date);
            Integer indexOfDate = data.getRecordingDates().indexOf(sampleYear);
            if (indexOfDate > data.getPrecipitation().size())
                throw new IllegalRainfallPredictionDateException(date);
            predictionData[i] = data.getPrecipitation().get(indexOfDate);
            sampleYear = date.minus(1, ChronoUnit.YEARS);
        }

        return Arrays.stream(predictionData).mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
    }
}