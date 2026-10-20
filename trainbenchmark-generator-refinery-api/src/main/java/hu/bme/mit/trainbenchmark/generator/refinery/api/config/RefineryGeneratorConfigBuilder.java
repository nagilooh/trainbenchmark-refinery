package hu.bme.mit.trainbenchmark.generator.refinery.api.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBuilder;

public class RefineryGeneratorConfigBuilder extends GeneratorConfigBuilder<RefineryGeneratorConfig, RefineryGeneratorConfigBuilder> {
	@Override
	public RefineryGeneratorConfig createConfig() {
		checkNotNulls();
		return new RefineryGeneratorConfig(configBase);
	}
}
