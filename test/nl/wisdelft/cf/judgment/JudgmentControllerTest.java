package nl.wisdelft.cf.judgment;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.weblayer.*;
import org.junit.*;
import org.mockito.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JudgmentControllerTest {

    private JudgmentController theJudgmentController;
    @Mock
    private CrowdFlowerFactory theCrowdFlowerFactory;
    private String theApiKey = "nl.wisdelft.cf.acceptance.test-api-key";
    @Mock
    private WebUtil theWebUtil;
    @Mock
    private WebCall theWebCall;
    private String theTestJudgmentId = "nl.wisdelft.cf.acceptance.test-judgment-id";
    private String theTestJobId = "nl.wisdelft.cf.acceptance.test-job-id";


    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        when(theCrowdFlowerFactory.createWebUtil()).thenReturn(theWebUtil);
        when(theCrowdFlowerFactory.createWebCall()).thenReturn(theWebCall);

        theJudgmentController = new JudgmentControllerImpl(theApiKey, theCrowdFlowerFactory);

    }

    @Test
    public void shouldCreateJudgment() throws Exception
    {
        Judgment myJudgment = CrowdFlowerTestDataFactory.createJudgment();
        String myValue = "/" + myJudgment.getJobId() + "/judgments.json?key=" + theApiKey;

        theJudgmentController.create(myJudgment);

        verify(theWebUtil).urlTransform(anyString(), eq(myValue));
        verify(theWebCall).create(anyString(), anyList());

    }

    @Test
    public void shouldGetJudgment() throws Exception
    {
        String myRestUrl = "/" + theTestJobId + "/judgments/" + theTestJudgmentId + ".json?key=" + theApiKey;

        when(theWebCall.get(anyString())).thenReturn("{}");

        Judgment myJudgment = theJudgmentController.getJudgment(theTestJobId, theTestJudgmentId);

        verify(theWebUtil).urlTransform(anyString(), eq(myRestUrl));
        assertThat(myJudgment).isNotNull();
    }

    @Test
    public void shouldUpdateJudgment() throws Exception
    {
                  //todo
    }

    @Test
    public void testDelete() throws Exception
    {
        //todo

    }
}
