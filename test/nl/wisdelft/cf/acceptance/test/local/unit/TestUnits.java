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

public class TestUnits {

    @Ignore
    @Test
    public void shouldGetAllUnits() throws IOException, JSONException, NullAPIKeyException
    {
        JobController myJobController = CrowdFlowerFactory.getJobController();

        Job myJob = myJobController.getJob("207321");

        Unit myUnit = myJobController.getJobUnits(myJob.getId()).get(0);

        System.out.println(myUnit.getAttribute(UnitAttribute.DATA));

    }

}
