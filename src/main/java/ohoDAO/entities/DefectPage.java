package ohoDAO.entities;

/**
 * Created by prabha on 25/5/17.
 */
public class DefectPage {

    private int defectId;
    private String sDate;
    private String sTime;
    private String uName;
    private String chassis;
    private String sProcess;
    private String oProcess;

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
