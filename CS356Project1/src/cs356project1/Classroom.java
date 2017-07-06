/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class holds a group of students and tries to simulate how
 * they will vote
 */
package cs356project1;

import iVote.IVoteService;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Classroom {
    
    private final Set<Student> roster;
    
    //constructor
    public Classroom(){
        roster = new HashSet<>();
    }
    
    //constructor that adds students at the same time
    public Classroom(Collection<Student> list){
        roster = new HashSet<>();
        for(Student s: list){
            roster.add(s);
        }
    }
    
    //adds a student to the class. the roster is a set, so there is no need
    //to worry about adding the same student twice
    public void add(Student s){
        roster.add(s);
    }
    
    //removes the student from the class
    public void delete(Student s){
        roster.remove(s);
    }
    
    //simulates the class voting
    //uses the students' caring level to determine if they will vote at all
    //used their confidence to determine how many times they will change their mind
    //and resubmit the question. max of 10 times
    public void vote(IVoteService service){
        List<Student> voters = new LinkedList<>();
        
        //gets the people who will end up submitting something
        for(Student s:roster){
            if(Math.random()<=s.getCaringLevel())
                voters.add(s);
        }
        
        //gives every voter a chance to vote. If that vote is not their
        //final vote, they are not removed from the list and will vote next time
        //the maximum votes has been arbatrairally set to 10
        int numRounds = 0;
        while(!voters.isEmpty() && numRounds<10){
            Iterator<Student> it = voters.iterator();
            while(it.hasNext()){
                Student s = it.next();
                s.vote(service);
                if(s.getConfidence()>Math.random())
                    it.remove();
            }
            numRounds++;
        }
        
    }
}
