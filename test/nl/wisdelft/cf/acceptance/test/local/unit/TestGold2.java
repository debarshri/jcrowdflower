package nl.wisdelft.cf.acceptance.test.local.unit;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.junit.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class TestGold2 {

    @Ignore
    @Test
    public void shouldAddGold() throws IOException, JSONException, InterruptedException
    {

        String cml = "<img id=\"\" src=\"{{images}}\"><cml:checkbox validates=\"required\" label=\"Contains human\" class=\"\"></cml:checkbox>";

        try
        {
            CrowdFlower crowd = new CrowdFlowerImpl();
            JobController myJobController = crowd.getJobController();
            Job myJob = CrowdFlowerTestDataFactory.createSampleJob();
            Job myJobAfterCreation = myJobController.create(myJob);

            myJobController.upload(myJobAfterCreation, "log/crowd.csv",
                                   "text/csv");

            Thread.sleep(1000);

            Job myJobToBeUpdated = new Job();

            myJobToBeUpdated.addProperty(JobAttribute.ID, myJobAfterCreation.getId());
            myJobToBeUpdated.addProperty(JobAttribute.TITLE,
                            "Detect a human in the picture");
            myJobToBeUpdated.addProperty(
                    JobAttribute.INSTRUCTIONS,
                    "In the given picture find a human figure, If it has a human then click on contains a human");

            myJobToBeUpdated.addProperty(JobAttribute.CML,
                                           cml);

            myJobController.update(myJobToBeUpdated);

            Thread.sleep(4000);

            Map<String, String> param = new HashMap<String, String>();
            param.put("check",
                      "contains_human");
            myJobController.addRemoveGold(myJobAfterCreation.getId(), param);

            List<Unit> myUnits = myJobController.getJobUnits(myJobAfterCreation.getId());
            Unit myUnit = myUnits.get(0);
            UnitController myUnitController = crowd.getUnitController();
            myUnitController.addGold(myUnit,"contains_human",
                     "true",
                     "It has a human");
        }
        catch (NullAPIKeyException e)
        {
            e.printStackTrace();
        }
    }


}
