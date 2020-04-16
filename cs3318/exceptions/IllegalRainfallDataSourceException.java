package cs3318.exceptions;

/**
 * <b>illegalRainfallDataSourceException</b> is an exception class used
 * if errors in the <b>Data Source</b> occur. This exception includes
 * exception chaining
 * illegalRainfallDataSourceException extends Exception making it an exception
 *
 *
 * @author Colin Kelleher
 * @since 2019-10-30
 * @version 1.0
 */

public class IllegalRainfallDataSourceException extends Exception {
    /**
     * This is a getter for the source
     * @return the source
     */
    public String getSource() {
        return source;
    }

    private final String source;

    /**
     * This is a CONSTRUCTOR for the exception
     * @param e is the exception that is raised
     * @param source is the the data source
     */
    public IllegalRainfallDataSourceException(Exception e, String source) {
        super(e);
        this.source = source;
    }
}
