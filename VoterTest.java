import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Testing suite for Voter class
public class VoterTest {

    @Test
    public void testAddVoter_Success() {
        // Test when adding a voter to an array with some null spots.
        
        Voter[] voterArray = new Voter[3];
        voterArray[0] = new Voter(1, 101);
        voterArray[1] = new Voter(2, 102);
        voterArray[2] = null;

        Voter newVoter = new Voter(3, 103);

        Voter[] newVoterArray = newVoter.addVoter(voterArray, newVoter);

        //Assert that the new voter was added to the first available slot
        assertEquals(newVoter, newVoterArray[2]);
    }

    @Test
    public void testAddVoter_NoSpace() {
        //Test when the array is full and no new voter can be added.

        Voter[] voterArray = new Voter[3];
        voterArray[0] = new Voter(1, 101);
        voterArray[1] = new Voter(2, 102);
        voterArray[2] = new Voter(3, 103);

        Voter newVoter = new Voter(4, 104);

        Voter[] newVoterArray = newVoter.addVoter(voterArray, newVoter);

        //Assert that the old and new voterArray are equal
        assertArrayEquals(voterArray, newVoterArray);
    }

    @Test
    public void testAddVoter_Duplicate() {
        //Test when adding a voter to an array with a duplicate element.

        Voter[] voterArray = new Voter[3];
        voterArray[0] = new Voter(1, 101);
        voterArray[1] = new Voter(2, 102);
        voterArray[2] = null;

        Voter newVoter = new Voter(2, 102);

        Voter[] newVoterArray = newVoter.addVoter(voterArray, newVoter);

        //Assert that the old and new voterArray are equal
        assertArrayEquals(voterArray, newVoterArray);
    }
}