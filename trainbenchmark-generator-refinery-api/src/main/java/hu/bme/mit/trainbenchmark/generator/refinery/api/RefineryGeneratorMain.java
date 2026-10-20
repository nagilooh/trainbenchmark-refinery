package hu.bme.mit.trainbenchmark.generator.refinery.api;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.refinery.api.config.RefineryGeneratorConfig;

public class RefineryGeneratorMain {

	public static void main(final String[] args) throws Exception {
		final RefineryGeneratorConfig gc = RefineryGeneratorConfig.fromFile(args[0], RefineryGeneratorConfig.class);
		final RefinerySerializer refinerySerializer = new RefinerySerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(refinerySerializer, gc);
		generator.generateModel();
	}

}
