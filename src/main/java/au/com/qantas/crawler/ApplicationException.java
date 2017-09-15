package au.com.qantas.crawler;

/**
 * Base class for all exceptions raised internally to signal that an error
 * condition has occurred.
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 6347675023685108688L;

    /**
     * Instantiates a new app exception.
     */
    public ApplicationException() {
        super("Error in Web crawler.");
    }

    /**
     * Instantiates a new app exception.
     *
     * @param message
     *            the message
     */
    public ApplicationException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new app exception.
     *
     * @param message
     *            the message
     * @param tr
     *            the Throwable
     */
    public ApplicationException(final String message, final Throwable tr) {
        super(message, tr);
    }

}
