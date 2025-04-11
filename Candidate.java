//Inheritance is unlikely, won't impress examiners
//May remove this class
public class Candidate {
    String name;
    String party;
    String district;

    public Candidate(String name, String party, String district) {
        this.name = name;
        this.party = party;
        this.district = district;
    }

    static Candidate[] candidates;
}