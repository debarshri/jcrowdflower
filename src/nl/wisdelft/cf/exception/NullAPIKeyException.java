package nl.wisdelft.cf.exception;

public class NullAPIKeyException extends Exception {

    private static final long serialVersionUID = 1L;

    public NullAPIKeyException()
    {
        super();
    }

    public String toString()
    {
        return "NullAPIKeyException: CrowdFlower's API KEY is not set";

    }

}
