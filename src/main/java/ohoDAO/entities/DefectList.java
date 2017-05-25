package ohoDAO.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by prabha on 25/5/17.
 */
public class DefectList {

    @NotNull
    @JsonProperty
    private Integer defectId;
    @JsonProperty String sDate;
    @JsonProperty String sTime;
    @JsonProperty String uName;
    @JsonProperty String chassis;
    @JsonProperty String sProcess;
    @JsonProperty String oProcess;

    public DefectList(int defectId,String sDate,String sTime,String uName,String chassis,String sProcess,String oProcess){
        this.defectId = defectId;
        this.sDate = sDate;
        this.sTime = sTime;
        this.uName = uName;
        this.chassis = chassis;
        this.sProcess = sProcess;
        this.oProcess = oProcess;
    }
    public int getDefectId() {
        return defectId;
    }
    public void setDefectId(int defectId) {
        this.defectId = defectId;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getsProcess() {
        return sProcess;
    }

    public void setsProcess(String sProcess) {
        this.sProcess = sProcess;
    }

    public String getoProcess() {
        return oProcess;
    }

    public void setoProcess(String oProcess) {
        this.oProcess = oProcess;
    }

}
