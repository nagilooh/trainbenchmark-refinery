package hu.bme.mit.trainbenchmark.generator.refinery.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;

public class RefineryGeneratorConfig extends GeneratorConfig {
	protected RefineryGeneratorConfig(GeneratorConfigBase configBase) {
		super(configBase);
	}

	@Override
	public String getProjectName() {
		return "refinery";
	}
}
