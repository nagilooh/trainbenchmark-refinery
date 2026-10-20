package hu.bme.mit.trainbenchmark.generator.refinery.problem.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;

public class RefineryCsvGeneratorConfig extends GeneratorConfig {
	protected RefineryCsvGeneratorConfig(GeneratorConfigBase configBase) {
		super(configBase);
	}

	@Override
	public String getProjectName() {
		return "refineryCsv";
	}
}
