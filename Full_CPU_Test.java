package prBenchmark;

import java.util.Random;

public class Full_CPU_Test extends Thread {

	public void run() {
		int sprints = 10;
		double y = 0; //Just to hold the result of the formula evaluation 
		double auxIterations = 1000000000/sprints; /*Splits, in as many sprints as the "sprints" variable is set to, the 
		number of iterations so each core can print several times during the process*/ 

		Random random = new Random(); //Random number generator
		double totalTimeStart = System.nanoTime(); //Total time starts ticking
		
		for (int i = 0; i <= 10; i++) { //Controls each sprint
			double sprintTimeStart = System.nanoTime(); //Sprint time starts
			for (int j = 0; j <= auxIterations; j++) {
				int a = random.nextInt(900);
				int b = random.nextInt(900); 	//Ecuation's Random numbers generation
				int c = random.nextInt(900);

				y = (-b + Math.sqrt(4*a*c))/ 2*a; /*Calculates something with random numbers, in this case it is a 
				second degree ecuation formula that can only throw Real numbers, not complex*/

			}
			
			double sprintTimeFinish = System.nanoTime(); //Sprint time finishes
			double sprintTime = sprintTimeFinish - sprintTimeStart; //Total time spent on the sprint
			double normalizedSprintTime; //To be used in the next method
			/*The next method is a bit useless, as all modern computers will most likely get a unit of seconds, but tested
			 * with an i5-3470 it gave about 4 seconds per sprint for each core with default values, so I don't doubt some
			 * computers out there are going to get the "ms" mark*/
			String sprintTimeUnit;
			if (sprintTime >= 1000000000) {
				normalizedSprintTime = sprintTime / 1000000000;
				sprintTimeUnit = "seconds";
			} else if (sprintTime >= 1000000 && sprintTime <= 1000000000) {
				normalizedSprintTime = sprintTime / 1000000;
				sprintTimeUnit = "ms";
			} else {
				normalizedSprintTime = sprintTime;
				sprintTimeUnit = "ns";
			}
			
			System.out.println(auxIterations + " iterations finished in " + normalizedSprintTime + " " + sprintTimeUnit);
		}
		
		/*Some nasty duplicated code that does the same useless thing as the method before, but this time with the total 
		 * time. (To be deprecated in next versions)*/
		double totalTimeFinish = System.nanoTime();
		double totalTime = totalTimeFinish - totalTimeStart;
		double normalizedTotalTime;
		String unit;
		if (totalTime >= 1000000000) {
			normalizedTotalTime = totalTime / 1000000000;
			unit = "seconds";
		} else if (totalTime >= 1000000 && totalTime <= 1000000000) {
			normalizedTotalTime = totalTime / 1000000;

			unit = "ms";
		} else {
			normalizedTotalTime = totalTime;
			unit = "ns";
		}
		
		Main.sumTimes(normalizedTotalTime);
		System.out.println("Total time spent on "+ auxIterations*sprints +" iterations was: " + normalizedTotalTime + " " + unit+"."); 
		//Prints total time
		
		/*System.out.println("The last result in the evaluation of the expression was: " + y); /*Ain't nobody got time for
		knowing the last result, and they dont care either*/

	}

}
