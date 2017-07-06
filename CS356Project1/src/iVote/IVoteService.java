/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this interface describes general methods and IVote service needs
 */
package iVote;

import question.Question;
import question.AnswerSubmission;
import Voter.Voter;
import java.util.List;

public interface IVoteService {
    public Question getQuestion();
    public void poseProblem();
    public void addVoter(Voter v);
    public void addAllVoters(List<Voter> voterList);
    public void deleteAllVoters(List<Voter> voterList);
    public void deleteVoter(Voter v);
    public void clearAllVoters();
    public int getNumberOfVoters();
    public int getNumberOfSubmissions();
    public boolean recieveAnswer(Voter v,AnswerSubmission sub);
    public void outputSubmissions();
    public void setQuestion(Question q);
    public void printCorrectAnswer();
    public void printGradeStatistics();
    public void clearSubmissions();  
}

