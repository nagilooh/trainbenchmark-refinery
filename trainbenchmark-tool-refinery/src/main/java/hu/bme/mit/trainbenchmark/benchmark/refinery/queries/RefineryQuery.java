package hu.bme.mit.trainbenchmark.benchmark.refinery.queries;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.refinery.driver.RefineryDriver;
import hu.bme.mit.trainbenchmark.benchmark.refinery.matches.RefineryMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import tools.refinery.store.query.ModelQueryAdapter;

import java.util.Collection;

public abstract class RefineryQuery<TRefineryMatch extends RefineryMatch> extends ModelQuery<TRefineryMatch, RefineryDriver> {

	ModelQueryAdapter queryEngine;
	protected Collection<TRefineryMatch> matches;

	public RefineryQuery(RailwayQuery query, RefineryDriver driver) {
		super(query, driver);
		queryEngine = driver.getEngine();
	}

	@Override
	public Collection<TRefineryMatch> evaluate() {
		matches = getMatcher().getAllMatches();
		return matches;
	}

}
