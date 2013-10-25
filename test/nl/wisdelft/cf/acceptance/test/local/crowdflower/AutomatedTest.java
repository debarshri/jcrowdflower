package nl.wisdelft.cf.acceptance.test.local.crowdflower;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;
import org.junit.*;

import java.io.*;
import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class AutomatedTest {

    private CrowdFlower theCrowdFlower;

    @Before
    public void setUp() throws IOException
    {
        theCrowdFlower = new CrowdFlowerImpl();
    }

    @Ignore
    @Test
    public void shouldReturnJob() throws IOException
    {
        JobController myJobController = theCrowdFlower.getJobController();
        assertThat(myJobController).isNotNull();
    }

    @Ignore
    @Test
    public void shouldReturnListOfJobs() throws IOException
    {
        List<Job> myJobControllers = theCrowdFlower.getJobs();
        assertThat(myJobControllers).isNotEmpty();
    }
}
