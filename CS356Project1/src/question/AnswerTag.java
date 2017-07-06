/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class holds information about the answer tag (A,B,C, ect)
 * tags can only be marked A-Z
 * It also has methods to convert between letters and the id that is used
 */
package question;

public class AnswerTag {
    
    private int id;//0 means the first answer, 1 means the second, and so on
    
    //constructor. If the passed id is not valid, it sets it to -1
    public AnswerTag(int id){
        if(id>=0 && id<=25)
            this.id = id;
        else
            this.id = -1;
    }
    
    //constructor. If the passed character is not valid, it sets it to -1
    //not case sensitive
    public AnswerTag(char letter){
        this.id = charToAnswerId(letter);
    }
    
    //copy constructor
    public AnswerTag(AnswerTag at2){
        this.id = at2.id;
    }
    
    //gets the id (0 means the first answer)
    public int getNumber(){
        return id;
    }
    
    //returns the letter that corresponds to the id
    //for example, if the id is 0, it returns 'A'
    //if the id is not valid, it returns '?'
    public char getLetter(){
        if(id==-1)
            return '?';
        else
            return (char) (id+65);
    }
    
    //returned if the id is valid
    public boolean isValid(){
        return id!=-1;
    }
    
    //converts a number 0-25 to a char. 0 converts to a, 1 converts to b,ect...
    //other numbers just return a '?'
    protected static char numberToChar(int n){
        if(n>=0 && n<=25)
            return (char) (n+65);
        return '?';
    }
    
    //converts a letter to a number id between 0 and 25. Case insensitive
    //other characters return -1
    protected static int charToAnswerId(char c){
        int id = (int) c;
        if(id>=48 && id<=57)
            return id-48;
        if(id>=65 && id<=90)
            return id-65;
        if(id>=97 && id<=122)
            return id-97;
        return -1;
    }
}
