package nl.wisdelft.cf.acceptance.test.local.examples;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;
import org.junit.*;

import java.io.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class TestGoogleNewsExample {

    private static final String TITLE = "Google news task refactor";
    private static final String INSTRUCTION = "Represent each news article using one word";
    private static final String GOOGLE_RSS_URL = "http://news.google.com/?output=rss";
    private JobController theJobController;

    @Before
    public void setUp() throws IOException
    {
        CrowdFlower myCrowdFlower = new CrowdFlowerImpl();
        theJobController = myCrowdFlower.getJobController();
    }

    @Ignore
    @Test
    public void shouldCreateGoogleNewsTask() throws IOException
    {

		/* fetch the Api-key and all*/

        Job myJob = new Job();
        myJob.addProperty("title", TITLE);
        myJob.addProperty("instructions",INSTRUCTION);
        Job myJobAfterCreation = theJobController.create(myJob);
        theJobController.upload(myJobAfterCreation,GOOGLE_RSS_URL);

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        assertThat(theJobController.getJobUnits(myJobAfterCreation.getId())).isNotEmpty();
    }
}
