/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this voter guesses the answer to a question completely randomly
 */
package Voter;

import question.AnswerSubmission;
import question.Question;
import java.util.Random;

public class RandomVoter extends AutoVoter{
    
    private Random rand;
    
    //constructor
    public RandomVoter(String id){
        super(id);
        rand = new Random();
    }
    
    //returns a random answer for the question with no special slant twords the
    //correct answer
    @Override
    public AnswerSubmission getSubmission(Question question) {
        return question.getRandomAnswer(0);
    }

}
