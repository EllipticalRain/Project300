import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class Data {
    static String[] candidates = {"Ferdinand Powell", "Michael Ford", "Carina Collins"};

    static Voter[] voters = Voter.voterRoll;
    
    static Voter[] alreadyVoted = {voters[0], voters[1], voters[3], voters[4], voters[5], voters[7], voters[8], null, null, null};

    static Ballot[] ballots = {
        new Ballot(26, 1),
        new Ballot(26, 2),
        new Ballot(26, 2),
        new Ballot(26, 1),
        new Ballot(26, 3),
        new Ballot(26, 1),
        new Ballot(26, 2),
        null,
        null,
        null
    };

    //Voting time is from 9 am to 6 pm, uses military time
    static int[] timestamps = {902, 1018, 1221, 1223, 1451, 1534, 1752, 0, 0, 0};

    static String username = "admin123";
    static boolean checkPassword(String password) throws Exception {
        int iterations = 10000;
        int keyLength = 512;

        byte[] salt = Base64.getDecoder().decode("3KHTAqVJrPOp0Jgac7zWSA==");
        String storedPassword = "uQDICOomJcd2bx4G6wYQX72pwjAlRIi6B/dj+ZVD1bvbg/Cf7o4LRH1rdYTXCnkF1ZSR8YCIZTVpEbQLUHaL1Q==";
        
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        
        byte[] hash = skf.generateSecret(spec).getEncoded();
        String stringHash = Base64.getEncoder().encodeToString(hash);

        if (stringHash.equals(storedPassword)) {
            return true;
        }
        else {
            return false;
        }
    }

    static String barColours = "'red', 'blue', 'green'";
    static String pieColours = "'green', 'red'";
    static String timeColours = "'red', 'brown', 'orange', 'yellow', 'green', 'cyan', 'blue', 'purple', 'indigo'";
}