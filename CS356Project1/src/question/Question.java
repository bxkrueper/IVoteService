/**
 * Programmer: Benjamin Krueper
 * Class: CS356
 * Project #: 1
 * 
 * Purpose: this interface outlines methods all questions should have
 */
package question;

import java.util.Collection;

public interface Question {
    public String getProblem();
    public AnswerSubmission getCorrectAnswer();
    public AnswerSubmission getRandomAnswer(float confidence);
    public String getCorrectAnswerString();
    public boolean verifyAnswer(AnswerSubmission sub);
    public float grade(AnswerSubmission sub);
    public String generateSubmissionResults(Collection<AnswerSubmission> allSubmissions);
}
