import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.websocket.WsContext;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;


public class ChatServer {
    private static final Map<WsContext, String> userUsernameMap = new ConcurrentHashMap<>();
    private static final Set<String> existingUsernames = new ConcurrentSkipListSet<>();


    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7070);

        app.get("/", ctx -> {
            ctx.redirect("html/index.html");
        });

        app.ws("/chat", ws -> {
            ws.onConnect(ctx -> {
                System.out.println("Connected!");
            });

            ws.onMessage(ctx -> {
                Map<String, String> messageData = ctx.messageAsClass(Map.class);

                if ("username".equals(messageData.get("type"))) {
                    if(existingUsernames.contains(messageData.get("username"))) {
                        ctx.send(Map.of("type", "error"));
                    } else {
                        userUsernameMap.put(ctx, messageData.get("username"));
                        existingUsernames.add(messageData.get("username"));
                        broadcastMessage("system", messageData.get("username"), "joined the chat");
                    }

                } else if ("message".equals(messageData.get("type"))) {
                    broadcastMessage("message",userUsernameMap.get(ctx), messageData.get("message"));
                }
            });

            ws.onClose(ctx -> {
                String username = userUsernameMap.get(ctx);

                if (username != null) {
                    userUsernameMap.remove(ctx);
                    existingUsernames.remove(username);
                    broadcastMessage("system", username, "left the chat");
                }
            });

        });

        app.get("/chat", ctx -> ctx.redirect("html/index.html"));
    }

    private static void broadcastMessage(String type, String sender, String message) {
        userUsernameMap.keySet().stream().filter(ctx -> ctx.session.isOpen()).forEach(session -> {
            session.send(
                    Map.of(
                            "type", type,
                            "username", sender,
                            "message", message
                    )
            );
        });
    }


}
