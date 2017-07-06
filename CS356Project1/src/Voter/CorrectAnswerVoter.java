/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this voter always submits the right answer
 */
package Voter;

import question.AnswerSubmission;
import question.Question;

public class CorrectAnswerVoter extends AutoVoter{
    
    //constructor
    public CorrectAnswerVoter(String id){
        super(id);
    }
    
    //finds and returns the correct answer to the question
    @Override
    public AnswerSubmission getSubmission(Question question) {
        return question.getCorrectAnswer();
    }

}
