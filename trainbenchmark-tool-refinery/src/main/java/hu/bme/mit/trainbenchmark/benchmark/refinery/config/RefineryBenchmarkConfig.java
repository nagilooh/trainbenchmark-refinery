package hu.bme.mit.trainbenchmark.benchmark.refinery.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;

public class RefineryBenchmarkConfig extends BenchmarkConfig {
	protected RefineryBenchmarkConfig(BenchmarkConfigBase configBase) {
		super(configBase);
	}

	@Override
	public String getToolName() {
		return "Refinery";
	}

	@Override
	public String getProjectName() {
		return "refinery";
	}
}
