package hu.bme.mit.trainbenchmark.benchmark.refinery;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.refinery.config.RefineryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.refinery.driver.RefineryDriver;
import hu.bme.mit.trainbenchmark.benchmark.refinery.driver.RefineryDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.refinery.matches.RefineryMatch;
import hu.bme.mit.trainbenchmark.benchmark.refinery.operations.RefineryModelOperationFactory;

import java.util.Comparator;

public class RefineryBenchmarkScenario extends BenchmarkScenario<RefineryMatch, RefineryDriver, RefineryBenchmarkConfig> {
	public RefineryBenchmarkScenario(DriverFactory<RefineryDriver> driverFactory, ModelOperationFactory<RefineryMatch, RefineryDriver> modelOperationFactory, Comparator<RefineryMatch> comparator, RefineryBenchmarkConfig bc) throws Exception {
		super(driverFactory, modelOperationFactory, comparator, bc);
	}

	public RefineryBenchmarkScenario(RefineryBenchmarkConfig bc) {
		super(
			new RefineryDriverFactory(),
			new RefineryModelOperationFactory(),
			new RefineryMatchComparator(),
			bc);
	}
}
