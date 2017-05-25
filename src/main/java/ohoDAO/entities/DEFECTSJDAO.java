package ohoDAO.entities;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by prabha on 25/5/17.
 */
@RegisterMapper(DefectsMapper.class)

public interface DEFECTSJDAO {

    @SqlQuery("select * from oho.defects")
    List<DefectList> findByDefectsList();

    @SqlUpdate("update oho.defects set sDate = :sDate, sTime = :sTime, uName = :uName, chassis = :chassis, sProcess = :sProcess, oProcess = :oProcess where defectId = :defectId")
    int updateDefects(@Bind("defectId") Integer defectId, @Bind("sDate") String sDate,@Bind("sTime") String sTime,
                       @Bind("uName") String uName,@Bind("chassis") String chassis,@Bind("sProcess") String sProcess
            ,@Bind("oProcess") String oProcess);

    @SqlUpdate("insert into oho.defects (sDate, sTime, uName, chassis, sProcess, oProcess) " +
            "values (:sDate, :sTime, :uName, :chassis, :sProcess, :oProcess)")
    int insert(@Bind("sDate") String sDate, @Bind("sTime") String sTime,@Bind("uName") String uName,
               @Bind("chassis") String chassis,@Bind("sProcess") String sProcess,@Bind("oProcess") String oProcess);

    @SqlQuery("select * from oho.defects where defectId = :defectId")
    List<DefectList> findByDefectsList(@Bind("defectId") int sDate);


}
