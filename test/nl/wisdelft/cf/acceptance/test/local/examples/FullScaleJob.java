package nl.wisdelft.cf.acceptance.test.local.examples;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.unit.*;
import org.junit.*;
import org.junit.Test;

import java.io.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static nl.wisdelft.cf.CrowdFlowerTestDataFactory.*;

public class FullScaleJob {

    private static final String INSTRUCTION = createInstruction();
    private CrowdFlower theCrowdFlower;
    private static final String CML = createCML();

    @Before
    public void setUp() throws IOException
    {
        theCrowdFlower = new CrowdFlowerImpl();
    }

    @Ignore
    @Test
    public void endToEndCrowdFlower() throws NullAPIKeyException
    {
        try
        {
            JobController myJobController = theCrowdFlower.getJobController();

            Job myJob = CrowdFlowerTestDataFactory.createSampleJob();
            Job myJobAfterCreation = myJobController.create(myJob);

            assertThat(myJobAfterCreation.getAttribute(JobAttribute.INSTRUCTIONS)).isEqualTo(INSTRUCTION);
            assertThat(myJobAfterCreation.getAttribute(JobAttribute.CML)).isEqualTo(CML);

            myJobController.upload(myJobAfterCreation, createPathToData(), createApplicationType());

            Thread.sleep(1500);

            assertThat(myJobController.getUnitsStatus(myJobAfterCreation.getId())).isNotNull();

			/* Creating a myUnitController */
            UnitController myUnitController = theCrowdFlower.getUnitController();
            Unit myUnit = new Unit();

            myUnit.addProperty("job_id",myJobAfterCreation.getId());
            myUnit.addProperty("data", CrowdFlowerTestDataFactory.createUnitData());
            myUnitController.create(myUnit);

            assertThat(myJobController.getJobUnits(myJobAfterCreation.getId()).size()).isEqualTo(4);
        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
