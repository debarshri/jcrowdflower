package nl.wisdelft.cf;

import nl.wisdelft.cf.weblayer.*;
import org.junit.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class CrowdFlowerFactoryTest {

    private CrowdFlowerFactory theCrowdFlowerFactory;

    @Before
    public void setUp()
    {
        theCrowdFlowerFactory = new CrowdFlowerFactory();
    }

    @Test
    public void shouldReturnWebCall()
    {
        assertThat(theCrowdFlowerFactory.createWebCall()).isInstanceOf(WebCall.class);
    }

    @Test
    public void shouldReturnWebUtil()
    {
        assertThat(theCrowdFlowerFactory.createWebUtil()).isInstanceOf(WebUtil.class);
    }

    @Test
    public void shouldReturnWebJobs()
    {
        assertThat(theCrowdFlowerFactory.createWebJobsCall()).isInstanceOf(WebJobsCall.class);
    }

    @Test
    public void shouldReturnWebJob()
    {
        assertThat(theCrowdFlowerFactory.createWebJobCall()).isInstanceOf(WebJobCall.class);
    }

    @Test
    public void shouldReturnAttribute()
    {
        assertThat(theCrowdFlowerFactory.createAttributes()).isNotNull();
    }

    @Test
    public void shouldReturnJSONObject()
    {
        assertThat(theCrowdFlowerFactory.createValues()).isNotNull();
    }
}
