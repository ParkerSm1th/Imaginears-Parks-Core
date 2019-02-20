package club.imaginears.core.utils;

import io.socket.client.IO;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

public class Socket {

    public static void setup() {
        io.socket.client.Socket socket;
        try {
            socket = IO.socket("http://imaginears.club:5555");
            socket.connect();
            Console.Log("Connected to imaginears socket server!", Console.types.LOG);
            String joinmsg = "WDW";
            socket.emit("clientjoin", joinmsg);
            socket.on("kick", new Emitter.Listener()
            {
                public void call(Object... args)
                {

                    Console.Log(args.toString(), Console.types.LOG);
                }
            })
                    .on("disconnect", new Emitter.Listener()
                    {
                        public void call(Object... args) {}
                    });
        }
        catch (URISyntaxException e){
            e.printStackTrace();
        }
    }

}
