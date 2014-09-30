package com.utn.ag.viajante.model;

import java.util.Arrays;
import java.util.Random;



/**
 * <p>
 * Implements selection of <i>n</i> candidates from a population by selecting
 * <i>n</i> candidates at random where the probability of each candidate getting
 * selected is proportional to its fitness score. This is analogous to each
 * candidate being assigned an area on a roulette wheel proportionate to its
 * fitness and the wheel being spun <n>i</n> times. Candidates may be selected
 * more than once.
 * </p>
 * 
 * <p>
 * In some instances, particularly with small population sizes, the randomness
 * of selection may result in excessively high occurrences of particular
 * candidates. If this is a problem, {@link StochasticUniversalSampling}
 * provides an alternative fitness-proportionate strategy for selection.
 * </p>
 * 
 * @author Daniel Dyer
 */
public class RouletteWheelSelection {
	public static Poblacion select(Poblacion poblacionActual,Random random) {
		// Record the cumulative fitness scores. It doesn't matter whether the
		// population is sorted or not. We will use these cumulative scores to
		// work out
		// an index into the population. The cumulative array itself is
		// implicitly
		// sorted since each element must be greater than the previous one. The
		// numerical difference between an element and the previous one is
		// directly
		// proportional to the probability of the corresponding candidate in the
		// population
		// being selected.
		double[] cumulativeFitnesses = new double[poblacionActual.size()];
		cumulativeFitnesses[0] = poblacionActual.get(0)
				.getFitness();
		for (int i = 1; i < poblacionActual.size(); i++) {
			cumulativeFitnesses[i] = cumulativeFitnesses[i - 1] + poblacionActual.get(i)
					.getFitness();
		}
		
		Poblacion selection = new Poblacion(poblacionActual.size());
		for (int i = 0; i < poblacionActual.size(); i++) {
			double randomFitness = random.nextDouble()
					* cumulativeFitnesses[cumulativeFitnesses.length - 1];
			int index = Arrays.binarySearch(cumulativeFitnesses, randomFitness);
			if (index < 0) {
				// Convert negative insertion point to array index.
				index = Math.abs(index + 1);
			}
			selection.add(poblacionActual.get(index));
		}
		return selection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Roulette Wheel Selection";
	}
}