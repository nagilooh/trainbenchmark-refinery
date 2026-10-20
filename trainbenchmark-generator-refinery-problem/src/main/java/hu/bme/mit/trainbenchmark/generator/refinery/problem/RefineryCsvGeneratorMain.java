package hu.bme.mit.trainbenchmark.generator.refinery.problem;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.config.Scenario;
import hu.bme.mit.trainbenchmark.generator.refinery.problem.config.RefineryCsvGeneratorConfigBuilder;

public class RefineryCsvGeneratorMain {

	public static void main(final String[] args) throws Exception {
		var configBase = new GeneratorConfigBase(Scenario.INJECT, 256);
		var generatorConfigBuilder = new RefineryCsvGeneratorConfigBuilder();
		var config = generatorConfigBuilder.setConfigBase(configBase).createConfig();
		final RefineryCsvSerializer refineryCsvSerializer = new RefineryCsvSerializer(config);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(refineryCsvSerializer, config);
		generator.generateModel();
	}

}
