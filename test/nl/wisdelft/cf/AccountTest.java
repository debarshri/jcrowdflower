package nl.wisdelft.cf;

import com.google.common.collect.*;
import nl.wisdelft.cf.weblayer.*;
import org.jetbrains.annotations.*;
import org.json.*;
import org.junit.*;
import org.junit.Test;
import org.mockito.*;

import java.net.*;
import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class AccountTest {
    @NotNull
    private Account theAccount;
    @NotNull
    @Mock
    private WebUtil theWebUtil;
    @NotNull
    @Mock
    private CrowdFlowerFactory theCrowdFlowerFactory;
    @NotNull private Map<String, String> theAccountDetails;

    @Before
    public void setUp() throws JSONException
    {
        MockitoAnnotations.initMocks(this);

        theAccountDetails = Maps.newHashMap();

        JSONObject myJSONObject = new JSONObject();
        myJSONObject.put("testKey",
                         "testValue");
        String myResult = myJSONObject.toString();
        when(theCrowdFlowerFactory.createWebUtil()).thenReturn(theWebUtil);
        when(theWebUtil.urlReader(any(URL.class))).thenReturn(myResult);

        theAccount = new Account("testApiKey",theAccountDetails,theCrowdFlowerFactory);

    }

    @Test
    public void shouldPopulateAccountDetailOnRefresh()
    {
        theAccount.refresh();
        assertThat(theAccountDetails).isNotEmpty();
        assertThat(theAccountDetails.get("testKey")).isEqualTo("testValue");

    }


}
