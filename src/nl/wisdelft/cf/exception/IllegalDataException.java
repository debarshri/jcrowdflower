package nl.wisdelft.cf.exception;

import org.json.*;

public class IllegalDataException extends JSONException {

    /**
     *
     */
    private static final long serialVersionUID = 6712161918921182544L;

    public IllegalDataException(String data)
    {
        super(data);
    }

    public String toString()
    {
        return "IllegalDataException: The incoming data is malformed or not accessible completely";
    }

}
