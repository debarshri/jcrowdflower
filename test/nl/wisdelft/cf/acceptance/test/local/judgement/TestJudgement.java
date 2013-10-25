package nl.wisdelft.cf.acceptance.test.local.judgement;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.unit.*;
import org.json.*;
import org.junit.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class TestJudgement {

    @Test
    @Ignore
    public void shouldReturnJudgments() throws IOException, JSONException
    {
        CrowdFlower myCrowdFlower = new CrowdFlowerImpl();
        JobController myJobController = myCrowdFlower.getJobController();
        List<Judgment> judg = myJobController.getJudgments("214182");

        System.out.println(judg.size());


        Unit myUnit = myJobController.getUnit("214182", "306594158");
        System.out.println(myUnit.getAttribute(UnitAttribute.UPDATED_AT));
    }
}
