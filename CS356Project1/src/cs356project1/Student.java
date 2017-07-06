/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class describes a student tries to simulate how they might vote
 * based on 3 factors: competence, confidence, and caring level
 */
package cs356project1;

import iVote.IVoteService;
import question.AnswerSubmission;
import question.Question;
import Voter.Voter;

public class Student implements Voter{

    private String id;
    private String name;
    //all of the following fields should be between 0 and 1
    private float competence;//chance of submitting the correct answer
    private float confidence;//chance of only submitting once
    private float caringLevel;//chance of submitting anything at all
    
    //constructor
    public Student(String ID,String name,float competence,float confidence,float care){
        this.id = ID;
        this.name = name;
        this.competence = setBetween0and1(competence);
        this.confidence = setBetween0and1(confidence);
        this.caringLevel = setBetween0and1(care);
    }
    
    //gets the id
    @Override
    public String getID() {
        return id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the competence
     */
    public float getCompetence() {
        return competence;
    }

    /**
     * @param competence the competence to set
     */
    public void setCompetence(float competence) {
        this.competence = setBetween0and1(competence);
    }

    /**
     * @return the confidence
     */
    public float getConfidence() {
        return confidence;
    }

    /**
     * @param confidence the confidence to set
     */
    public void setConfidence(float confidence) {
        this.confidence = setBetween0and1(confidence);
    }

    /**
     * @return the caringLevel
     */
    public float getCaringLevel() {
        return caringLevel;
    }

    /**
     * @param caringLevel the caringLevel to set
     */
    public void setCaringLevel(float caringLevel) {
        this.caringLevel = setBetween0and1(caringLevel);
    }

    //returns an answer to the question based on how smart the student is
    @Override
    public AnswerSubmission getSubmission(Question question) {
        return question.getRandomAnswer(competence);
    }
    
    //equals method for comparing. This is based only on id, as they are unique
    @Override
    public boolean equals(Voter v2) {
        return this.id.equals(v2.getID());
    }

    //submitts an answer to the ivove service, unless the ivote service does not
    //have a question loaded up
    @Override
    public boolean vote(IVoteService ivs) {
        Question q = ivs.getQuestion();
        if(q==null)
            return false;
        
        return ivs.recieveAnswer(this, getSubmission(q));
    }
    
    //returns a number that is guarenteed to be between 0 and one
    //inputs outside this range result in the closest valid number
    private static float setBetween0and1(float f){
        if(f<0)
            return 0f;
        if(f>1)
            return 1f;
        return f;
    }

    //tells the student something
    @Override
    public void notify(String str) {
        System.out.println(getName() + " (Id# " + getID() + "): " + str);
    }

    
}
