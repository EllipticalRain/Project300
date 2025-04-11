import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.net.InetSocketAddress;

//This is where the code will be tested from the voter's view
public class VoterMain {
    public static void main(String[] args) throws Exception {
        String[] candidates = Data.candidates;
        Voter[] voters = Voter.voterRoll;

        Voter[] alreadyVoted = Data.alreadyVoted;
        Ballot[] ballots = Data.ballots;

        PollingStation westwywern = new PollingStation(128, 26, alreadyVoted, ballots);

        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        
        //HTML webpage is created with form
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = Html.voterRegister;
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        //Form input is handled here
        server.createContext("/voter", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                //Form data for register is extracted
                InputStream is = exchange.getRequestBody();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringWriter writer = new StringWriter();
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }
                String formData = writer.toString();
                
                //The voter's ID is parsed
                int id;
                try {
                    id = Integer.parseInt(parseFormData(formData, "voterid"));
                }
                catch (NumberFormatException e) {
                    id = 0;
                }

                int canVote = westwywern.register(voters, id);

                if (canVote == 0) {
                    //Executes when voter is eligible, is in the right district and hasn't voted
                    String list = "";
                    for (int i = 0; i < candidates.length; i++) {
                        list = list + "                <li>" + candidates[i] + "</li>";
                    }

                    //This page allows the voter to cast their ballot
                    String response = Html.getCastBallot(list);

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                    server.createContext("/castballot", new HttpHandler() {
                        @Override
                        public void handle(HttpExchange exchange) throws IOException {
                            //Form data for cast ballot is extracted
                            InputStream is = exchange.getRequestBody();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                            StringWriter writer = new StringWriter();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                writer.write(line);
                            }
                            String formData = writer.toString();

                            //Candidate number is parsed
                            int candidateNum;
                            try {
                                candidateNum = Integer.parseInt(parseFormData(formData, "cannum"));
                            }
                            catch (NumberFormatException e) {
                                candidateNum = 0;
                            }

                            //Only accepts valid candidate numbers
                            if (candidateNum >= 1 && candidateNum <= candidates.length) {
                                westwywern.castBallot(candidateNum);

                                //Success page where the voter successfully casts their ballot
                                String response = Html.castSuccess;

                                exchange.sendResponseHeaders(200, response.getBytes().length);
                                OutputStream os = exchange.getResponseBody();
                                os.write(response.getBytes());
                                os.close();
                            }
                            else {
                                //Error page where the voter spoiled their ballot
                                String response = Html.castFail;

                                exchange.sendResponseHeaders(200, response.getBytes().length);
                                OutputStream os = exchange.getResponseBody();
                                os.write(response.getBytes());
                                os.close();
                            }
                        }
                    });
                }
                else if (canVote == 1) {
                    //Error page when the voter is not eligible to vote
                    String response = Html.registerError1;

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
                else if (canVote == 2) {
                    //Error page when the voter is not from this district, or the voter has already voted
                    String response = Html.registerError2;

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            }
        });

        //This starts the server where the HTML page is running
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on http://localhost:8001");
    }

    //Method to parse form data from POST body
    private static String parseFormData(String formData, String key) {
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue[0].equals(key)) {
                return keyValue[1];
            }
        }
        return "";
    }
}