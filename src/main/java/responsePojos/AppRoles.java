package responsePojos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prasi on 7/2/17.
 */
public class AppRoles
{
    private int appId;
    private String appName;
    private String appApi;
    private String appPath;
    private String appUrl;
    private int roleId;
    private String roleName;

    public AppRoles(
            @JsonProperty("id") Integer appId,
            @JsonProperty("appName") String appName,
            @JsonProperty("appApi") String appApi,
            @JsonProperty("appPath") String appPath,
            @JsonProperty("appUrl") String appUrl,
            @JsonProperty("roleId") int roleId,
            @JsonProperty("roleName") String roleName)
    {
        this.appId = appId;
        this.appName = appName;
        this.appApi = appApi;
        this.appPath = appPath;
        this.appUrl = appUrl;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppApi() {
        return appApi;
    }

    public void setAppApi(String appApi) {
        this.appApi = appApi;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
