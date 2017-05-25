package responsePojos;

/**
 * Created by prasi on 7/2/17.
 */
public class ApiResponseData
{
    private boolean isSuccess;
    private String message;
    private int id;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
