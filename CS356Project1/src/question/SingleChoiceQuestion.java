/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class describes a choice question where only one answer can be selected at a time
 */
package question;

import java.util.List;
import java.util.Random;

public class SingleChoiceQuestion extends ChoiceQuestion{

    private final int correctAnswerID;
    
    //constructor
    public SingleChoiceQuestion(String questionText,List<ChoiceAnswerDescription> possibleAnswerList,char correctAnswerLetter){
        super(questionText,possibleAnswerList);
        this.correctAnswerID = AnswerTag.charToAnswerId(correctAnswerLetter);
    }
    
    //gets the id of the correct answer (0 means the first one)
    public int getCorrectAnswerId(){
        return correctAnswerID;
    }

    //returns whether the answer is a valid submission for a single choice question
    //only one answer must be selected
    @Override
    public boolean verifyAnswer(ChoiceAnswerSubmission sub) {
        return sub.isValid() && sub.getNumberOfSelectedAnswers()==1;
    }

    //returns the correct answer letter (ex: "B")
    @Override
    public String getCorrectAnswerString() {
        return Character.toString(AnswerTag.numberToChar(correctAnswerID));
    }

    //returns the correct answer  in ChoiceAnswerSubmission form
    @Override
    public ChoiceAnswerSubmission getCorrectChoiceAnswer() {
        AnswerTag[] tagArray = new AnswerTag[]{new AnswerTag(correctAnswerID)};//this array contains the single correct answer
        return new ChoiceAnswerSubmission(tagArray,getNumberOfPossibleAnswers());
    }

    //returns 0 if the wrong answer is selected and 1 if the right answer is selected
    //if the answer is formatted wrong, the grade is 0.
    @Override
    public float grade(ChoiceAnswerSubmission sub) {
        if(!verifyAnswer(sub))
            return 0f;
        
        //sub has only one answer
        if(sub.get(correctAnswerID))
            return 1f;
        else
            return 0f;
    }

    //returns an AnswerSubission with a slanted chance of having the correct answer be choosen
    //0 confidence makes this completely random (1/n chance) and 1 returns the correct answer
    @Override
    public AnswerSubmission getRandomAnswer(float confidence) {
        float randomCorrectnessChance = 1/(float)this.getNumberOfPossibleAnswers();
        float correctnessChance = (1-randomCorrectnessChance)*confidence+randomCorrectnessChance;
        if(correctnessChance>Math.random())
            return getCorrectChoiceAnswer();
        else{
            //return a random wrong answer
            Random rand = new Random();
            int wrongAnswerID = rand.nextInt(getNumberOfPossibleAnswers()-1);
            if(wrongAnswerID>=correctAnswerID)
                wrongAnswerID++;
            AnswerTag[] tagArray = new AnswerTag[]{new AnswerTag(wrongAnswerID)};//this array contains a single wrong answer
            return new ChoiceAnswerSubmission(tagArray,getNumberOfPossibleAnswers());
        }
    }
    
}
