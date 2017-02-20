package BL;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by clint on 20-02-2017.
 */
public class Server implements Runnable{

    private ServerSocket serverSocket;
    private final int port = 2001;

    @Override
    public void run()
    {
            System.out.println("Opening port…\n");
            try
            {
                serverSocket = new ServerSocket(port); //Step 1.
            }
            catch(IOException ioEx)
            {
                System.out.println(
                        "Unable to attach to port!");
                System.exit(1);
            }
            do
            {
                //handleClient();
            }while (true);
        }

        public void VarifyChatName()
        {

        }
}
