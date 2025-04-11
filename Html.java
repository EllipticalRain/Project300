public class Html {
    static String topStyle = 
    """
    <!DOCTYPE html>
    <html>
    <head>
        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
                text-align: center;
            }
        </style>
    </head>
    <body>   
    """;

    static String voterRegister = 
    """
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset='UTF-8'>
            <meta name='viewport' content='width=device-width, initial-scale=1'>
            <style>
                body {
                    font-family: Arial, Helvetica, sans-serif;
                    background-color: black;
                }

                * {
                    box-sizing: border-box;
                }

                .container {
                    padding: 16px;
                    background-color: white;
                }

                input[type=text], input[type=password] {
                    width: 100%;
                    padding: 15px;
                    margin: 5px 0 22px 0;
                    display: inline-block;
                    border: none;
                    background: #f1f1f1;
                }

                input[type=text]:focus, input[type=password]:focus {
                    background-color: #ddd;
                    outline: none;
                }

                hr {
                    border: 1px solid #f1f1f1;
                    margin-bottom: 25px;
                }

                .loginbtn {
                    background-color: #04AA6D;
                    color: white;
                    padding: 16px 20px;
                    margin: 8px 0;
                    border: none;
                    cursor: pointer;
                    width: 100%;
                    opacity: 0.9;
                }

                .loginbtn:hover {
                    opacity: 1;
                }

                a {
                    color: dodgerblue;
                }
            </style>
            <title>Voter Terminal</title>
        </head>
        <body>

        <form action='/voter' method='POST'>
            <div class='container'>
                <h1>Register</h1>
                <p>Please enter your ID number to enter the terminal.</p>
                <hr>

                <label for='voterid'><b>ID Number</b></label>
                <input type='text' placeholder='Enter ID Number' name='voterid' id='voterid' required>

                <button type='submit' class='loginbtn'>Register</button>
            </div>
        </form>

        </body>
    </html>        
    """;

    static String registerError1 = topStyle +
    """
    <h1>Sorry, you are not an eligible voter. You will not be allowed further access to the terminal.</h1>
                
    <p>If you think there is a mistake, please talk to a member of staff or call 555-0179.</p>
                
    </body>
    </html>        
    """;

    static String registerError2 = topStyle +
    """
    <h1>Sorry, you either not an eligible voter in this district or you have already voted. You will not be allowed further access to the terminal.</h1>
                
    <p>If you think there is a mistake, please talk to a member of staff or call 555-0179.</p>
                
    </body>
    </html>        
    """;

    static String getCastBallot(String list) {
        return
        """
        <!DOCTYPE html>
        <html>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1'>
                <style>
                    body {
                        font-family: Arial, Helvetica, sans-serif;
                        background-color: black;
                    }
            
                    * {
                        box-sizing: border-box;
                    }
                    
                    .container {
                        padding: 16px;
                        background-color: white;
                    }
                    
                    input[type=text], input[type=password] {
                        width: 100%;
                        padding: 15px;
                        margin: 5px 0 22px 0;
                        display: inline-block;
                        border: none;
                        background: #f1f1f1;
                    }
                    
                    input[type=text]:focus, input[type=password]:focus {
                        background-color: #ddd;
                        outline: none;
                    }
                    
                    hr {
                        border: 1px solid #f1f1f1;
                        margin-bottom: 25px;
                    }
                    
                    .loginbtn {
                        background-color: #04AA6D;
                        color: white;
                        padding: 16px 20px;
                        margin: 8px 0;
                        border: none;
                        cursor: pointer;
                        width: 100%;
                        opacity: 0.9;
                    }
                    
                    .loginbtn:hover {
                        opacity: 1;
                    }
                    
                    a {
                        color: dodgerblue;
                    }
                </style>
                <title>Voter Terminal</title>
            </head>
            <body>
    
            <form action='/castballot' method='POST'>
                <div class='container'>
                    <h1>Cast Your Vote</h1>
                    <p>Please enter a number that corresponds to the candidate you wish to elect.</p>
                    <hr>
        
                    <p>Candidates:</p>
                    <ol>
        """ +
        list +
        """
                    </ol>
        
                    <hr>
        
                    <label for='cannum'><b>Candidate Number</b></label>
                    <input type='text' placeholder='Enter Candidate Number' name='cannum' id='cannum' required>
        
                    <button type='submit' class='loginbtn'>Cast Ballot</button>
                </div>
            </form>
        
            </body>
        "</html>        
        """;
    }

    static String castSuccess = topStyle +
    """
    <h1>You have successfully voted!</h1>
                
    </body>
    </html>        
    """;

    static String castFail = topStyle +
    """
    <h1>Sorry, you did not enter a valid number. Your ballot will not be counted.</h1>
    
    <p>If you think there is a mistake, please talk to a member of staff or call 555-0179.</p>
    
    </body>
    </html>       
    """;

    static String adminLogin = """   
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <style>
                body {
                    font-family: Arial, Helvetica, sans-serif;
                    background-color: black;
                }
    
                * {
                    box-sizing: border-box;
                }
                
                .container {
                    padding: 16px;
                    background-color: white;
                }
                
                input[type=text], input[type=password] {
                    width: 100%;
                    padding: 15px;
                    margin: 5px 0 22px 0;
                    display: inline-block;
                    border: none;
                    background: #f1f1f1;
                }
                
                input[type=text]:focus, input[type=password]:focus {
                    background-color: #ddd;
                    outline: none;
                }
                
                hr {
                    border: 1px solid #f1f1f1;
                    margin-bottom: 25px;
                }
                
                .loginbtn {
                    background-color: #04AA6D;
                    color: white;
                    padding: 16px 20px;
                    margin: 8px 0;
                    border: none;
                    cursor: pointer;
                    width: 100%;
                    opacity: 0.9;
                }
                
                .loginbtn:hover {
                    opacity: 1;
                }
                
                a {
                    color: dodgerblue;
                }
            </style>
            <title>Admin Terminal</title>
        </head>
        <body>
    
        <form action='/admin' method='POST'>
            <div class="container">
                <h1>Admin Login</h1>
                <p>Please enter credentials to access administrator terminal.</p>
                <hr>
    
                <label for="username"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" id="username" required>
    
                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
    
                <button type="submit" class="loginbtn">Login</button>
            </div>
        </form>
    
        </body>
    </html>
    """;

    static String loginError = topStyle +
    """
    <h1>Username or password is wrong. You will not be allowed further access to the terminal.</h1>
    </body>
    </html>
    """;

    static String getStatsPage(String barCandidates, String barBallots, String barColours, String pieTurnoutStatus, String pieTurnout, String pieColours, String time, String timeTally, String timeColours) {
        return 
        """
        <!DOCTYPE html>
        <html>
            <head>
                <style>
                    body {
                        font-family: Arial, Helvetica, sans-serif;
                        text-align: center;
                    }

                    .chart-container {
                        display: flex;
                        flex-wrap: wrap;
                        justify-content: center;
                        gap: 20px;
                    }

                    canvas {
                        width: 100%;
                        max-width: 600px;
                        height: 400px;
                    }
                    
                    #qr-container {
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                        margin-top: 30px;
                    }

                    .qr-button {
                        background-color: #04AA6D;
                        color: white;
                        padding: 16px 20px;
                        margin: 8px 0;
                        border: none;
                        cursor: pointer;
                        width: 40%;
                        opacity: 0.9;
                    }
                        
                    .qr-button:hover {
                        opacity: 1;
                    }
                </style>
            </head>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js'></script>
        <body>
        
            <h1>Administrator Terminal</h1>
        
            <div class="chart-container">
                <canvas id='ballots'></canvas>
                <canvas id='turnout'></canvas>
                <canvas id='time'></canvas>
            </div>

            <div id="qr-container">
                <button onclick="generateQRCode()" class="qr-button">Generate QR Code</button>
                <br>
                <img id="qr-code" src="" alt="QR Code will appear here" style="display:none; margin-top:10px;"/>
            </div>
        
            <script>
            function generateQRCode() {
                let qrData = "http://localhost:8001?v=" + new Date().getTime(); // VoterMain URL
                let qrUrl = "https://quickchart.io/qr?size=300x300&text=" + encodeURIComponent(qrData);
                
                document.getElementById("qr-code").src = qrUrl;
                document.getElementById("qr-code").style.display = "block";
            }

            var candidates = [""" + 
            barCandidates + """
            ];
            var ballotTally = [""" + 
            barBallots + """
            ];
            var barColours = [""" + 
            barColours + """
            ];
        
            var turnout = [""" + 
            pieTurnoutStatus + """
            ];
            var votedTurnout = [""" + 
            pieTurnout + """
            ];
            var pieColours = [""" + 
            pieColours + """
            ];

            var time = [""" +
            time + """
            ];
            var timeTally = [""" +
            timeTally + """
            ];
            var timeColours = [""" +
            timeColours + """
            ];
        
            new Chart('ballots', {
                type: 'horizontalBar',
                data: {
                labels: candidates,
                datasets: [{
                    backgroundColor: barColours,
                    data: ballotTally
                }]
            },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: 'Ballot Tally'
                    },
                    scales: {
                        xAxes: [{ticks: {min: 0, max:10}}]
                    }
                }
            });
        
            new Chart('turnout', {
                type: 'pie',
                data: {
                labels: turnout,
                datasets: [{
                    backgroundColor: pieColours,
                    data: votedTurnout
                }]
            },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: 'Turnout'
                    }
                }
            });

            new Chart('time', {
                type: 'horizontalBar',
                data: {
                labels: time,
                datasets: [{
                    backgroundColor: timeColours,
                    data: timeTally
                }]
            },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: 'Ballot Tally'
                    },
                    scales: {
                        xAxes: [{ticks: {min: 0, max:5}}]
                    }
                }
            });
            </script>
        
        </body>
        </html>
        """;
    }
}