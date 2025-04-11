public class Voter {
    //@ spec_public
    int id;
    //@ spec_public
    int districtID;

    //@ ensures this.id == id;
    //@ ensures this.districtID == districtID;
    //@ pure
    public Voter(int id, int districtID) {
        this.id = id;
        this.districtID = districtID;
    }

    //12 voters so far
    //This assumes all voters hav been assigned the correct IDs and there are no duplicates IDs
    static Voter[] voterRoll = {
        new Voter(1341, 26),
        new Voter(693, 26),
        new Voter(5432, 26), //Not voted
        new Voter(1945, 26),
        new Voter(4982, 26),
        new Voter(4738, 26),
        new Voter(4551, 26), //Not voted
        new Voter(4465, 26),
        new Voter(7186, 26),
        new Voter(9499, 26), //Not voted
        new Voter(2139, 57), //Wrong district
        new Voter(715, 57) //Wrong district
    };

    /*@
    @ requires voterArray != null;
    @ requires voterArray.length >= 0;
    @ ensures (\old(voterArray) != voterArray && (\exists int i; 0 <= i < voterArray.length; voterArray[i] == voter)) || \old(voterArray) == voterArray;
    @*/
    public Voter[] addVoter(Voter[] voterArray, Voter voter) {
        //@ assume 0 <= voterArray.length < Integer.MAX_VALUE;

        //@ maintaining 0 <= i <= voterArray.length;
        //@ maintaining \forall int k; 0 <= k < i; voterArray[k] != null;
        //@ loop_writes i, voterArray[*];
        //@ decreases voterArray.length -i;
        for (int i = 0; i < voterArray.length; i++) {

            //@ assume 0 <= i < voterArray.length;

            if (voterArray[i] == null) {
                voterArray[i] = voter;
                break;
            }
            else if (voterArray[i] == voter) {
                break;
            }
        }

        //Testing only, prints out the entire voter array
        /*
        for (int i = 0; i < voterArray.length; i++) {
            if (voterArray[i] != null) {
                System.out.println(voterArray[i].id);
            }
            else {
                System.out.println("NULL");
            }
        }
        */

        return voterArray;
    }
}