package edu.matc.pricecheck;

/**
 * Created by student on 4/9/17.
 */
public class RunMessage {
    private static int status = 200;
    private static String message = "Operation successful.";

    /**
     * Gets status.
     *
     * @return Value of status.
     */
    public static int getStatus() {
        return status;
    }

    /**
     * Gets message.
     *
     * @return Value of message.
     */
    public static String getMessage() {
        return message;
    }

    /**
     * Sets new status.
     *
     * @param statusIn New value of status.
     */
    public static void setStatus(int statusIn) {
        status = statusIn;
    }

    /**
     * Sets new message.
     *
     * @param messageIn New value of message.
     */
    public static void setMessage(String messageIn) {
        message = messageIn;
    }

    /**
     * This return the message in JSON
     * @return messageOut
     */
    public static String getMessageJSON() {
        String messageOut =  "{\"message\" : \"" + status + " : "
                + message + "\"}";

        return messageOut;
    }

    /**
     * This return the message in HTML
     * @return messageOut
     */
    public static String getMessageHTML() {
        String messageOut =  "<h2><span>message:</span>"+ status + " : " + message + "</h2>";

        return messageOut;
    }

}
