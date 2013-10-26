package nl.wisdelft.cf.acceptance.test.local.job;


import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.order.*;
import org.junit.*;

import java.io.*;

public class TestJobOrder {

    @Ignore
    @Test
    public void test() throws IOException
    {
        String cml = "<img id=\"\" src=\"{{images}}\"><cml:checkbox validates=\"required\" label=\"Contains human\" class=\"\"/>";

        JobController myJobController = CrowdFlowerFactory.getJobController();

        Job myJob = CrowdFlowerTestDataFactory.createSampleJob();

        Job myJobAfterCreation = myJobController.create(myJob);

        try
        {
            myJobController.upload(myJobAfterCreation, "log/crowd.csv", "text/csv");
        }
        catch (NullAPIKeyException e1)
        {
            e1.printStackTrace();
        }


        Job myJobToBeUpdated = new Job();

        myJobToBeUpdated.addProperty(JobAttribute.ID, myJobAfterCreation.getId());

        myJobToBeUpdated.addProperty(JobAttribute.TITLE,
                        "Detect a human in the picture");

        myJobToBeUpdated.addProperty(
                JobAttribute.INSTRUCTIONS,
                "In the given picture find a human figure, If it has a human then click on contains a human");

        myJobToBeUpdated.addProperty(JobAttribute.CML,
                        cml);


        myJobToBeUpdated.addProperty(JobAttribute.PAGES_PER_ASSIGNMENT,
                        "0.1");
        myJobToBeUpdated.addProperty(JobAttribute.UNITS_PER_ASSIGNMENT,
                        "1");

        try
        {
            myJobController.update(myJobToBeUpdated);
        }
        catch (NullAPIKeyException e)
        {
            e.printStackTrace();
        }

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

}
