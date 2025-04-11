public class Ballot {
    //@ spec_public
    int districtID;
    //@ spec_public
    int candidateNum;

    //@ ensures this.districtID == districtID;
    //@ ensures this.candidateNum == candidateNum;
    //@ pure
    public Ballot(int districtID, int candidateNum) {
        this.districtID = districtID;
        this.candidateNum = candidateNum;
    }

    /*@
    @ requires ballotArray != null;
    @ requires ballotArray.length >= 0;
    @ ensures (\old(ballotArray) != ballotArray && (\exists int i; 0 <= i < ballotArray.length; ballotArray[i] == ballot)) || \old(ballotArray) == ballotArray;
    @*/
    public Ballot[] addBallot(Ballot[] ballotArray, Ballot ballot) {

        //@ assume 0 <= ballotArray.length < Integer.MAX_VALUE;

        //@ maintaining 0 <= i <= ballotArray.length;
        //@ maintaining \forall int k; 0 <= k < i; ballotArray[k] != null;
        //@ loop_writes i, ballotArray[*];
        //@ decreases ballotArray.length -i;
        for (int i = 0; i < ballotArray.length; i++) {

            //@ assume 0 <= i < ballotArray.length;

            if (ballotArray[i] == null) {
                ballotArray[i] = ballot;
                break;
            }
        }

        //Testing only, prints out the entire ballot array
        /*
        for (int i = 0; i < ballotArray.length; i++) {
            if (ballotArray[i] != null) {
                System.out.println(ballotArray[i].candidateNum);
                System.out.println(ballotArray[i].districtID);
            }
            else {
                System.out.println("NULL");
            }
        }
        */

        return ballotArray;
    }
}