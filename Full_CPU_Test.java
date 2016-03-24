package prBenchmark;

import java.util.Random;

public class Full_CPU_Test extends Thread {

	public void run() {

		double y = 0; //Just to hold the result of the formula evaluation 
		double auxIterations = 1000000000 / 10;

		Random random = new Random();
		double tiempoTotalIni = System.nanoTime();
		for (int i = 0; i <= 10; i++) {

			double iteracionTiempoIni = System.nanoTime();
			for (int j = 0; j <= auxIterations; j++) {
				int a = random.nextInt(900);
				int b = random.nextInt(900);
				int c = random.nextInt(900);

				y = (-b + Math.sqrt(4 * a * c)) / 2 * a;

			}
			double iteracionTiempoFin = System.nanoTime();
			double tiempoIteracionNano = iteracionTiempoFin - iteracionTiempoIni;
			double tiempoReguladoIteracion;
			String unidadIteracion;
			if (tiempoIteracionNano >= 1000000000) {
				tiempoReguladoIteracion = tiempoIteracionNano / 1000000000;
				unidadIteracion = "seconds";
			} else if (tiempoIteracionNano >= 1000000 && tiempoIteracionNano <= 1000000000) {
				tiempoReguladoIteracion = tiempoIteracionNano / 1000000;
				unidadIteracion = "ms";
			} else {
				tiempoReguladoIteracion = tiempoIteracionNano;
				unidadIteracion = "ns";
			}
			System.out.println(auxIterations + " iterations finished in " + tiempoReguladoIteracion + " "
					+ unidadIteracion + " (" + (tiempoIteracionNano) + " ns" + ")");
		}

		double tiempoTotalFin = System.nanoTime();
		double tiempoTotalNano = tiempoTotalFin - tiempoTotalIni;
		double tiempoReguladoTotal;
		String unidad;
		if (tiempoTotalNano >= 1000000000) {
			tiempoReguladoTotal = tiempoTotalNano / 1000000000;
			unidad = "seconds";
		} else if (tiempoTotalNano >= 1000000 && tiempoTotalNano <= 1000000000) {
			tiempoReguladoTotal = tiempoTotalNano / 1000000;

			unidad = "ms";
		} else {
			tiempoReguladoTotal = tiempoTotalNano;
			unidad = "ns";
		}
		Main.sumarTiempos(tiempoReguladoTotal);
		System.out.println("Total time spent on 1000000000 iterations was: " + tiempoReguladoTotal + " " + unidad + "("
				+ (tiempoTotalNano) + "ns" + ")");
		System.out.println("The last result in the evaluation of the expression was: " + y);

	}

}
