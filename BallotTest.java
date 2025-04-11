import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Testing suite for Ballot class
public class BallotTest {

    @Test
    public void testAddBallot_Success() {
        //Test when adding a ballot to an array with some null spots.
        
        Ballot[] ballotArray = new Ballot[3];
        ballotArray[0] = new Ballot(1, 101);
        ballotArray[1] = null;
        ballotArray[2] = new Ballot(2, 102);

        Ballot newBallot = new Ballot(3, 103);

        Ballot[] newBallotArray = newBallot.addBallot(ballotArray, newBallot);

        //Assert that the new ballot was added to the first available slot
        assertEquals(newBallot, newBallotArray[1]);
    }

    @Test
    public void testAddBallot_NoSpace() {
        //Test when the array is full and no new ballot can be added.

        Ballot[] ballotArray = new Ballot[3];
        ballotArray[0] = new Ballot(1, 101);
        ballotArray[1] = new Ballot(2, 102);
        ballotArray[2] = new Ballot(3, 103);

        Ballot newBallot = new Ballot(4, 104);

        Ballot[] newBallotArray = newBallot.addBallot(ballotArray, newBallot);
        
        //Assert that the old and new ballotArray are equal
        assertArrayEquals(ballotArray, newBallotArray);
    }
}

