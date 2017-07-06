/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class holds the text of a possible answer
 * 
 */
package question;

public class ChoiceAnswerDescription {
    
    private final String description;
    
    //constructor
    public ChoiceAnswerDescription(String text){
        this.description = text;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    //for comparing
    public boolean equals(ChoiceAnswerDescription pa){
        return this.description.equals(pa.description);
    }
    
    //returns the text of the answer description
    @Override
    public String toString(){
        return description;
    }

}
