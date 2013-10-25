package nl.wisdelft.cf.job;

import com.google.common.collect.*;
import org.junit.*;

import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class ChannelTest {

    private Channel theChannel;

    @Before
    public void setUp()
    {
        Set<String> myAvailableChannels = Sets.newHashSet("nl.wisdelft.cf.acceptance.test-available");
        Set<String> myEnabledChannels = Sets.newHashSet("nl.wisdelft.cf.acceptance.test-enabled");

        theChannel = new Channel("nl.wisdelft.cf.acceptance.test-job-id",
                                 myAvailableChannels,
                                 myEnabledChannels);
    }

    @Test
    public void shouldReturnAvailableChannels()
    {
        assertThat(
                Iterables
                        .getOnlyElement(
                                theChannel.getAvailableChannels()
                        )
        ).isEqualTo("nl.wisdelft.cf.acceptance.test-available");
    }

    @Test
    public void shouldReturn()
    {
        assertThat(
                Iterables
                        .getOnlyElement(
                                theChannel.getEnabledChannels()
                        )
        ).isEqualTo("nl.wisdelft.cf.acceptance.test-enabled");
    }

}
