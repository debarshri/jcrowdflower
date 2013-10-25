package nl.wisdelft.cf.exception;

import java.net.*;

public class MalformedCrowdURLException extends MalformedURLException {

    private static final long serialVersionUID = 1L;
    private String URL;

    public MalformedCrowdURLException(String URL)
    {
        super(URL);
        this.URL = URL;
    }

    public String toString()
    {
        return "MalformedCrowdURLException: Crowd web service URL is" + URL
               + "is malformed";

    }
}
