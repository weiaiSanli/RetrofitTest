package bean;

/**
 * Created by shi on 2017/2/23.
 */
public class UpdateNetBean {


    /**
     * message : 登陆成功
     * data :
     * infoCode : 200
     * success : 1
     */

    private String message;
    private String data;
    private int infoCode;
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(int infoCode) {
        this.infoCode = infoCode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
