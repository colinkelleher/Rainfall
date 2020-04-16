package cs3318.exceptions;

import java.time.LocalDate;

/**
 * <b>IllegalRainfallPredictionDateException</b> is an exception class used
 * if errors in the<b>Prediction Date</b> occur. This exception includes
 * exception chaining
 * illegalRainfallPredictionDateException extends Exception making it an exception
 *
 *
 * @author Colin Kelleher
 * @since 2019-10-30
 * @version 1.0
 */
public class IllegalRainfallPredictionDateException extends Exception{
    /**
     * Getter method to get the date
     *
     * @return the date
     */
    public LocalDate getDate() {

        return date;
    }

    private final LocalDate date;

    /**
     * Constructor for the PredictionDateException
     * @param date is the date that has raised the exception
     */
    public IllegalRainfallPredictionDateException(LocalDate date) {
        this.date=date;
    }
}
