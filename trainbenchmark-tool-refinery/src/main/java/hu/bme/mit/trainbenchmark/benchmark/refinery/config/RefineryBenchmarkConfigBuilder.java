package hu.bme.mit.trainbenchmark.benchmark.refinery.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class RefineryBenchmarkConfigBuilder extends BenchmarkConfigBuilder<RefineryBenchmarkConfig, RefineryBenchmarkConfigBuilder> {
	@Override
	public RefineryBenchmarkConfig createConfig() {
		return new RefineryBenchmarkConfig(configBase);
	}
}
