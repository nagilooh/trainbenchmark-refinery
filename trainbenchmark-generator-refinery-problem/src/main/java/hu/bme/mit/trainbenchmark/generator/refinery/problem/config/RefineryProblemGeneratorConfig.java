package hu.bme.mit.trainbenchmark.generator.refinery.problem.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;

public class RefineryProblemGeneratorConfig extends GeneratorConfig {
	protected RefineryProblemGeneratorConfig(GeneratorConfigBase configBase) {
		super(configBase);
	}

	@Override
	public String getProjectName() {
		return "refineryProblem";
	}
}
