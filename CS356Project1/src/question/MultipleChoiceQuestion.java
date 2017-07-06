/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class describes a choice question where multiple answers can be selected
 */
package question;

import java.util.List;

public class MultipleChoiceQuestion extends ChoiceQuestion{

    private final ChoiceAnswerSubmission correctAnswer;
    
    //constructor
    public MultipleChoiceQuestion(String questionText,List<ChoiceAnswerDescription> possibleAnswerList,char ... correctAnswerLetters){
        super(questionText,possibleAnswerList);
        correctAnswer = new ChoiceAnswerSubmission(correctAnswerLetters,this.getNumberOfPossibleAnswers());
    }

    //Multiple choice question has no extra checks. any valid ChoiceAnswerSubmission works
    @Override
    public boolean verifyAnswer(ChoiceAnswerSubmission sub) {
        return sub.isValid();
    }

    //returns the correct answer letters in string form
    @Override
    public String getCorrectAnswerString() {
        return correctAnswer.getSelectedAnswerLetters().toString();
    }

    //returns a copy of the correct answer
    @Override
    public ChoiceAnswerSubmission getCorrectChoiceAnswer() {
        return new ChoiceAnswerSubmission(correctAnswer);
    }

    //grades the score (0-1) based on the correct answer
    //choices that match the correct answer add to the score and
    //different choices subtract from it
    //the score is divided by the number of possible answers at the end to get the
    //average. minimum score is 0.
    @Override
    public float grade(ChoiceAnswerSubmission sub) {
        if(!verifyAnswer(sub))
            return 0f;
        
        int score = 0;
        for(int i=0;i<this.getNumberOfPossibleAnswers();i++){
            if(sub.get(i)^correctAnswer.get(i))
                score--;
            else
                score++;
        }
        if(score<=0)
            return 0f;
        return (float) score/this.getNumberOfPossibleAnswers();
    }

    //returns a random answer based on confidence
    //confidence should be between 0(choose each selection randomly) and 1
    //(choose each selection correctly)
    @Override
    public AnswerSubmission getRandomAnswer(float confidence) {
        float chanceOfSelectingRight = confidence/2+0.5f;
        ChoiceAnswerSubmission randomSub = new ChoiceAnswerSubmission(this.getNumberOfPossibleAnswers());
        for(int i=0;i<this.getNumberOfPossibleAnswers();i++){
            if(chanceOfSelectingRight>Math.random())
                randomSub.set(i,correctAnswer.get(i));//check the option correctly
            else
                randomSub.set(i,!correctAnswer.get(i));//check the option incorrectly
        }
        return randomSub;
    }
    
}