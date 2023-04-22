import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class WordStuff implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String answer= "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Angel's list: %s \n", answer);
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    answer = answer +"\n"+parameters[1];
                    return String.format("Word added was %s", parameters[1]);
                }
            }
            return "404 Not Found!";
        }
    }
}

class WordServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new WordStuff());
    }
}
