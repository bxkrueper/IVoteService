/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this interface states what methods a voter should have
 */
package Voter;

import question.AnswerSubmission;
import iVote.IVoteService;
import question.Question;

public interface Voter {
    public String getID();
    public boolean vote(IVoteService ivs);
    public AnswerSubmission getSubmission(Question question);
    public boolean equals(Voter v2);
    public void notify(String str);
}
