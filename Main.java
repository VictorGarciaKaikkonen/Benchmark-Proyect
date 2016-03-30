package prBenchmark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*		A brief and simple to-do list for the future:
 * 
 * Implement other classes
 * Search of previous files in order to calculate a mean
 * GUI
 * */

public class Main {
	static File log = new File(
			"C:\\Users\\Public\\log.txt"); /*
						 * *Your path goes here* (it's where the file is going
						 * to be created). Make sure you use double back slashes
						 * (\\), otherwise Java will understand this as commands
						 */
	static Date date = new Date();
	static String fileName;
	static double totalTime = 0;
	static int times = 0;

	public static void main(String[] args) throws BenchmarkException {

		fileName = date.toString();
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("Application running on " + cores + " cores.");
		for (int i = 1; i <= cores; i++) {
			(new Full_CPU_Test()).start();
		}

		// calculateMean(log); /*It doesn't work yet*/

	}

	public static void sumTimesAndDisplayResults(double time) throws FileNotFoundException {
		totalTime += time;
		times++;
		double totalIterations = 1000 * (Runtime.getRuntime().availableProcessors());
		if (times == Runtime.getRuntime().availableProcessors()) {
			StringBuilder sb = new StringBuilder();

			System.out.println("----------------------------------------------------------------------------------");

			sb.append(System.lineSeparator());
			sb.append("The average time per core is: " + totalTime / Runtime.getRuntime().availableProcessors()
					+ " seconds.");
			sb.append(System.lineSeparator());
			System.out.println("The average time per core is: " + totalTime / Runtime.getRuntime().availableProcessors()
					+ " seconds.");

			System.out.println("----------------------------------------------------------------------------------");

			sb.append("The total number of iterations is: " + totalIterations + " millions,");
			sb.append(System.lineSeparator());
			System.out.println("The total number of iterations is: " + totalIterations + " millions,");

			System.out.println("Total time spent on " + Full_CPU_Test.getAuxIterations() * Full_CPU_Test.getSprints()
					+ " iterations was: " + Full_CPU_Test.getTimeSpent() + ".");
			sb.append("Total time spent on " + Full_CPU_Test.getAuxIterations() * Full_CPU_Test.getSprints()
					+ " iterations was: " + Full_CPU_Test.getTimeSpent() + ".");
			sb.append(System.lineSeparator());

			sb.append("---> Those values make a final result of: "
					+ (totalIterations) / (totalTime / Runtime.getRuntime().availableProcessors())
					+ " million iterations per second <---");
			System.out.println("---> Those values make a final result of: "
					+ (totalIterations) / (totalTime / Runtime.getRuntime().availableProcessors())
					+ " million iterations per second <---");
			System.out.println("----------------------------------------------------------------------------------");

			sb.append(System.lineSeparator());
			writeFile(sb.toString());

		}
	}

	private static void writeFile(String data) throws FileNotFoundException {

		try {
			if (log.exists() == false) { /*
											 * If the file doesn't exist, it
											 * creates a new one in the
											 * specified path
											 */
				System.out.println("I made a new file at " + log.getAbsolutePath());
				log.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(log, true));
			System.out.println("Writing to " + log.getName());
			out.append(System.lineSeparator());
			out.append("******* " + date + "******* "
					+ "\n");/*
							 * It adds the date to the new log's text and writes
							 * the results below
							 */
			out.append(data);
			out.close();
		} catch (IOException e) {
			System.out.println("I couldn't log."); // Something went wrong
		}

	}

	@SuppressWarnings("unused")
	private static void calculateMean(File file) {
		/*
		 * This calculates the mean of the results written on your log file.
		 * Make sure you have at least 4 runs present on your log, otherwise it
		 * won't work. The more you have, the more accurate the mean will be.
		 * Also make sure to run your test with everything else closed, as every
		 * tiny bit of resources being drawn is going to affect the result.
		 * 
		 * If you see that a particular run gets irregular results, delete that
		 * part of the log manually, as it would affect the mean.
		 */

		try {

			// Opens the log as a buffered reader
			BufferedReader bf = new BufferedReader(new FileReader(log));

			/*
			 * Start a line count and declare a string to hold our current line
			 * as well as a hit counter and a String array and double array
			 */

			int linecount = 0;
			int hit = 0;
			String line;
			double[] results = new double[10];
			String[] matchingLines = new String[10];

			for (int i = 0; i < results.length; i++) {
				results[i] = 0;
			}

			Pattern p = Pattern.compile("\\bThose values make a final result of:\\b", Pattern.CASE_INSENSITIVE);

			while ((line = bf.readLine()) != null) {
				linecount++;

				Matcher m = p.matcher(line);

				// indicate all matches on the line
				while (m.find()) {
					if (matchingLines.length >= hit) {
						matchingLines = Arrays.copyOf(matchingLines, 2 * matchingLines.length);
						results = Arrays.copyOf(results, 2 * results.length);
					}

					matchingLines[hit] = line;
					String tmpstr = matchingLines[hit];
					String asd[] = tmpstr.split(" ");
					double dbl = Double.parseDouble(asd[1]);
					results[hit] = dbl;

					hit++;
					System.out.println("String was found at position " + m.start() + " on line " + linecount);
				}
			}

			// Close the file after done searching
			bf.close();

			if (hit >= 4) {
				double res, sum = 0;
				int max = 1;
				for (int i = 0; i < results.length; i++) {
					if (results[i] != 0) {
						sum += results[i];
						max = i + 1;
					}
				}
				res = sum / max;
				System.out.println("The mean of " + max + " runs is " + res + " million iterations per second.");
			}
		} catch (IOException e) {
			System.out.println("IO Error Occurred: " + e.toString());
		}
	}

}
