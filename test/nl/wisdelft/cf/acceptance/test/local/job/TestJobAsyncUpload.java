package nl.wisdelft.cf.acceptance.test.local.job;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.job.*;
import org.junit.*;

import java.io.*;

public class TestJobAsyncUpload {

    private JobController theJobController;
    private Job theJob;

    @Before
    public void setUp() throws IOException
    {
        theJobController = CrowdFlowerFactory.getJobController();
        theJob = CrowdFlowerTestDataFactory.createSampleJob();
    }

    @Ignore
    @Test
    public void shouldUploadDataAsThreaded() throws IOException
    {
         theJobController.create(theJob);

        try
        {
            theJobController.upload(theJob,CrowdFlowerTestDataFactory.createPathToData(),
                          CrowdFlowerTestDataFactory.createApplicationType(),
                          true);
        }
        catch (NullAPIKeyException e)
        {
            e.printStackTrace();
        }
    }

}
