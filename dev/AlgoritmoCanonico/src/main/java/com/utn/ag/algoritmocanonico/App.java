package com.utn.ag.algoritmocanonico;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang3.StringUtils;

import com.utn.ag.algoritmocanonico.gui.PnlInformeChart;
import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;
import com.utn.ag.algoritmocanonico.service.AlgoritmoCanonico;
import com.utn.ag.algoritmocanonico.service.impl.AlgoritmoCanonicoImpl;
import com.utn.ag.algoritmocanonico.vo.InformeChart;
import com.utn.ag.algoritmocanonico.vo.InformeVO;

public class App {
	private static Options createOptions() {
		Options options = new Options();
		options.addOption("c", "crossover", true,
				"probabilidad de crossover double number: between 0 and 1");
		options.addOption("d", "debug", false, "debug app");
		options.addOption("f", "fast", false, "no gui");
		options.addOption("h", "help", false, "print this message and exit");
		options.addOption("i", "iteraciones", true, "nro iteraciones");
		options.addOption("inf", "informe", false, "escribe el informe");
		options.addOption("o", "output file", true,
				"save data to output file in csv");
		options.addOption("p", "poblacion", true, "poblacion n");
		options.addOption("m", "mutacion", true,
				"probabilidad de crossover double number: between 0 and 1");
		options.addOption("v", "verbose", false, "verbose");
		return options;
	}

	private static void showHelp(Options options) {
		HelpFormatter h = new HelpFormatter();
		h.printHelp("help", options);
	}

	public static void main(String args[]) throws FileNotFoundException {
		Options options = createOptions();
		try {
			CommandLineParser parser = new PosixParser();
			CommandLine cmd = parser.parse(options, args);
			if (cmd.hasOption("c")) {
				try {
					Double probabilidad = Double.parseDouble(cmd
							.getOptionValue("c"));
					if (probabilidad >= 0 || probabilidad <= 1) {
						AppConstants.PROBCROSSOVER = probabilidad;
					}
				} catch (NumberFormatException ex) {
					// do not care
				}
			}
			if (cmd.hasOption("m")) {
				try {
					Double probabilidad = Double.parseDouble(cmd
							.getOptionValue("m"));
					if (probabilidad >= 0 || probabilidad <= 1) {
						AppConstants.PROBMUTACION = probabilidad;
					}
				} catch (NumberFormatException ex) {
					// do not care
				}
			}
			if (cmd.hasOption("i")) {
				try {
					Integer iteraciones = Integer.parseInt(cmd
							.getOptionValue("i"));
					AppConstants.ITERACIONES = iteraciones;
				} catch (NumberFormatException ex) {
					// do not care
				}
			}
			if (cmd.hasOption("p")) {
				try {
					Integer poblacion = Integer.parseInt(cmd
							.getOptionValue("p"));
					AppConstants.POBLACION = poblacion;
				} catch (NumberFormatException ex) {
					// do not care
				}
			}
			if (cmd.hasOption("h")) {
				showHelp(options);
			}
			MockedLogger.DEBUG = cmd.hasOption("d");
			MockedLogger.VERBOSE = cmd.hasOption("v");

			if (cmd.hasOption("o")) {
				String outputPath = cmd.getOptionValue("o");
				if (!StringUtils.isEmpty(outputPath)) {
					AppConstants.FILE_INFORME = outputPath;
				}
			}
			if(cmd.hasOption("inf")){
				MockedLogger.WRITE_FILE = true;
			}
			if (cmd.hasOption("f")) {
				generarInforme();
			} else {
				PnlInformeChart.showApp();
			}
		} catch (Exception e) {
			e.printStackTrace();
			showHelp(options);
			System.exit(-1);
		}

	}

	public static InformeChart generarInforme() {
		// clean previous created file
		File f = new File(AppConstants.FILE_INFORME);
		f.delete();
		MockedLogger.informe("Max-F(x),Min-F(x),Promedio,MaxGenoma");
		InformeChart informe = new InformeChart();
		AlgoritmoCanonico a = new AlgoritmoCanonicoImpl();
		Poblacion p = null;
		for (int i = 0; i < AppConstants.ITERACIONES - 1; i++) {
			if (i == 0) {
				p = a.nuevaPoblacion();
			} else {
				p = a.nuevaPoblacion(p);
			}
			MockedLogger.verbose("## Nueva Poblacion: " + i);
			InformeVO informeVO = p.getInformeVO();
			MockedLogger.writeInforme(informeVO);
			informe.add(informeVO);
		}
		MockedLogger.debug("fin!");
		return informe;
	}

}
