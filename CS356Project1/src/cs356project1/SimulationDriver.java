/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this class is a driver that tests the IVoteSimulator
 */
package cs356project1;

import iVote.IVoteService;
import iVote.ConcreteIVoteService;
import question.ChoiceAnswerSubmission;
import question.ChoiceAnswerDescription;
import question.MultipleChoiceQuestion;
import question.ChoiceQuestion;
import question.SingleChoiceQuestion;
import Voter.RandomVoter;
import Voter.ManualVoter;
import Voter.Voter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationDriver {

    //breaks up the testing into 3 stages
    public static void main(String[] args) {
        testEmptyService();
        testWithManualAndRandomVoters();
        testWithClassroom();
    }
    
    //tests for null pointer erros and outlier problems like them
    private static void testEmptyService(){
        IVoteService service = new ConcreteIVoteService();
        
        ManualVoter voter1 = new ManualVoter("001");
        ManualVoter voter2 = new ManualVoter("002");
        service.addVoter(voter1);
        
        service.poseProblem();
        
        boolean b = voter1.vote(service,new ChoiceAnswerSubmission(new char[]{'a'},2));
        voter2.vote(service,new ChoiceAnswerSubmission(new char[]{'a'},2));
        
        System.out.println("number of voters: " + service.getNumberOfVoters());
        System.out.println(b);
        
        System.out.println();
        service.outputSubmissions();
        
        System.out.println();
        service.printCorrectAnswer();
        
    }
    
    //tests specific situations manually
    private static void testWithManualAndRandomVoters(){
        IVoteService service = new ConcreteIVoteService();
        
        ChoiceQuestion q1 = question1();
        service.setQuestion(q1);
        
        List<ManualVoter> voterList = new ArrayList();
        voterList.add(new ManualVoter("000"));
        voterList.add(new ManualVoter("001"));
        voterList.add(new ManualVoter("002"));
        voterList.add(new ManualVoter("003"));
        voterList.add(new ManualVoter("004"));
        
        for(Voter v: voterList){
            service.addVoter(v);
        }
        
        service.poseProblem();
        
        voterList.get(0).vote(service, new ChoiceAnswerSubmission(new char[]{'a'},q1.getNumberOfPossibleAnswers()));
        voterList.get(1).vote(service, new ChoiceAnswerSubmission(new char[]{'c'},q1.getNumberOfPossibleAnswers()));
        voterList.get(2).vote(service, new ChoiceAnswerSubmission(new char[]{'a'},q1.getNumberOfPossibleAnswers()));
        voterList.get(3).vote(service, new ChoiceAnswerSubmission(new char[]{'b'},q1.getNumberOfPossibleAnswers()));
        voterList.get(4).vote(service, new ChoiceAnswerSubmission(new char[]{'d'},q1.getNumberOfPossibleAnswers()));
        
        System.out.println();
        service.outputSubmissions();
        System.out.println();
        
        voterList.get(4).vote(service, new ChoiceAnswerSubmission(new char[]{'b'},q1.getNumberOfPossibleAnswers()));
        
        service.outputSubmissions();
        System.out.println();
        
        service.printCorrectAnswer();
        service.printGradeStatistics();
        System.out.println("# of voters: " + service.getNumberOfVoters());
        System.out.println("# of voters who submited: " + service.getNumberOfSubmissions());
        System.out.println();
        
        
        System.out.println("\n\n\n");
        
        
        
        
        
        
        
        ChoiceQuestion q2 = question2();
        service.setQuestion(q2);
        
        service.poseProblem();
        
        voterList.get(0).vote(service, new ChoiceAnswerSubmission(new char[]{'a','b','g'},q2.getNumberOfPossibleAnswers()));
        voterList.get(1).vote(service, new ChoiceAnswerSubmission(new char[]{'a','b'},q2.getNumberOfPossibleAnswers()));
        voterList.get(2).vote(service, new ChoiceAnswerSubmission(new char[]{'b'},q2.getNumberOfPossibleAnswers()));
        voterList.get(3).vote(service, new ChoiceAnswerSubmission(new char[]{'b','d'},q2.getNumberOfPossibleAnswers()));
        voterList.get(4).vote(service, new ChoiceAnswerSubmission(new char[]{'a'},q2.getNumberOfPossibleAnswers()));
        
        System.out.println();
        service.outputSubmissions();
        System.out.println();
        
        voterList.get(4).vote(service, new ChoiceAnswerSubmission(new char[]{'c'},q2.getNumberOfPossibleAnswers()));
        
        service.outputSubmissions();
        System.out.println();
        
        service.printCorrectAnswer();
        
        service.printGradeStatistics();
        System.out.println("# of voters: " + service.getNumberOfVoters());
        System.out.println("# of voters who submited: " + service.getNumberOfSubmissions());
        
        
        
        
        
        System.out.println("\n\n\n");
        service.clearAllVoters();
        
        ChoiceQuestion q4 = question4();
        service.setQuestion(q4);
        
        List<RandomVoter> randomVoterList = new ArrayList();
        int numberOfRandomVoters = (int) (Math.random()*10+5);
        for(int i=0;i<numberOfRandomVoters;i++){//add a random amount of random voters to the list
            randomVoterList.add(new RandomVoter(Integer.toString(i)));
        }
        
        for(Voter v: randomVoterList){
            service.addVoter(v);
        }
        
        service.poseProblem();
        
        for(int i=0;i<randomVoterList.size()-1;i++){//all but the last voter votes
            randomVoterList.get(i).vote(service);
        }
        
        System.out.println();
        service.outputSubmissions();
        System.out.println();
        
        
        service.printCorrectAnswer();
        service.printGradeStatistics();
        System.out.println("# of voters: " + service.getNumberOfVoters());
        System.out.println("# of voters who submited: " + service.getNumberOfSubmissions());
        System.out.println();
    }
    
    //tests the system using the classroom class that makes students vote automatically
    public static void testWithClassroom(){
        System.out.println("\n\n\n");
        IVoteService service = new ConcreteIVoteService();
        
        ChoiceQuestion q2 = question2();
        service.setQuestion(q2);
        
        List<Student> studentList = new ArrayList();
        
        studentList.add(new Student("001","Bob",0.1f,1f,0.9f));
        studentList.add(new Student("002","Samatha",0.6f,0.9f,0.95f));
        studentList.add(new Student("003","Hector",0.5f,0.8f,0.99f));
        studentList.add(new Student("004","Gerald",0.7f,0.8f,0.97f));
        studentList.add(new Student("005","Jesse",0.2f,0.8f,0.99f));
        studentList.add(new Student("006","Bruiser",0.9f,0.1f,0.97f));
        studentList.add(new Student("007","Oliver",0.8f,0.8f,0.95f));
        studentList.add(new Student("008","Beth",0.7f,0.6f,0.93f));
        studentList.add(new Student("009","Alex",0.8f,0.95f,0.94f));
        studentList.add(new Student("010","Tony",0.5f,0.89f,0.49f));
        studentList.add(new Student("011","Darel",0.7f,0.85f,0.91f));
        studentList.add(new Student("012","James",0.95f,0.91f,0.97f));
        studentList.add(new Student("013","Jane",0.9f,0.67f,0.93f));
        
        Classroom classroom = new Classroom();
        
        for(Student s:studentList){
            classroom.add(s);
            service.addVoter(s);
        }
        
        service.poseProblem();
        classroom.vote(service);
        
        service.outputSubmissions();
        System.out.println();
        
        service.printCorrectAnswer();
        service.printGradeStatistics();
        System.out.println("# of voters who submited: " + service.getNumberOfSubmissions());
        
        
        
        //testing answer with lots of choices
        System.out.println("\n\n\n");
        ChoiceQuestion q3 = question3();
        service.setQuestion(q3);
        
        service.poseProblem();
        classroom.vote(service);
        
        
        
        service.outputSubmissions();
        System.out.println();
        
        service.printCorrectAnswer();
        service.printGradeStatistics();
        System.out.println("# of voters who submited: " + service.getNumberOfSubmissions());
    }
    
    //returns question 1 (single choice)
    private static ChoiceQuestion question1(){
        String questionText = "Is mayonnaise an instrument?";
        List<ChoiceAnswerDescription> possibleAnswerList = new LinkedList<>();
        possibleAnswerList.add(new ChoiceAnswerDescription("Yes"));
        possibleAnswerList.add(new ChoiceAnswerDescription("No"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Sometimes"));
        ChoiceQuestion q = new SingleChoiceQuestion(questionText,possibleAnswerList,'b');
        return q;
    }
    
    //returns question 2 (mulitple choice)
    private static ChoiceQuestion question2(){
        String questionText = "This is... (pick all the right answers)";
        List<ChoiceAnswerDescription> possibleAnswerList = new LinkedList<>();
        possibleAnswerList.add(new ChoiceAnswerDescription("Sparta"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Patrick"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Not a fair question"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #4"));
        ChoiceQuestion q = new MultipleChoiceQuestion(questionText,possibleAnswerList,'a','b');
        return q;
    }
    
    //returns question 3 (tests big answer lists)
    private static ChoiceQuestion question3(){
        String questionText = "This is test question #3 (pick one)";
        List<ChoiceAnswerDescription> possibleAnswerList = new LinkedList<>();
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #1"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #2"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #3"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #4"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #5"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #6"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #7"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #8"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #9"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #10"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #11"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #12"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #13"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #14"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #15"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #16"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #17"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #18"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #19"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #20"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #21"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #22"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #23"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #24"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #25"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #26"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #27"));
        possibleAnswerList.add(new ChoiceAnswerDescription("Answer #28"));
        ChoiceQuestion q = new SingleChoiceQuestion(questionText,possibleAnswerList,'b');
        return q;
    }
    
    //returns question 4 (testing for one answer)
    private static ChoiceQuestion question4(){
        String questionText = "Every villain is ___";
        List<ChoiceAnswerDescription> possibleAnswerList = new LinkedList<>();
        possibleAnswerList.add(new ChoiceAnswerDescription("lemons"));
        ChoiceQuestion q = new SingleChoiceQuestion(questionText,possibleAnswerList,'a');
        return q;
    }
    
}
