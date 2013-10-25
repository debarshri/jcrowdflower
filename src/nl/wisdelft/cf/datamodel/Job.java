package nl.wisdelft.cf.datamodel;

import com.google.common.collect.*;
import nl.wisdelft.cf.job.*;
import org.json.*;
import org.slf4j.*;

import java.util.*;

public class Job {
    private String id;
    private static final Logger LOGGER = LoggerFactory.getLogger(Job.class);
    private Map<String,String> theAttributes;

    public Job(
            final String aId,
            final Map<String,String> aAttributes)
    {
        id = aId;
        theAttributes = aAttributes;
    }

    public Job()
    {
        id = "";
        theAttributes = Maps.newHashMap();
    }

    public Job(final JSONObject aJson)
    {
        id = "";
        theAttributes = Maps.newHashMap();
        jsonIterate(aJson);
    }

    public String getId()
    {
        return id;
    }

    public Map<String,String> getAttributes()
    {
        return theAttributes;
    }

    /*
To be replaced with jackson later
 */
    @SuppressWarnings("rawtypes")
    private void jsonIterate(JSONObject json)
    {

        Iterator iterate;
        try
        {

            iterate = json.keys();

            while (iterate.hasNext())
            {
                addAttribute(json, iterate);
            }
        }
        catch (JSONException e)
        {
            LOGGER.error("Cannot parse the incoming job details : ",
                         e);
        }
    }

    private void addAttribute(final JSONObject json, final Iterator aIterate) throws JSONException
    {
        String key = (String) aIterate.next();
        addProperty(key,
                    json.get(key).toString());
    }

    /*
    To be replaced with a builder pattern or with jackson
     */
    public void addProperty(
            String aProperty,
            String aValue)
    {

        if (aProperty.equals("id"))
        {
            id = aValue;
        }

        LOGGER.info("Adding aProperty" + aProperty + " to job with id  - {}", id);

        theAttributes.put("job[" + aProperty + "]", aValue);

    }

    public String getAttribute(String propertyName)
    {
       return theAttributes.get("job[" + propertyName + "]");
    }

    public void addProperty(
            JobAttribute property,
            String value)
    {
        addProperty(property.toString(), value);
    }

    public String getAttribute(JobAttribute propertyName)
    {
        return getAttribute(propertyName.toString());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id,
                            theAttributes);
    }

    @Override
    public boolean equals(final Object o)
    {
        return Objects.equals(id,
                              ((Job) o).getId()) &&
               Objects.equals(getClass(),
                              o.getClass()) &&
               Objects.equals(theAttributes,
                              ((Job) o).getAttributes());
    }
}
