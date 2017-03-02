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
        socket = new Socket(ip, 2001);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        System.out.println(join());
    }

    public void startConversation(String m) throws IOException {


        //System.out.println(data(m));

    }

/*
    @Override
    public void run()  {
        try {
            socket = new Socket(ip, 2001);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public String join() throws IOException {
        output.writeUTF(name);// + name + ", " + ip + ":" + port);
        String serv = input.readUTF();
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
                // startConnection();
                String msg = recieveMessage();
                while (!msg.equals("QUIT")){
                    msg = recieveMessage();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /*  Socket socket = new Socket("localhost", 2001);
      DataInputStream input = new DataInputStream(socket.getInputStream());
      DataOutputStream output = new DataOutputStream(socket.getOutputStream());
      static String name;
      String serv = null;

      public Client() throws IOException {
         /* new Thread(new Runnable() {
              @Override
              public void run() {
              String msg;
              while((msg = input.readUTF()) != null){
                      try {
                          serv = input.readUTF();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }}

          }).start();*/
   /* }

    public void writeMessageToServer(String message) throws IOException {

        String msg = message;
        output.writeUTF(msg);
        //String servMessage = input.readUTF();
        //System.out.println(servMessage);
       // recieveMessage();
     //   String servMessage = input.readUTF();
       // System.out.println(servMessage)
    }
*/
    public String recieveMessage() throws IOException {
        String msg = null;
        //while (true) {
        try {
            msg = input.readUTF();
            System.out.println(msg);
            return msg;


        } catch (IOException e) {
            e.printStackTrace();
        }return msg;
    }
    // String serv = input.readUTF();
   //System.out.println(serv);

    //////////////////////getters/////////////////////////
    public String getName()
    {
        return this.name;
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
}

