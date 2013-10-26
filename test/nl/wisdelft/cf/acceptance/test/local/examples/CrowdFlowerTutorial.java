package nl.wisdelft.cf.acceptance.test.local.examples;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.order.*;

public class CrowdFlowerTutorial {

    public static void main(String[] args) throws NullAPIKeyException, InterruptedException
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

        myJob.addProperty(JobAttribute.TITLE, "Test Job title");
        myJob.addProperty(JobAttribute.INSTRUCTIONS, "Test instructions");
        myJob.addProperty(JobAttribute.CML, "<cml>Test cml </cml>");
        myJob.addProperty(JobAttribute.PAGES_PER_ASSIGNMENT,
                          "0.1");
        myJob.addProperty(JobAttribute.UNITS_PER_ASSIGNMENT,
                          "1");

        Job myJobAfterCreation = myJobController.create(myJob);

        myJobController.upload(myJobAfterCreation, "log/crowd.csv", "text/csv");

        OrderController myOrderController = CrowdFlowerFactory.getOrderController();

        Order myOrder = new Order(myJobAfterCreation.getId());
        myOrder.setDebitUnitCount("3");
        myOrder.addChannel("amt");

        /*
        * Costs money
        */

        myOrderController.create(myOrder);
        /**
         * Takes some time to create
         */
        myOrderController.cancel(myJobAfterCreation.getId());
    }
}
