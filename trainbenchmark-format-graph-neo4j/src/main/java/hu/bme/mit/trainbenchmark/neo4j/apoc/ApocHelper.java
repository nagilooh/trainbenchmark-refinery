package hu.bme.mit.trainbenchmark.neo4j.apoc;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.api.exceptions.KernelException;
import org.neo4j.kernel.impl.proc.Procedures;
import org.neo4j.kernel.internal.GraphDatabaseAPI;

public class ApocHelper {

	// https://github.com/neo4j-contrib/neo4j-apoc-procedures/blob/a4423863ce77ef51c5d94cdbdc65d8a09172821b/src/test/java/apoc/util/TestUtil.java
	public static void registerProcedure(GraphDatabaseService db, Class<?>...procedures) throws KernelException {
		Procedures proceduresService = ((GraphDatabaseAPI) db).getDependencyResolver().resolveDependency(Procedures.class);
		for (Class<?> procedure : procedures) {
			proceduresService.registerProcedure(procedure);
			proceduresService.registerFunction(procedure);
		}
	}

//	// https://github.com/neo4j-contrib/neo4j-apoc-procedures/blob/9d5e1ce9d00992c78a8d2a985f6e89cfb20d41cd/test-utils/src/main/java/apoc/util/TestUtil.java
//	public static void registerProcedure(GraphDatabaseService db, Class<?>... procedures) {
//		GlobalProcedures globalProcedures =
//			((GraphDatabaseAPI) db).getDependencyResolver().resolveDependency(GlobalProcedures.class);
//		for (Class<?> procedure : procedures) {
//			try {
//				globalProcedures.registerProcedure(procedure, true);
//				globalProcedures.registerFunction(procedure, true);
//				globalProcedures.registerAggregationFunction(procedure, true);
//			} catch (KernelException e) {
//				throw new RuntimeException("while registering " + procedure, e);
//			}
//		}
//	}

}
