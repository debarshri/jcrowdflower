package nl.wisdelft.cf.judgment;

import nl.wisdelft.cf.datamodel.*;

import java.util.*;

public interface JudgmentController {

    public void create(Judgment aJudgment);

    public Judgment getJudgment(String aJobId,String aJudgmentId);

    /**
     * [ web call ] Possible parameters - limit and page
     *
     * @param param
     * @return
     */
    public String read(Map<String, String> param);

    public void update(Judgment aJudgment);

    public void delete(String aJobId, String aJudgmentId);

}
