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
	
	
	public void setAnswer(int answer) 
	{
		this.answer = answer;
	}
	public String getQuestion() 
	{
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
}
