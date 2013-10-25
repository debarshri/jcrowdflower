package nl.wisdelft.cf.acceptance.test.local.job;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.job.*;
import org.junit.*;

import java.io.*;

public class TestJobPause {

    @Ignore
    @Test
    public void test() throws IOException
    {
        CrowdFlower crowd = new CrowdFlowerImpl();
        JobController myJobController = crowd.getJobController();
        myJobController.pause("211760");

    }

}
