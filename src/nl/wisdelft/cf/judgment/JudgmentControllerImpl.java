package nl.wisdelft.cf.judgment;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.weblayer.*;
import org.fest.util.*;
import org.json.*;

import java.util.*;

import static nl.wisdelft.cf.weblayer.WebUtil.convertAttributesToNameValuePair;

public class JudgmentControllerImpl implements JudgmentController {

    private final String URL = "https://api.crowdflower.com/v1/jobs";
    private final String apiKey;
    private final WebUtil theWebUtil;
    private final WebCall theWebCall;

    public JudgmentControllerImpl(String apiKey)
    {
        this(apiKey, new CrowdFlowerFactory());
    }

    @VisibleForTesting
    JudgmentControllerImpl(
            String apiKey,
            CrowdFlowerFactory aCrowdFlowerFactory)
    {
        this.apiKey = apiKey;
        theWebUtil = aCrowdFlowerFactory.createWebUtil();
        theWebCall = aCrowdFlowerFactory.createWebCall();
    }

    @Override
    public void create(Judgment aJudgment)
    {

        String augURL = theWebUtil.urlTransform(URL,
                                              "/" + aJudgment.getJobId()
                                              + "/judgments.json?key=" + apiKey);
        theWebCall.create(augURL,convertAttributesToNameValuePair(aJudgment.getAttributes()));
    }

    @Override
    public Judgment getJudgment(String aJobId,String aJudgmentId)
    {
        String augURL = theWebUtil.urlTransform(URL,
                                              "/" + aJobId
                                              + "/judgments/" + aJudgmentId + ".json?key=" + apiKey);
        try
        {
            return new Judgment(new JSONObject(theWebCall.get(augURL)));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Judgment aJudgment)
    {
        String myMorph = String.format("/%s/judgments/%s.json?key=%s", aJudgment.getJobId(),
                                       aJudgment.getJudgmentId(),
                                       apiKey);

        String augURL = theWebUtil.urlTransform(URL,
                                                myMorph);
        theWebCall.update(augURL, convertAttributesToNameValuePair(aJudgment.getAttributes()));
    }

    @Override
    public void delete(String aJobId, String aJudgmentId)
    {
        String myMorph = "/" + aJobId
                         + "/judgments/" + aJudgmentId + ".json?key=" + apiKey;

        String augURL = theWebUtil.urlTransform(URL,
                                                myMorph);
        theWebCall.delete(augURL);
    }

    @Override
    public String read(Map<String, String> param)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
