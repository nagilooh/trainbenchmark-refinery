//package hu.bme.mit.trainbenchmark.benchmark.refinery.operations;
//
//import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;
//import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
//import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
//import hu.bme.mit.trainbenchmark.benchmark.refinery.driver.RefineryDriver;
//import hu.bme.mit.trainbenchmark.benchmark.refinery.matches.RefineryMatch;
//import hu.bme.mit.trainbenchmark.benchmark.refinery.queries.RefineryQuery;
//import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
//
//public class RefineryModelOperationFactory extends ModelOperationFactory<RefineryMatch, RefineryDriver> {
//	@Override
//	public ModelOperation<? extends RefineryMatch, RefineryDriver> createOperation(RailwayOperation operationEnum, String workspaceDir, RefineryDriver driver) throws Exception {
//
//		switch (operationEnum) {
//			case CONNECTEDSEGMENTS -> {
//				final RefineryQuery<ConnectedSegmentsMatch> query = new RefineryQueryConnectedSegments(driver);
//				final ModelOperation<ConnectedSegmentsMatch, RefineryDriver> operation = ModelOperation.of(query);
//				return operation;
//			}
//		}
//	}
//}
