package nl.wisdelft.cf.job;

import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;

class AsynchronousUploadTask implements Runnable {
    private JobController theJobController;
    private final Job theJob;
    private final String theAbsolutePath;
    private final String theContentType;

    public AsynchronousUploadTask(
            final JobController aJobController,
            final Job aJob,
            final String aAAbsolutePath,
            final String aContentType)
    {

        theJobController = aJobController;
        theJob = aJob;
        theAbsolutePath = aAAbsolutePath;
        theContentType = aContentType;
    }

    @Override
    public void run()
    {
        try
        {
            theJobController.upload(theJob,
                                  theAbsolutePath,
                                  theContentType);
        }
        catch (NullAPIKeyException e)
        {
            e.printStackTrace();
        }
    }
}
