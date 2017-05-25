package ohoDAO.entities;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by prabha on 25/5/17.
 */
public class DefectsMapper implements ResultSetMapper<DefectList>{


    @Override
    public DefectList map(int i, ResultSet res, StatementContext statementContext) throws SQLException {
        return new DefectList(res.getInt("defectId"),
                res.getString("sDate"),
                res.getString("sTime"),
                res.getString("uName"),
                res.getString("chassis"),
                res.getString("sProcess"),
                res.getString("oProcess"));
    }
}
