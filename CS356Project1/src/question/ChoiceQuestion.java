/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class describes a question where the answer can be chosen from
 * a list of possibilities
 */
package question;

import java.util.Collection;
import java.util.List;

public abstract class ChoiceQuestion implements Question{
    
    private final String questionText;
    private final List<ChoiceAnswerDescription> possibleAnswerList;
    
    //constructor
    public ChoiceQuestion(String questionText,List<ChoiceAnswerDescription> possibleAnswerList){
        this.questionText = questionText;
        this.possibleAnswerList = possibleAnswerList;
        
        if(possibleAnswerList.size()>26){
            System.out.println("Answer list too big. Trimming to 26...");
            while(possibleAnswerList.size()>26){
                possibleAnswerList.remove(possibleAnswerList.size()-1);
            }
        }
    }
    
    //returns the question and possible ansewr list in string format
    @Override
    public String getProblem() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionText);
        sb.append("\n");
        
        for(int i=0;i<possibleAnswerList.size();i++){
            sb.append(getAnswerTagString(i));
            sb.append(": ");
            sb.append(possibleAnswerList.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    //returns the list of possible answers
    public List<ChoiceAnswerDescription> getPossibleAnswerList() {
        return possibleAnswerList;
    }
    
    //returns the number of possible answers
    public int getNumberOfPossibleAnswers(){
        return possibleAnswerList.size();
    }

    //gets the answerNumber - 1 th answer description in the list
    public ChoiceAnswerDescription getAnswerDescription(int answerNumber) {
        return possibleAnswerList.get(answerNumber);
    }
    
    //gets the tag associated with the answer number (0 gives "A", 1 returns "B", ect
    public String getAnswerTagString(int id){
        return Character.toString(AnswerTag.numberToChar(id));
    }
    
    //returns whether the ansewr submission is reasonable for this question
    //if it is the wrong type, it is not, otherwise use the subclass method
    @Override
    public boolean verifyAnswer(AnswerSubmission sub){
        if(sub==null)
            return false;
        if(sub instanceof ChoiceAnswerSubmission)
            return verifyAnswer((ChoiceAnswerSubmission) sub);
        else return false;
    }
    
    //lets the subclass determine if the answer makes sense
    //for example, if multiple ansewrs are submitted for a single choice question
    //it will not pass
    public abstract boolean verifyAnswer(ChoiceAnswerSubmission sub);
    
    //returns the grade for the answer submission
    //0: wrong, 1: correct
    //if the submission is the wrong type, it is completely wrong
    @Override
    public float grade(AnswerSubmission sub){
        if(sub instanceof ChoiceAnswerSubmission)
            return grade((ChoiceAnswerSubmission) sub);
        else return 0f;
    }
    
    //lets the subclass grade the question after converting it
    public abstract float grade(ChoiceAnswerSubmission sub);
    
    //returns the correct answer
    public abstract ChoiceAnswerSubmission getCorrectChoiceAnswer();
    
    //returns the correcta answer
    @Override
    public AnswerSubmission getCorrectAnswer(){
        return getCorrectChoiceAnswer();
    }
    
    //returns a string describing the results of all the choice answer submissions in the collection
    //other types that may happen to be in the collection are ignored
    //example:
    //Submission results:
    //A: 7
    //B: 7
    //C: 1
    //D: 1
    @Override
    public String generateSubmissionResults(Collection<AnswerSubmission> allSubmissions){
        int numberOfAnswers = getNumberOfPossibleAnswers();
        int[] answerCountArray = new int[numberOfAnswers];
        
        
        for(AnswerSubmission sub: allSubmissions){
            if(sub==null)
                continue;
            if(!(sub instanceof ChoiceAnswerSubmission))
                continue;
            ChoiceAnswerSubmission cSub = (ChoiceAnswerSubmission) sub;
            
            for(int i=0;i<numberOfAnswers;i++){
                if(cSub.get(i))//use choice method here
                    answerCountArray[i] += 1;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Submission results:\n");
        for(int i=0;i<numberOfAnswers;i++){
            sb.append(getAnswerTagString(i));
            sb.append(": ");
            sb.append(answerCountArray[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
    
}
