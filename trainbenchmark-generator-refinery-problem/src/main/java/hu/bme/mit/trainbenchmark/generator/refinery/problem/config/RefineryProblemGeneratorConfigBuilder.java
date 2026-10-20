package hu.bme.mit.trainbenchmark.generator.refinery.problem.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBuilder;

public class RefineryProblemGeneratorConfigBuilder extends GeneratorConfigBuilder<RefineryProblemGeneratorConfig, RefineryProblemGeneratorConfigBuilder> {
	@Override
	public RefineryProblemGeneratorConfig createConfig() {
		checkNotNulls();
		return new RefineryProblemGeneratorConfig(configBase);
	}
}
