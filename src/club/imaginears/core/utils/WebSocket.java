package club.imaginears.core.utils;

import club.imaginears.core.Core;
import com.neovisionaries.ws.client.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WebSocket {
    public static com.neovisionaries.ws.client.WebSocket websocket;
    public static boolean supposedToBeOn = false;
    public static void setupSocket() {
        supposedToBeOn = true;
        try {
            websocket = new WebSocketFactory()
                    .createSocket("wss://imaginears.club:4444")
                    .addListener(new WebSocketListener() {
                        @Override
                        public void onStateChanged(com.neovisionaries.ws.client.WebSocket websocket, WebSocketState newState) throws Exception {

                        }

                        @Override
                        public void onConnected(com.neovisionaries.ws.client.WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                            Console.Log("Connected to imaginears websocket", Console.types.LOG);
                        }

                        @Override
                        public void onConnectError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause) throws Exception {
                            Console.Log("Error connecting to imaginears websocket", Console.types.ERROR);
                        }

                        @Override
                        public void onDisconnected(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
                            if (supposedToBeOn) {
                                Console.Log("Disconnected from imaginears websocket trying to reconnect", Console.types.LOG);
                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {

                                    public void run() {
                                        setupSocket();

                                    }
                                }, 5L);
                            } else {
                                Console.Log("Disconnected from imaginears websocket", Console.types.LOG);
                            }
                        }

                        @Override
                        public void onFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onContinuationFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onTextFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onBinaryFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onCloseFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onPingFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onPongFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onTextMessage(com.neovisionaries.ws.client.WebSocket websocket, String text) throws Exception {
                            System.out.println("Received msg!!: " + text);
                            try {
                                JSONParser parser = new JSONParser();
                                JSONObject json = (JSONObject) parser.parse(text);
                                Console.Log(json.get("type").toString(), Console.types.LOG);

                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onTextMessage(com.neovisionaries.ws.client.WebSocket websocket, byte[] data) throws Exception {

                        }

                        @Override
                        public void onBinaryMessage(com.neovisionaries.ws.client.WebSocket websocket, byte[] binary) throws Exception {

                        }

                        @Override
                        public void onSendingFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onFrameSent(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onFrameUnsent(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onThreadCreated(com.neovisionaries.ws.client.WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

                        }

                        @Override
                        public void onThreadStarted(com.neovisionaries.ws.client.WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

                        }

                        @Override
                        public void onThreadStopping(com.neovisionaries.ws.client.WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

                        }

                        @Override
                        public void onError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause) throws Exception {

                        }

                        @Override
                        public void onFrameError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onMessageError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, List<WebSocketFrame> frames) throws Exception {

                        }

                        @Override
                        public void onMessageDecompressionError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, byte[] compressed) throws Exception {

                        }

                        @Override
                        public void onTextMessageError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {

                        }

                        @Override
                        public void onSendError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onUnexpectedError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause) throws Exception {

                        }

                        @Override
                        public void handleCallbackError(com.neovisionaries.ws.client.WebSocket websocket, Throwable cause) throws Exception {

                        }

                        @Override
                        public void onSendingHandshake(com.neovisionaries.ws.client.WebSocket websocket, String requestLine, List<String[]> headers) throws Exception {

                        }
                    })
                    .connect();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WebSocketException ex) {
            ex.printStackTrace();
        }
    }

    public static void disconnectSocket() {
        supposedToBeOn = false;
        websocket.disconnect();
    }
}
