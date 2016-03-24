package prBenchmark;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

/*		A brief and simple to-do list for the future
 * 
 * Implement a decent result giving method
 * Implement a log file
 * Implement the other classes
 * Search of previous files in order to calculate a mean
 * GUI
 * */
		


public class Main {
	static Date date = new Date();
	static String fileName;
	static double totalTime = 0;
	static int times = 0;
	public static void main(String[] args) throws BenchmarkException {
		
		fileName = date.toString();
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("Application running on "+ cores +" cores.");
		for(int i = 1; i <= cores; i++){
			(new Full_CPU_Test()).start();
		}
	
			}

	
	public static void sumTimes(double time){
			totalTime += time;
			times++;
			double totalIterations = 1000* (Runtime.getRuntime().availableProcessors());
			if(times == Runtime.getRuntime().availableProcessors()){
			System.out.println("----------------------------------------------------------------------------------");
			//System.out.println("The sum of time used by all the CPUs is: "+ totalTime+" seconds.");//A bit of a nonsense
			System.out.println("The average time per core is: " + totalTime/Runtime.getRuntime().availableProcessors()+ " seconds.");
			//System.out.println("----------------------------------------------------------------------------------");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("The total number of iterations is: " +totalIterations+" millions,");
			System.out.println("which are done in "+totalTime+" seconds");
			System.out.println("---> Those values make a final result of: "+ (totalIterations)/(totalTime/Runtime.getRuntime().availableProcessors()) + " million iterations per second <---");
			System.out.println("----------------------------------------------------------------------------------");

			}
	}			
	
	/*public static void imprimir(String datos){
		
		System.out.println(datos);
		escribirFichero(datos);
		
		
	}*/
	
	
/*
	private static void escribirFichero(String datos) throws FileNotFoundException {
		
		try(PrintWriter pw = new PrintWriter(new File("C:\\Users\\Víctor\\Desktop\\nombreFichero.txt"))){
			
			
			
			
		}
		
	}*/
}
