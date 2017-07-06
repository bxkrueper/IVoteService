/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class simulates an IVote service for multiple choice questions
 */
package iVote;

import question.AnswerSubmission;
import question.Question;
import Voter.Voter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcreteIVoteService implements IVoteService{
    private final Map<Voter,AnswerSubmission> submissionMap;
    private Question currentQuestion;
    
    //constructor
    public ConcreteIVoteService(){
        submissionMap = new HashMap<>();
    }
    
    //get the current question
    public Question getQuestion(){
        return currentQuestion;
    }
    
    //changes the current question and sets everybody's answers to null
    public void setQuestion(Question q){
        clearSubmissions();
        currentQuestion = q;
    }
    
    //displays the question and the possible answers
    public void poseProblem(){
        if(currentQuestion==null)
            System.out.println("There is no question at the moment");
        else
            System.out.println(currentQuestion.getProblem());
    }
    
    //adds a voter to the system (unless they are already in the system)
    public void addVoter(Voter v){
        if(submissionMap.containsKey(v)){
            System.out.println(v.getID() + "has already been added!");
        }else{
            submissionMap.put(v,null);
        }
    }
    
    //add all the voters in the list to the system
    public void addAllVoters(List<Voter> voterList){
        for(Voter v:voterList){
            addVoter(v);
        }
    }
    
    //deletes the voter from the system
    public void deleteVoter(Voter v){
        submissionMap.remove(v);
    }
    
    //deletes all the voters in the list from the system
    public void deleteAllVoters(List<Voter> voterList){
        for(Voter v:voterList){
            deleteVoter(v);
        }
    }
    
    
    //clears all voters from the system
    public void clearAllVoters(){
        submissionMap.clear();
    }
    
    //if the answer is valid, put it in the map
    //an example of an invalid answer is when more than one answer is submittes for a single choice question
    //returns whether or not the answer was accepted so the caller can have a chance to respond
    //answers submitted by the same voter replace their previous one
    //a voter must be registered to send a submission
    public boolean recieveAnswer(Voter v,AnswerSubmission sub){
        if(!submissionMap.containsKey(v)){
            v.notify("You are not registered in the system!");
            return false;
        }
        if(currentQuestion==null){
            v.notify("There is no question at the moment");
            return false;
        }
        
        if(currentQuestion.verifyAnswer(sub)){
            if(submissionMap.get(v)==null){
                v.notify("Answer submitted");
            }else{
                v.notify("Answer updated");
            }
            submissionMap.put(v,sub);
            return true;
        }
        else{
            v.notify("your answer was not accepted");
            return false;
        }
    }
    
    //displays information about how everyone voted. formate depends on the question
    public void outputSubmissions(){
        if(currentQuestion==null){
            System.out.println("There are no submissions because there is no question!");
            return;
        }
        
        Collection<AnswerSubmission> allSubmissions = submissionMap.values();
        System.out.println(currentQuestion.generateSubmissionResults(allSubmissions));
    }
    
    //sets all regestered voters' answers to null
    public void clearSubmissions(){
        for (Map.Entry<Voter, AnswerSubmission> entry : submissionMap.entrySet()) {
            submissionMap.put(entry.getKey(), null);
        }
    }
    
    //prints the answer to the current question
    public void printCorrectAnswer(){
        if(currentQuestion==null){
            System.out.println("There is no answer, as there is no question");
            return;
        }
        System.out.println("Correct Answer: " + currentQuestion.getCorrectAnswerString());
    }
    
    //prints the average score of all the submissions
    public void printGradeStatistics(){
        if(currentQuestion==null){
            System.out.println("There is no answer, as there is no question");
            return;
        }
        
        float totalScore = 0f;
        for(AnswerSubmission sub: submissionMap.values()){
            totalScore += currentQuestion.grade(sub);
        }
        float averageScore = totalScore/getNumberOfSubmissions();
        System.out.println("Average score: " + averageScore);
    }

    //returns the number of voters in the system
    public int getNumberOfVoters() {
        return submissionMap.size();
    }

    //returns the number of submissions by the voters for the curernt question
    //submissions from the same voter replaced the previous one
    public int getNumberOfSubmissions() {
        Collection<AnswerSubmission> allSubmissions = submissionMap.values();
        int number=0;
        for(AnswerSubmission sub: allSubmissions){
            if(sub!=null)
                number++;
        }
        return number;
    }
}
