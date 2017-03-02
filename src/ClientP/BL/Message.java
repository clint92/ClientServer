package ClientP.BL;

/**
 * Created by clint on 28-02-2017.
 */
public class Message {
    String userName;
    String msg;
    String type;

    public Message()
    {
    }
    public Message(String type, String userName, String msg)
    {
        this.userName = userName;
        this.msg = msg;
        this.type = type;
    }

    public String getUserName()
    {
        return this.userName;
    }
    public String getMsg()
    {
        return this.msg;
    }
    public String getType()
    {
        return this.type;
    }

    public String toString()
    {
        return this.type + ", " + this.userName + "";
    }
}
