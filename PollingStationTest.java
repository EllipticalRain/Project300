import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Testing suite for PollingStation class
public class PollingStationTest {
    @Test
    public void testRegister_Eligible() {
        //Test registering an eligible voter who meets all criteria.

        Ballot[] ballots = new Ballot[3];
        ballots[0] = new Ballot(1, 101);
        ballots[1] = new Ballot(1, 102);
        ballots[2] = null;

        Voter voter1 = new Voter(1, 1);
        Voter voter2 = new Voter(2, 1);
        Voter voter3 = new Voter(3, 1);

        Voter[] voters = {voter1, voter2, voter3};
        Voter[] hasVoted = {voter1, voter2, null};

        PollingStation ps = new PollingStation(1, 1, hasVoted, ballots);

        int testVoterNum = 3;

        int eligible = ps.register(voters, testVoterNum);

        assertTrue(eligible == 0);
    }

    @Test
    public void testRegister_Ineligible() {
        //Test registering an ineligible voter.

        Ballot[] ballots = new Ballot[3];
        ballots[0] = new Ballot(1, 101);
        ballots[1] = new Ballot(1, 102);
        ballots[2] = null;

        Voter voter1 = new Voter(1, 1);
        Voter voter2 = new Voter(2, 1);
        Voter voter3 = new Voter(3, 1);

        Voter[] voters = {voter1, voter2, voter3};
        Voter[] hasVoted = {voter1, voter2, null};

        PollingStation ps = new PollingStation(1, 1, hasVoted, ballots);

        int testVoterNum = 4;

        int eligible = ps.register(voters, testVoterNum);

        assertTrue(eligible == 1);
    }

    @Test
    public void testRegister_WrongDistrict() {
        //Test registering a voter from another district.

        Ballot[] ballots = new Ballot[3];
        ballots[0] = new Ballot(1, 101);
        ballots[1] = new Ballot(1, 102);
        ballots[2] = null;

        Voter voter1 = new Voter(1, 1);
        Voter voter2 = new Voter(2, 1);
        Voter voter3 = new Voter(3, 1);
        Voter voter4 = new Voter(4, 2);

        Voter[] voters = {voter1, voter2, voter3, voter4};
        Voter[] hasVoted = {voter1, voter2, null, null};

        PollingStation ps = new PollingStation(1, 1, hasVoted, ballots);

        int testVoterNum = 4;

        int eligible = ps.register(voters, testVoterNum);

        assertTrue(eligible == 2);
    }

    @Test
    public void testRegister_AlreadyVoted() {
        //Test registering a voter who already voted.

        Ballot[] ballots = new Ballot[3];
        ballots[0] = new Ballot(1, 101);
        ballots[1] = new Ballot(1, 102);
        ballots[2] = null;

        Voter voter1 = new Voter(1, 1);
        Voter voter2 = new Voter(2, 1);
        Voter voter3 = new Voter(3, 1);

        Voter[] voters = {voter1, voter2, voter3};
        Voter[] hasVoted = {voter1, voter2, null};

        PollingStation ps = new PollingStation(1, 1, hasVoted, ballots);

        int testVoterNum = 2;

        int eligible = ps.register(voters, testVoterNum);

        assertTrue(eligible == 2);
    }

    @Test
    public void testCastBallot() {
        //Test casting a ballot and updating ballot array field of polling station.
        
        Ballot[] ballots = new Ballot[3];
        ballots[0] = new Ballot(1, 101);
        ballots[1] = new Ballot(1, 102);
        ballots[2] = null;

        Voter voter1 = new Voter(1, 1);
        Voter voter2 = new Voter(2, 1);

        Voter[] hasVoted = {voter1, voter2, null};

        PollingStation ps = new PollingStation(1, 1, hasVoted, ballots);

        ps.castBallot(103);

        assertTrue(ps.stationBallots[2].candidateNum == 103);
    }
}