package nl.wisdelft.cf.acceptance.test.local.job;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.job.*;
import org.junit.*;

import java.io.*;
import java.util.*;

public class TestParam {

    @Ignore
    @Test
    public void test() throws IOException, NullAPIKeyException
    {
        JobController myJobController = CrowdFlowerFactory.getJobController();

        Job myJob = myJobController.getJob("214510");

        Map<String, String> param = new HashMap<String, String>();

        param.put("force",
                  "true");

        myJobController.upload(myJob.getId(), "log/crowd.csv",
                   "text/csv",
                   param);


    }

}
