/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this voter can be told exactly how to vote by passing an
 * Answer Submission
 */
package Voter;

import question.AnswerSubmission;
import iVote.IVoteService;
import question.Question;

public class ManualVoter implements Voter{
    
    private String id;
    private AnswerSubmission submission;
    
    //constructor
    public ManualVoter(String id){
        this.id=id;
    }

    //gets the id
    @Override
    public String getID() {
        return id;
    }
    
    /**
     * @return the submission
     */
    public AnswerSubmission getCurrentSubmission() {
        return submission;
    }

    /**
     * @param sub the new submission
     */
    public void setSubmission(AnswerSubmission sub) {
        this.submission = sub;
    }

    //sends the last set submission to the ivote service
    @Override
    public boolean vote(IVoteService ivs) {
        return ivs.recieveAnswer(this, getCurrentSubmission());
    }
    
    //sets the new submission first, then sends it to the ivote service
    public boolean vote(IVoteService ivs,AnswerSubmission as) {
        setSubmission(as);
        return ivs.recieveAnswer(this, getCurrentSubmission());
    }

    //equals method for comparing
    @Override
    public boolean equals(Voter v2) {
        return this.id.equals(v2.getID());
    }

    //returns the last set submission, no matter what the question is
    @Override
    public AnswerSubmission getSubmission(Question question) {
        return getCurrentSubmission();
    }

    //tells the voter something
    @Override
    public void notify(String str) {
        System.out.println(getID() + ": " + str);
    }

    

}
