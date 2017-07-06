/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class is more simple than the student voter.
 * Its subclasses can be used for testing
 */
package Voter;

import iVote.IVoteService;
import question.Question;

public abstract class AutoVoter implements Voter{

    private final String id;
    
    //constructor
    public AutoVoter(String id){
        this.id = id;
    }
    
    //gets the id
    @Override
    public String getID() {
        return id;
    }

    @Override
    public boolean equals(Voter v2) {
        return this.id.equals(v2.getID());
    }
    
    //tells the voter to get the question from the service and submit their answer
    @Override
    public boolean vote(IVoteService ivs) {
        Question q = ivs.getQuestion();
        if(q==null)
            return false;
        return ivs.recieveAnswer(this, getSubmission(q));
    }
    
    //this lets the IVoteService give the voter feedback on what happened to their submission
    //the string can say things like: "submitted sucsessfully", "changed submission sucsessfully"
    //and "error! wrong submission format"
    //as this project is doesn't actually work with real people, this just prints out the message
    @Override
    public void notify(String str) {
        System.out.println(getID() + ": " + str);
    }
    
}
