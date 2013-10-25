package nl.wisdelft.cf;

import nl.wisdelft.cf.weblayer.*;
import org.json.*;
import org.slf4j.*;

import java.net.*;
import java.util.*;

public class Account {

    private Map<String, String> theAccountDetails;
    private static final String URL = "https://api.crowdflower.com/v1/account.json";
    private String theApiKey;
    private Logger logger = LoggerFactory.getLogger(Account.class);
    private final WebUtil theWebUtil;

    public Account(String aApiKey,
                   Map<String,String> aAccountDetails,
                   CrowdFlowerFactory aCrowdFlowerFactory)
    {
        theApiKey = aApiKey;
        theAccountDetails = aAccountDetails;
        theWebUtil = aCrowdFlowerFactory.createWebUtil();
    }

    public void refresh()
    {
        String wGet = "";
        JSONObject json;
        Iterator iterate;
        try
        {
            wGet = theWebUtil.urlReader(new URL(URL + "?key=" + theApiKey));
            json = new JSONObject(wGet);
            iterate = json.keys();

            logger.info("Adding account attributes");

            while (iterate.hasNext())
            {
                String key = (String) iterate.next();
                theAccountDetails.put(key,
                                   json.get(key).toString());
            }

        }
        catch (MalformedURLException e)
        {
            logger.error(e.toString());
        }
        catch (JSONException e)
        {
            logger.error(e.toString());
        }

    }

    public String get(String property)
    {
        return theAccountDetails.get(property);

    }

}
