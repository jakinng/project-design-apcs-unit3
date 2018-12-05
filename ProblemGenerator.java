public class ProblemGenerator 
{
	final static int LEVEL_1_MIN_NUM = 1;
	final static int LEVEL_1_MAX_NUM = 9;
	final static int LEVEL_1_MAX_SUM = 9;
	// There are no PIVs as of now so if in the future there are still no PIVs we can maybe delete this idk
	public ProblemGenerator()
	{
	}
	
	/**
	 * 
	 * Precondition: 1 <= level <= 3
	 * @param level
	 * @return
	 */
	public static Problem generateProblem(int level)
	{
		int num1;
		int num2; //the two numbers being added/subtracted
		if (level == 1)
		{
			num1 = (int)(Math.random() * LEVEL_1_MAX_NUM + LEVEL_1_MIN_NUM);
			num2 = (int)(Math.random() * LEVEL_1_MAX_NUM  + LEVEL_1_MIN_NUM);
			int sum = num1 + num2;
			while (sum >= 10)
			{
				num1 = (int)(Math.random() * LEVEL_1_MAX_NUM + LEVEL_1_MIN_NUM);
				num2 = (int)(Math.random() * LEVEL_1_MAX_NUM  + LEVEL_1_MIN_NUM);
			}
		}
	}
}
