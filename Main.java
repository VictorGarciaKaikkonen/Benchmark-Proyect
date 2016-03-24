package prBenchmark;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class Main {
	static Date date = new Date();
	static String nombreFichero;
	static double tiempoTotal = 0;
	static int veces = 0;
	public static void main(String[] args) throws BenchmarkException {
		
		nombreFichero = date.toString();
		int numCPUs = Runtime.getRuntime().availableProcessors();
		System.out.println("Application running on "+ numCPUs +" cores.");
		for(int i = 1; i <= numCPUs; i++){
			(new Full_CPU_Test()).start();
		}
	
		//Implementar la exposición de resultados
		//Implementar Escritura de fichero
		//Implementar el resto de los algoritmos
		//Búsqueda de fichero anterior
		//Interfaz
			}

	
	public static void sumarTiempos(double tiempo){
			tiempoTotal += tiempo;
			veces++;
			double totalIterations = 1000* (Runtime.getRuntime().availableProcessors());
			if(veces == Runtime.getRuntime().availableProcessors()){
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("The sum of time used by all the CPUs is: "+ tiempoTotal+" seconds.");
			System.out.println("The average time per core is: " + tiempoTotal/Runtime.getRuntime().availableProcessors()+ " seconds.");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("The total number of iterations is: " +totalIterations+" millions,");
			System.out.println("which are done in "+tiempoTotal+"seconds");
			System.out.println("Those values make a final result of: "+ (totalIterations)/(tiempoTotal/Runtime.getRuntime().availableProcessors()) + " million iterations per second");
			System.out.println("----------------------------------------------------------------------------------");

			}
	}			
	public static void imprimir(String datos){
		
		System.out.println(datos);
		//escribirFichero(datos);
		
		
	}
	


/*
	private static void escribirFichero(String datos) throws FileNotFoundException {
		
		try(PrintWriter pw = new PrintWriter(new File("C:\\Users\\Víctor\\Desktop\\nombreFichero.txt"))){
			
			
			
			
		}
		
	}*/
}
