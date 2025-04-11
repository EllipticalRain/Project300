public class PollingStation {
    //@ spec_public
    int stationID;
    //@ spec_public
    int districtID;
    //@ spec_public
    Voter[] hasVoted;
    //@ spec_public
    Ballot[] stationBallots;

    //@ ensures this.stationID == stationID;
    //@ ensures this.districtID == districtID;
    //@ ensures this.hasVoted == hasVoted;
    //@ ensures this.stationBallots == stationBallots;
    //@ pure
    public PollingStation(int stationID, int districtID, Voter[] hasVoted, Ballot[] stationBallots) {
        this.stationID = stationID;
        this.districtID = districtID;
        this.hasVoted = hasVoted;
        this.stationBallots = stationBallots;
    }

    //@ requires voterRoll != null;
    //@ requires voterRoll.length >= 0;
    //@ requires id >= 0;
    //@ ensures \result >= 0 && \result <= 2;
    public int register(Voter[] voterRoll, int id) {
        //Voter[] voterRoll = Voter.voterRoll;

        int eligibility = 1;

        boolean inVoterRoll = checkVoterEligible(voterRoll, id);

        //If voter is in the right district and voter hasn't already voted
        if (inVoterRoll) {
            int voterDistrictID = 0;

            //This is the exact same for loop in checkVoterEligible, the only difference is that it finds the voter's actual district
            //rather than confirming the voter is eligible to vote. Given that this loop only runs if the voter is eligible, it is
            //guaranteed that the if block in this for loop will be executed.
            //@ maintaining 0 <= i <= voterRoll.length;
            //@ maintaining \forall int k; 0 <= k < i; voterRoll[k].id != id;
            //@ loop_writes i;
            //@ decreases voterRoll.length -i;
            for (int i = 0; i < voterRoll.length; i++) {
                //@ assume 0 <= i < voterRoll.length;

                if (id == voterRoll[i].id) {
                    voterDistrictID = voterRoll[i].districtID;
                    break;
                }
            }

            //@ assume 0 <= voterDistrictID < Integer.MAX_VALUE;
            
            boolean eligibility1 = checkVoterDistrict(voterDistrictID);
            boolean eligibility2 = checkVotingStatus(this.hasVoted, id);

            if (eligibility1 && eligibility2) {
                eligibility = 0;

                Voter voter = new Voter(id, voterDistrictID);

                this.hasVoted = voter.addVoter(this.hasVoted, voter);
            }
            else {
                //Voter cannot vote here because they are either in the wrong district or they have already voted
                eligibility = 2;
            }
        }
        else {
            //Voter cannot vote here because they are not a registered voter
            eligibility = 1;
        }

        //@ assert eligibility >= 0 && eligibility <= 2;
        return eligibility;
    }

    //@ requires voters != null;
    //@ requires voters.length >= 0;
    //@ requires id >= 0;
    //@ ensures (\result == true && (\exists int i; 0 <= i < voters.length; voters[i].id == id)) || \result == false;
    private boolean checkVoterEligible(Voter[] voters, int id) {
        boolean eligible = false;

        //This loop checks whether the voter exists in the voter roll
        //@ maintaining 0 <= i <= voters.length;
        //@ maintaining \forall int k; 0 <= k < i; voters[k].id != id;
        //@ loop_writes i;
        //@ decreases voters.length -i;
        for (int i = 0; i < voters.length; i++) {
            //@ assume 0 <= i < voters.length;

            if (id == voters[i].id) {
                eligible = true;
                break;
            }
        }

        return eligible;
    }

    //Checks if the voter is in the right district
    //@ requires voterDistrictID >= 0;
    //@ ensures (\result == true && voterDistrictID == this.districtID) || (\result == false && voterDistrictID != this.districtID);
    private boolean checkVoterDistrict(int voterDistrictID) {
        if (voterDistrictID == this.districtID) {
            return true;
        }
        else {
            return false;
        }
    }

    //@ requires alreadyVoted != null;
    //@ requires alreadyVoted.length >= 0;
    //@ requires id >= 0;
    //@ ensures \result == true || (\result == false && (\exists int i; 0 <= i < alreadyVoted.length; alreadyVoted[i].id == id));
    private boolean checkVotingStatus(Voter[] alreadyVoted, int id) {
        boolean hasVoted = false;

        //This loop checks whether the voter has voted by checking if their name is in this array of voters who have already voted
        //@ maintaining 0 <= i <= alreadyVoted.length;
        //@ maintaining \forall int k; 0 <= k < i; alreadyVoted[k].id != id;
        //@ loop_writes i;
        //@ decreases alreadyVoted.length -i;
        for (int i = 0; i < alreadyVoted.length; i++) {
            //@ assume 0 <= i < alreadyVoted.length;

            if (alreadyVoted[i] != null && id == alreadyVoted[i].id) {
                hasVoted = true;
                break;
            }
        }

        if (hasVoted) {
            //When the voter already voted
            return false;
        }
        else {
            //When the voter hasn't voted
            return true;
        }
    }

    //This assumes that voter is eligible and voter has entered a valid number
    //@ requires candidateNum > 0;
    public void castBallot(int candidateNum) {
        Ballot ballot = new Ballot(this.districtID, candidateNum);
        this.stationBallots = ballot.addBallot(stationBallots, ballot);
    }
}