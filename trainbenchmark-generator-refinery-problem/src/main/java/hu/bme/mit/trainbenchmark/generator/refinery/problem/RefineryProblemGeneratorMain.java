package hu.bme.mit.trainbenchmark.generator.refinery.problem;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.config.Scenario;
import hu.bme.mit.trainbenchmark.generator.refinery.problem.config.RefineryProblemGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.refinery.problem.config.RefineryProblemGeneratorConfigBuilder;

public class RefineryProblemGeneratorMain {

	public static void main(final String[] args) throws Exception {
//		final RefineryProblemGeneratorConfig gc = RefineryProblemGeneratorConfig.fromFile(args[0], RefineryProblemGeneratorConfig.class);

		var configBase = new GeneratorConfigBase(Scenario.BATCH, 1);
		var generatorConfigBuilder = new RefineryProblemGeneratorConfigBuilder();
		var config = generatorConfigBuilder.setConfigBase(configBase).createConfig();
		final RefineryProblemSerializer refineryProblemSerializer = new RefineryProblemSerializer(config);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(refineryProblemSerializer, config);
		generator.generateModel();
	}

}
