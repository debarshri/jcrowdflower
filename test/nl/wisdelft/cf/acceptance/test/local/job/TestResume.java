package nl.wisdelft.cf.acceptance.test.local.job;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.job.*;
import org.junit.*;

import java.io.*;

public class TestResume {

    @Ignore
    @Test
    public void test() throws IOException
    {

        CrowdFlower crwod = new CrowdFlowerImpl();
        JobController myJobController = crwod.getJobController();
        myJobController.resume("211760");

    }

}
