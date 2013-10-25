package nl.wisdelft.cf.acceptance.test.local.examples;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;

public class CrowdFlowerTutorial {

    public static void main(String[] args)
    {
        /**
         * Alternatively you can declare it as
         * CrowdFlowerFactory.setProperties("/path/to/properties/file");
         */

        JobController myJobController = CrowdFlowerFactory.getJobController();
        Job myJob = new Job();

        /**
         * You must have following three properties to create a job successfully
         */

        myJob.addProperty(JobAttribute.TITLE,"Test Job title");
        myJob.addProperty(JobAttribute.INSTRUCTIONS,"Test instructions");
        myJob.addProperty(JobAttribute.CML,"<cml>nl.wisdelft.cf.acceptance.test cml </cml>");

        Job myJobAfterCreation = myJobController.create(myJob);
    }
}
