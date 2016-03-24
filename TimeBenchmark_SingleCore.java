package prBenchmark;

import java.util.Random;

public class TimeBenchmark_SingleCore {

	
	public TimeBenchmark_SingleCore(int i, int j) throws BenchmarkException{
		TimeBenchmarkIterate(i,j);
	}

	private static void TimeBenchmarkIterate(int iteraciones, int rango) throws BenchmarkException{
		
		if(rango <= 0 || iteraciones <= 0){
			throw new BenchmarkException("Arguments can't be negative.");
		}
		double y = 0;
		double auxIteraciones = iteraciones / 10;
		
		Random random = new Random();
		double tiempoTotalIni = System.nanoTime();
		for(int i = 0; i <= 10; i++){
			
			
			double iteracionTiempoIni = System.nanoTime();
		for(int j = 0; j <= auxIteraciones; j++){
			int a = random.nextInt(rango);
			int b = random.nextInt(rango);
			int c = random.nextInt(rango);
			
			y = (-b+Math.sqrt(4*a*c))/2*a;
	
		}
			double iteracionTiempoFin = System.nanoTime();
			double tiempoIteracionNano = iteracionTiempoFin-iteracionTiempoIni;
			double tiempoReguladoIteracion;
			String unidadIteracion;
			if(tiempoIteracionNano >= 1000000000){
				tiempoReguladoIteracion = tiempoIteracionNano/1000000000;
				unidadIteracion = "seconds";
			}else if(tiempoIteracionNano >= 1000000 && tiempoIteracionNano <= 1000000000){
				tiempoReguladoIteracion = tiempoIteracionNano/1000000;
				unidadIteracion = "ms";
			}else{
				tiempoReguladoIteracion = tiempoIteracionNano;
				unidadIteracion = "ns";
			}
			System.out.println(auxIteraciones +" iterations finished in "+ tiempoReguladoIteracion + " " + unidadIteracion +" ("+(tiempoIteracionNano)+" ns"+")");
		}
		
		
		
		
		double tiempoTotalFin = System.nanoTime();
		double tiempoTotalNano = tiempoTotalFin-tiempoTotalIni;
		double tiempoReguladoTotal;
		String unidad;
		if(tiempoTotalNano >= 1000000000){
			tiempoReguladoTotal = tiempoTotalNano/1000000000;
			unidad = "seconds";
		}else if(tiempoTotalNano >= 1000000 && tiempoTotalNano <= 1000000000){
			tiempoReguladoTotal = tiempoTotalNano/1000000;
			unidad = "ms";
		}else{
			tiempoReguladoTotal = tiempoTotalNano;
			unidad = "ns";
		}
		
		System.out.println("Total time spent on "+ iteraciones +" iterations was: "+ tiempoReguladoTotal + " " + unidad +"("+(tiempoTotalNano)+"ns"+")");
		System.out.println("The last result in the evaluation of the expression was: "+ y);
	
	}
	

	
	
	
	
}
