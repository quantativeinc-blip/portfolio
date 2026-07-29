import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Quantitive Inc - Web Design Agency Server (Aberdeen, Scotland)
 * Built with pure Java HttpServer serving HTML & CSS.
 */
public class QuantitiveServer {
    public static void main(String[] args) throws IOException {
        int port = 3000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Quantitive Inc Java Web Server starting on port " + port);

        // Serve static HTML and CSS files
        server.createContext("/", new StaticFileHandler());
        
        // Serve WhatsApp API metadata endpoint
        server.createContext("/api/whatsapp", new WhatsAppApiHandler());
        
        server.setExecutor(null);
        server.start();
        System.out.println("Quantitive Inc Java Server running at http://localhost:" + port);
    }

    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/")) {
                path = "/index.html";
            }

            String filePath = "." + path;
            if (Files.exists(Paths.get(filePath))) {
                byte[] response = Files.readAllBytes(Paths.get(filePath));
                String contentType = "text/html";
                if (path.endsWith(".css")) {
                    contentType = "text/css";
                } else if (path.endsWith(".js")) {
                    contentType = "application/javascript";
                } else if (path.endsWith(".svg")) {
                    contentType = "image/svg+xml";
                } else if (path.endsWith(".png")) {
                    contentType = "image/png";
                }
                
                exchange.getResponseHeaders().set("Content-Type", contentType);
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
            } else {
                String notFound = "<h1>404 - Page Not Found</h1><p>Quantitive Inc - Local Aberdeen Web Design</p>";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(404, notFound.length());
                OutputStream os = exchange.getResponseBody();
                os.write(notFound.getBytes());
                os.close();
            }
        }
    }

    static class WhatsAppApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String jsonResponse = "{"
                + "\"company\": \"Quantitive Inc\","
                + "\"location\": \"Aberdeen, Scotland, UK\","
                + "\"whatsappNumber\": \"+447880019548\","
                + "\"status\": \"online\""
                + "}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, jsonResponse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();
        }
    }
}
