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

//This is where the code will be tested from a polling worker's view
public class AdminMain {

    public static void main(String[] args) throws Exception {
        String[] candidates = Data.candidates;

        Voter[] alreadyVoted = Data.alreadyVoted;
        Ballot[] ballots = Data.ballots;

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        //HTML webpage is created with form
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = Html.adminLogin;
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        //Form input is handled here
        server.createContext("/admin", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                //Form data is extracted
                InputStream is = exchange.getRequestBody();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringWriter writer = new StringWriter();
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }
                String formData = writer.toString();
                
                //Username and password are parsed
                String username = parseFormData(formData, "username");
                String password = parseFormData(formData, "psw");

                boolean isPasswordCorrect = false;
                try {
                    isPasswordCorrect = Data.checkPassword(password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Only executes if credentials are correct
                if (username.equals(Data.username) && isPasswordCorrect) {
                    String barCandidates = "";
                    String barBallots = "";
                    String barColours = Data.barColours;

                    String pieTurnoutStatus = "'Voted', 'Not Voted'";
                    String pieTurnout = "";
                    String pieColours = Data.pieColours;

                    String time = "'9 to 10 am', '10 to 11 am', '11 am to 12 pm', '12 to 1 pm', '1 to 2 pm', '2 to 3 pm', '3 to 4 pm', '4 to 5 pm', '5 to 6 pm'";
                    String timeTally = "";
                    String timeColours = Data.timeColours;

                    for (int i = 0; i < candidates.length; i++) {
                        if (i > 0) {
                            barCandidates += ", ";
                        }

                        barCandidates = barCandidates + "'" + candidates[i] + "'";
                    }

                    int[] voteCounts = new int[candidates.length + 1]; // Array indexed by candidate number, index 0 remains 0
                    for (Ballot ballot : ballots) {
                        if (ballot != null) {
                            voteCounts[ballot.candidateNum]++;
                        }
                        else {
                            break;
                        }
                    }

                    for (int i = 1; i < voteCounts.length; i++) {
                        if (i > 1) {
                            barBallots += ", ";
                        }

                        barBallots = barBallots + voteCounts[i];
                    }

                    int voted = 0;
                    int notVoted = 0;
                    for (int i = 0; i < alreadyVoted.length; i++) {
                        if (alreadyVoted[i] == null) {
                            notVoted++;
                        }
                    }
                    voted = alreadyVoted.length - notVoted;
                    pieTurnout = voted + ", " + notVoted;

                    int[] timeCounts = new int[9];
                    for (int milTime : Data.timestamps) {
                        if (milTime >= 900 && milTime <= 1000) {
                            timeCounts[0]++;
                        }
                        else if (milTime > 1000 && milTime <= 1100) {
                            timeCounts[1]++;
                        }
                        else if (milTime > 1100 && milTime <= 1200) {
                            timeCounts[2]++;
                        }
                        else if (milTime > 1200 && milTime <= 1300) {
                            timeCounts[3]++;
                        }
                        else if (milTime > 1300 && milTime <= 1400) {
                            timeCounts[4]++;
                        }
                        else if (milTime > 1400 && milTime <= 1500) {
                            timeCounts[5]++;
                        }
                        else if (milTime > 1500 && milTime <= 1600) {
                            timeCounts[6]++;
                        }
                        else if (milTime > 1600 && milTime <= 1700) {
                            timeCounts[7]++;
                        }
                        else if (milTime > 1700 && milTime <= 1800) {
                            timeCounts[8]++;
                        }
                        else if (milTime == 0) {
                            break;
                        }
                    }

                    for (int i = 0; i < timeCounts.length; i++) {
                        if (i > 0) {
                            timeTally += ", ";
                        }

                        timeTally = timeTally + timeCounts[i];
                    }

                    //Statistics page only shows after login is successful
                    String response = Html.getStatsPage(barCandidates, barBallots, barColours, pieTurnoutStatus, pieTurnout, pieColours, time, timeTally, timeColours);

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
                else {
                    //Error page when login credentials are incorrect
                    String response = Html.loginError;

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
        System.out.println("Server started on http://localhost:8000");
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