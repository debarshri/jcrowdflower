package nl.wisdelft.cf.exception;

public class CrowdFlowerException extends Exception {

    private static final long serialVersionUID = 1L;

    private String exception;

    public CrowdFlowerException(String exception)
    {
        super();
        this.exception = exception;
    }

    public String toString()
    {
        return "CrowdFlowerException: " + exception;
    }

}
