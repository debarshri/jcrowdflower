package nl.wisdelft.cf.job;

import com.google.common.collect.*;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.weblayer.*;

import org.json.*;
import org.junit.*;
import org.junit.Test;
import org.mockito.*;

import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JobControllerTest {

    public static final String JOB_URL = "https://api.crowdflower.com/v1/jobs";
    private JobController theJobController;
    @Mock
    private CrowdFlowerFactory theCrowdFlowerFactory;
    @Mock private WebJobCall theWebJobCall;
    @Mock private WebUtil theWebUtil;
    private Map<String, String> theAttributes;
    private Job theJob;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        String myJobId = "1";
        theAttributes = Maps.newHashMap();
        String myApiKey = "nl.wisdelft.cf.acceptance.test-api-key";

        when(theCrowdFlowerFactory.createWebJobCall()).thenReturn(theWebJobCall);
        when(theCrowdFlowerFactory.createWebUtil()).thenReturn(theWebUtil);

        theJob = new Job(myJobId,theAttributes);
        theJobController = new JobControllerImpl(myApiKey,
                                theCrowdFlowerFactory);
    }


    @Test
    public void shouldInvokeWebCallOnCreate()
    {
        when(theWebUtil.urlTransform(eq(JOB_URL),anyString())).thenReturn("aRequest");
        when(theWebJobCall.createJob(anyString(), anyList())).thenReturn("{ \"id\" : \"1\"}");

        Job myJob = theJobController.create(theJob);

        verify(theWebJobCall).createJob(anyString(),anyList());

        assertThat(myJob.getId()).isEqualTo("1");
        assertThat(myJob.getAttributes()).hasSize(1);
    }

    @Test
    public void shouldInvokeWebCallOnGetJob() throws NullAPIKeyException, MalformedCrowdURLException, JSONException
    {
        when(theWebUtil.urlTransform(eq(JOB_URL),anyString())).thenReturn("aRequest");
        when(theWebJobCall.getJob(anyString())).thenReturn(new JSONObject("{ \"id\" : \"1\"}"));

        Job myJob = theJobController.getJob(theJob.getId());

        assertThat(myJob).isNotNull();
    }

    @Test
    public void shouldInvokeWebCallOnUpload() throws NullAPIKeyException
    {
        when(theWebUtil.urlTransform(eq(JOB_URL),anyString())).thenReturn("aRequest");

        String myBsolutePath = "nl.wisdelft.cf.acceptance.test-path";
        String myContentType = "nl.wisdelft.cf.acceptance.test-content-type";
        theJobController.upload(theJob, myBsolutePath, myContentType);

        verify(theWebJobCall).upload(eq(myBsolutePath), anyString(), eq(myContentType));
    }
}
