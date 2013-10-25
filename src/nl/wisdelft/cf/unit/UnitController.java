package nl.wisdelft.cf.unit;

import nl.wisdelft.cf.datamodel.*;

public interface UnitController {

    public Unit getUnit(String aJobId, String aUnitId);

    public Unit create(Unit aUnit);

    public void update(Unit aUnit);

    public void delete(String aJobId, String aUnitId);

    public void addGold(
            Unit aUnit,
            String legend,
            String value,
            String reason);

    public void removeGold(String aJobId, String aUnitId);

}
