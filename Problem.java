/**
 * Simulates a Problem
 * @author Jakin Ng and Taerim Eom
 * @version 12.5.2018
 */
// ask Deppe about equals/toString
public class Problem 
{
	int answer; //the answer to the problem 
	String question; //the question in String form (e.g. "1 + 1 = ")
	
	/**
	 * Constructs a problem with answer and question
	 * @param answer The answer to the problem
	 * @param question The question statement of the problem
 	 */
	public Problem(int answer, String question)
	{
		this.answer = answer;
		this.question = question;
	}
	
	/**
	 * Returns the answer to the Problem
	 * @return The answer to the Problem
	 */
	public int getAnswer() 
	{
		return answer;
	}
	
	/**
	 * Sets answer to the parameter answer
	 * @param answer The new answer that the answer PIV is being set to
	 */
	public void setAnswer(int answer) 
	{
		this.answer = answer;
	}
	
	/**
	 * Returns the question part of the Problem
	 * @return The question part of the Problem
	 */
	public String getQuestion() 
	{
		return question;
	}
	
	/**
	 * Sets question to the parameter question
	 * @param question The new question that the question PIV is being set to
	 */
	public void setQuestion(String question) 
	{
		this.question = question;
	}
	
	/**
	 * Returns whether or not the questions and answers of both Problems are equal
	 * @param problem
	 * @return Whether the two problems are equivalent
	 */
	public boolean equals(Problem problem)
	{
		// checks if answer and question are equal
		if (answer == problem.getAnswer() && question.equals(problem.getQuestion()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns this Problem in String form: both the question and the answer
	 * @return This Problem in String form
	 */
	public String toString()
	{
		String problem = question + " " + answer; //concatenates question and answer
		return problem;
	}
}
