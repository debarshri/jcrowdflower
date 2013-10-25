package nl.wisdelft.cf.job;

import nl.wisdelft.cf.datamodel.*;
import org.junit.*;
import org.mockito.*;

import static org.mockito.Mockito.verify;

public class AsynchronousUploadTaskTest {

    @Mock
    private JobController theJobController;
    @Mock
    private Job theJob;
    private String theAbsolutePath = "nl.wisdelft.cf.acceptance.test-path";
    private String theContentType = "nl.wisdelft.cf.acceptance.test-contentType";
    private AsynchronousUploadTask theTask;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        theTask = new AsynchronousUploadTask(theJobController,
                                             theJob,
                                             theAbsolutePath,
                                             theContentType);

    }

    @Test
    public void shouldInvokeUploadOnRun() throws Exception
    {
        theTask.run();

        verify(theJobController).upload(theJob,theAbsolutePath,theContentType);
    }
}
