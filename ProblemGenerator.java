public class ProblemGenerator 
{
	final static int LEVEL_1_MIN_NUM = 1; //setting constraints so they are one-digit integers
	final static int LEVEL_1_MAX_NUM = 9;
	final static int LEVEL_1_MAX_SUM = 9; //sum must be less than 10
	final static int LEVEL_2_MIN_NUM = 1;
	final static int LEVEL_2_MAX_NUM = 9;
	final static int LEVEL_3_MIN_NUM = 1;
	final static int LEVEL_3_MAX_NUM = 9;
	// There are no PIVs as of now so if in the future there are still no PIVs we can maybe delete this idk
	public ProblemGenerator()
	{
	}
	
	public static void main(String[] args)
	{
		Problem bruh = new Problem(3, "5 - 2");
		Problem sys = new Problem(bruh.getAnswer(), bruh.getQuestion());
		Problem jade = sys;
		Problem lvl1 = generateProblem(1);
		Problem lvl2 = generateProblem(2);
		Problem lvl3 = generateProblem(3);
		System.out.println(bruh);
		System.out.println(sys);
		System.out.println(jade);
		System.out.println(lvl1);
		System.out.println(lvl2);
		System.out.println(lvl3);
		System.out.println(jade.equals(sys));
		System.out.println(jade == sys);
		System.out.println(jade.equals(bruh));
		System.out.println(jade == bruh);
		System.out.println(bruh.equals(sys));
		System.out.println(bruh == sys);
	}
	
	/**
	 * 
	 * Precondition: 1 <= level <= 3
	 * @param level
	 * @return
	 */
	public static Problem generateProblem(int level)
	{
		//the two numbers being added/subtracted
		int num1;
		int num2; 
		//if level is not 1, 2, or 3 it will return invalid input
		//otherwise question/answer will be changed
		Problem generatedProblem = new Problem(0, "invalid input");
		//dealing with the case for level 1: adding numbers (> 1: lvl 1 min num) less than 10 (level 1 min num) 
		//whose sum is less than (level 1 max sum)
		if (level == 1)
		{
			num1 = random(LEVEL_1_MIN_NUM, LEVEL_1_MAX_NUM);
			num2 = random(LEVEL_1_MIN_NUM, LEVEL_1_MAX_NUM); //generating random numbers
			int sum = num1 + num2; 
			while (sum > LEVEL_1_MAX_SUM) //checking if the sum satisfies the conditions
			{
				num1 = random(LEVEL_1_MIN_NUM, LEVEL_1_MAX_NUM);
				num2 = random(LEVEL_1_MIN_NUM, LEVEL_1_MAX_NUM);
				sum = num1 + num2;
			}
			//setting the problem's answer/question to what we want
			generatedProblem.setAnswer(sum);
			generatedProblem.setQuestion(Integer.toString(num1) + " + " + num2 + " ="); 
		}
		//the case where level is 2
		//tests addition of arbitrary one-digit numbers
		else if (level == 2)
		{
			//generates two random integers, finds the sum
			num1 = random(LEVEL_2_MIN_NUM, LEVEL_2_MAX_NUM);
			num2 = random(LEVEL_2_MIN_NUM, LEVEL_2_MAX_NUM);
			int sum = num1 + num2;
			//and sets the problem correspondingly
			generatedProblem.setAnswer(sum);
			generatedProblem.setQuestion(Integer.toString(num1) + " + " + num2 + " =");
		}
		//level is 3: subtraction of one-digit numbers with a non-negative difference
		else if (level == 3)
		{
			//finding two random 1-digit integers and making num1 the larger
			num1 = random(LEVEL_3_MIN_NUM, LEVEL_3_MAX_NUM);
			num2 = random(LEVEL_3_MIN_NUM, LEVEL_3_MAX_NUM);
			int bigger = Math.max(num1, num2);
			int smaller = Math.min(num1, num2);
			int difference = bigger - smaller;
			generatedProblem.setAnswer(difference);
			generatedProblem.setQuestion(Integer.toString(bigger) + " - " + smaller + " =");
		}
		return generatedProblem;
	}
	
	/**
	 * Returns a random integer between min and max, inclusive
	 * @param min The lower bound on the number returned
	 * @param max The upper bound on the number returned
	 * @return A random integer between min and max
	 */
	public static int random(int min, int max)
	{
		//uses the math.random() function and casting to int
		int randomInt = (int)(Math.random() * (max - min + 1) + min);
		return randomInt;
	}
}