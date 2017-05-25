package ohoDAO;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.dropwizard.hibernate.UnitOfWork;
import ohoDAO.entities.DEFECTSJDAO;
import ohoDAO.entities.DefectList;
import ohoDAO.entities.DefectPage;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by prabha on 25/5/17.
 */
@Path("/defects")
@Api(value = "/defects", description = "create defects using params")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Defects {
    DEFECTSJDAO defectsjdao;
    public Defects(DEFECTSJDAO defectsjdao) {
         this.defectsjdao =defectsjdao;
    }

    @POST
    @ApiOperation(value="Return Roles", notes="no session token.")
    @ApiResponses(value={
            @ApiResponse(code=400, message="Invalid ID"),
    })
    public String createDefects(DefectPage defectInput)
    {
        try{
            int d  = defectsjdao.insert(defectInput.getsDate(),defectInput.getsTime(),defectInput.getuName(),defectInput.getChassis(),defectInput.getsProcess(),defectInput.getoProcess());
            System.out.print("defectLists"+d);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @GET
    @UnitOfWork
    @ApiOperation(value="Return Roles", notes="Need session token.")
    @ApiResponses(value={
            @ApiResponse(code=400, message="Invalid ID"),
    })
    public List<DefectList> getDefectsList()
    {
        try
        {
            List<DefectList> defectLists = defectsjdao.findByDefectsList();
            return defectLists;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    @PUT
    @ApiOperation(value="Return Roles", notes="no session token.")
    @ApiResponses(value={
            @ApiResponse(code=400, message="Invalid ID"),
    })
    public List<DefectList> updateDefects(DefectPage defectInput)
    {
        try{
            int defectId = defectInput.getDefectId();
            String sDate= defectInput.getsDate();
            String sTime= defectInput.getsTime();
            String uName= defectInput.getuName();
            String chassis= defectInput.getChassis();
            String sProcess= defectInput.getsProcess();
            String oProcess= defectInput.getoProcess();
            int d  = defectsjdao.updateDefects(defectId,sDate,sTime,uName,chassis,sProcess,oProcess);
            List<DefectList> defectLists = defectsjdao.findByDefectsList(defectId);
            System.out.print("defectLists"+d);
            return  defectLists;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
