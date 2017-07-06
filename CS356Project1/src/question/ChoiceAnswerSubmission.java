/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class describes an answer submission that is designed to pair
 * with choice questions it uses a bit set to store which answers were selected
 * and which were not
 */
package question;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class ChoiceAnswerSubmission implements AnswerSubmission{
    
    private final BitSet bitSet;
    private final int numPossibilities;
    private boolean isValid;
    
    //constructor
    //the creater is intended to set which answers are selected after object creation
    public ChoiceAnswerSubmission(int numPossibilities){
        this.isValid = true;
        this.numPossibilities = numPossibilities;
        this.bitSet = new BitSet(numPossibilities);
    }
    
    //constructor with answer tags
    public ChoiceAnswerSubmission(AnswerTag[] tagArray,int numPossibilities){
        this(numPossibilities);
        
        for(AnswerTag answerTag:tagArray){
            int answerID = answerTag.getNumber();
            if(answerID>=0 && answerID<numPossibilities)
                bitSet.set(answerID);
            else
                this.isValid = false;
        }
    }
    
    //constructor with chars
    public ChoiceAnswerSubmission(char[] charArray,int numPossibilities){
        this(numPossibilities);
        
        for(char ch:charArray){
            int answerID = AnswerTag.charToAnswerId(ch);
            if(answerID>=0 && answerID<numPossibilities)
                bitSet.set(answerID);
            else
                this.isValid = false;
        }
    }
    
    //constructor with ints. 0 means the first answer was selected
    public ChoiceAnswerSubmission(int[] intArray,int numPossibilities){
        this(numPossibilities);
        
        for(int answerID:intArray){
            if(answerID>=0 && answerID<numPossibilities)
                bitSet.set(answerID);
            else
                this.isValid = false;
        }
    }
    
    //copy constructor
    public ChoiceAnswerSubmission(ChoiceAnswerSubmission cas2){
        this.isValid = true;
        this.numPossibilities = cas2.numPossibilities;
        this.bitSet = (BitSet) cas2.bitSet.clone();
        
    }
    
    //returns whether the answer at the specified id is selected
    public boolean get(int answerID){
        return bitSet.get(answerID);
    }
    
    //selects or deselects the answer with the specified index
    public void set(int index,boolean value){
        if(index>=0 && index<numPossibilities){
            bitSet.set(index,value);
        }
        else{
            this.isValid = false;
        }
        
    }
    
    //returns how many answers are selected
    public int getNumberOfSelectedAnswers(){
        return bitSet.cardinality();
    }
    
    //returns whether an invalid answer was set (like -1)
    //there is no way to make it valid again, but these answer submissions are
    //intended to used and deleted quickly unless they end up in the IVoteSystem's
    //memory, but invalid submissions won't end up there
    @Override
    public boolean isValid(){
        return isValid;
    }
    
    //returns the ids of the selected answers in list format
    public List<Integer> getSelectedAnswerIDs(){
        List<Integer> list = new LinkedList<>();
        if(!isValid)
            list.add(-1);
        for(int i=0;i<numPossibilities;i++){
            if(get(i))
                list.add(i);
        }
        return list;
    }
    
    //returns the character tags (A-Z) of the selected answers in list format
    public List<Character> getSelectedAnswerLetters(){
        List<Integer> intList = getSelectedAnswerIDs();
        List<Character> charList = new ArrayList(intList.size());
        for(int i:intList){
            charList.add(AnswerTag.numberToChar(i));
        }
        return charList;
    }
    
    @Override
    public String toString(){
        return bitSet.toString() + " numPossibilities: " + numPossibilities + " valid: " + isValid;
    }
}
