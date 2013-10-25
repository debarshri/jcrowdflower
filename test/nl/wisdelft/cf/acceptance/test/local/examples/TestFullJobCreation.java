package nl.wisdelft.cf.acceptance.test.local.examples;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;
import org.junit.*;

import java.io.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class TestFullJobCreation {

    private static final String TITLE = "Purely created using Java";
    private static final String INSTRUCTION = CrowdFlowerTestDataFactory.createInstruction();
    private static final String CML = CrowdFlowerTestDataFactory.createCML();

    @Ignore
    @Test
    public void test() throws IOException
    {
        JobController myJobController = CrowdFlowerFactory.getJobController();
        Job myJob = new Job();
        myJob.addProperty(JobAttribute.TITLE, TITLE);
        myJob.addProperty(JobAttribute.INSTRUCTIONS,INSTRUCTION);
        myJob.addProperty(JobAttribute.CML,CML);
        myJobController.create(myJob);

        assertThat(myJob.getAttribute(JobAttribute.TITLE)).isEqualTo(TITLE);
    }

}
