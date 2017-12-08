package ClientP.BL;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by clint on 21-02-2017.
 */
public class Client implements Runnable {

    static String name;
    static boolean msgB = false;
    boolean connectionB;
    static String msg;
    String ip = "localhost";
    int port = 2001;
    static Socket socket = null;
    static DataInputStream input;
    static DataOutputStream output;

    public Client() {
    }

    public Client(String name) throws IOException {
        this.name = name;
    }

    public void startConnection() throws IOException {
        socket = new Socket(ip,2001);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        //System.out.println(join());
    }


    public String join() throws IOException {
        output.writeUTF(name);// + name + ", " + ip + ":" + port);
        String serv = input.readUTF();
        System.out.println(serv + "dddd");
        if(serv.equals("J_OK"))
        {
            this.connectionB = true;
        }
        if(serv.equals("J_ERR"))
        {
            this.connectionB = false;
        }

        return serv;
    }

    public void data(String msg) throws IOException {
        output.writeUTF(msg);// + name + ", " + ip + ":" + port);
        /*String serv = input.readUTF();
        return serv;*/
    }

    public void closeSocket() throws IOException {
        socket.close();
    }

    @Override
    public void run() {

            try {
                 msg = recieveMessage();
                while (!msg.equals("QUIT")){
                    msg = recieveMessage();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String recieveMessage() throws IOException {
      //  String msg = null;
        //while (true) {

        try {
            msg = input.readUTF();
            System.out.println(msg);
            msgB = true;
            return msg;


        } catch (IOException e) {
            e.printStackTrace();
        }return msg;
    }


    //////////////////////getters/////////////////////////
    public String getName()
    {
        return this.name;
    }

    public String getMsg()
    {
        return this.msg;
    }

    public Boolean getMsgB()
    {
        return this.msgB;
    }
    public boolean getConnectionB()
    {
        return this.connectionB;
    }

    //////////////////////Setters /////////////////////
    public void setName(String name)
    {
        this.name = name;
    }

    public Socket getSocket(){
        return this.socket;
    }

    public String getServ()
    {
        return "";
    }

    public void setMsgBFalse()
    {
        this.msgB = false;
    }
}

