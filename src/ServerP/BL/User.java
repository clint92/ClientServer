package ServerP.BL;

import java.net.Socket;

/**
 * Created by clint on 20-02-2017.
 */
public class User {
    String name;
    String IP;
    int port;
    Socket socket;
    public User(Socket socket, String name)
    {
        this.name = name;
        this.socket = socket;
    }

    public User(String name, String IP, int port)
    {
        this.name = name;
        this.IP = IP;
        this.port = port;
    }
    public String getName()
    {
        return this.name;
    }
    public Socket getSocket(){return socket;}
}
