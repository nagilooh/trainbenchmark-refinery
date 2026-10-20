package hu.bme.mit.trainbenchmark.generator.refinery.problem.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBuilder;

public class RefineryCsvGeneratorConfigBuilder extends GeneratorConfigBuilder<RefineryCsvGeneratorConfig, RefineryCsvGeneratorConfigBuilder> {
	@Override
	public RefineryCsvGeneratorConfig createConfig() {
		checkNotNulls();
		return new RefineryCsvGeneratorConfig(configBase);
	}
}
