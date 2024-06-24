package hu.bme.mit.trainbenchmark.benchmark.refinery.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import tools.refinery.logic.dnf.Query;
import tools.refinery.logic.dnf.RelationalQuery;
import tools.refinery.logic.term.truthvalue.TruthValue;
import tools.refinery.store.model.Model;
import tools.refinery.store.model.ModelStore;
import tools.refinery.store.query.ModelQueryAdapter;
import tools.refinery.store.query.interpreter.QueryInterpreterAdapter;
import tools.refinery.store.query.view.AnySymbolView;
import tools.refinery.store.query.view.KeyOnlyView;
import tools.refinery.store.representation.Symbol;

public class RefineryDriver extends Driver {

	protected ModelQueryAdapter engine;
	protected Model model;

	private static final Symbol<Boolean> railwayContainer = Symbol.of("RailwayContainer", 1);
	private static final Symbol<Boolean> railwayElement = Symbol.of("RailwayElement", 1);
	private static final Symbol<Boolean> region = Symbol.of("Region", 1);
	private static final Symbol<Boolean> semaphore = Symbol.of("Semaphore", 1);
	private static final Symbol<Boolean> route = Symbol.of("Route", 1);
	private static final Symbol<Boolean> switchPosition = Symbol.of("SwitchPosition", 1);
	private static final Symbol<Boolean> trackElement = Symbol.of("TrackElement", 1);
	private static final Symbol<Boolean> sensor = Symbol.of("Sensor", 1);
	private static final Symbol<Boolean> segment = Symbol.of("Segment", 1);
	private static final Symbol<Boolean> sswitch = Symbol.of("Switch", 1);
	private static final Symbol<Boolean> routes = Symbol.of("routes", 2);
	private static final Symbol<Boolean> regions = Symbol.of("regions", 2);
	private static final Symbol<Boolean> sensors = Symbol.of("sensors", 2);
	private static final Symbol<Boolean> elements = Symbol.of("elements", 2);
	private static final Symbol<Boolean> semaphore_signal = Symbol.of("semaphoreSignal", 2);
	private static final Symbol<Boolean> follows = Symbol.of("follows", 2);
	private static final Symbol<Boolean> requires = Symbol.of("requires", 2);
	private static final Symbol<Boolean> entry = Symbol.of("entry", 2);
	private static final Symbol<Boolean> exit = Symbol.of("exit", 2);
	private static final Symbol<Boolean> active = Symbol.of("active", 1);
	private static final Symbol<Boolean> target = Symbol.of("target", 2);
	private static final Symbol<Boolean> switchPositionPosition = Symbol.of("swihchPositionPosition", 2);
	private static final Symbol<Boolean> connectsTo = Symbol.of("connectsTo", 2);
	private static final Symbol<Boolean> monitors = Symbol.of("monitors", 2);
	private static final Symbol<Boolean> semaphores = Symbol.of("semaphores", 2);
	private static final Symbol<Boolean> currentPosition = Symbol.of("currentPosition", 2);
//	private static final Symbol<Enum> position = Symbol.of("Position", 1, Enum.class);
//	private static final Symbol<Enum> signal = Symbol.of("Signal", 1, Enum.class);



	private static final AnySymbolView railwayContainerView = new KeyOnlyView<>(railwayContainer);
	private static final AnySymbolView railwayElementView = new KeyOnlyView<>(railwayElement);
	private static final AnySymbolView regionView = new KeyOnlyView<>(region);
	private static final AnySymbolView semaphoreView = new KeyOnlyView<>(semaphore);
	private static final AnySymbolView routeView = new KeyOnlyView<>(route);
	private static final AnySymbolView switchPositionView = new KeyOnlyView<>(switchPosition);
	private static final AnySymbolView trackElementView = new KeyOnlyView<>(trackElement);
	private static final AnySymbolView sensorView = new KeyOnlyView<>(sensor);
	private static final AnySymbolView segmentView = new KeyOnlyView<>(segment);
	private static final AnySymbolView sswitchView = new KeyOnlyView<>(sswitch);
	private static final AnySymbolView routesView = new KeyOnlyView<>(routes);
	private static final AnySymbolView regionsView = new KeyOnlyView<>(regions);
	private static final AnySymbolView sensorsView = new KeyOnlyView<>(sensors);
	private static final AnySymbolView elementsView = new KeyOnlyView<>(elements);
	private static final AnySymbolView semaphore_signalView = new KeyOnlyView<>(semaphore_signal);
	private static final AnySymbolView followsView = new KeyOnlyView<>(follows);
	private static final AnySymbolView requiresView = new KeyOnlyView<>(requires);
	private static final AnySymbolView entryView = new KeyOnlyView<>(entry);
	private static final AnySymbolView exitView = new KeyOnlyView<>(exit);
	private static final AnySymbolView activeView = new KeyOnlyView<>(active);
	private static final AnySymbolView targetView = new KeyOnlyView<>(target);
	private static final AnySymbolView switchPositionPositionView = new KeyOnlyView<>(switchPositionPosition);
	private static final AnySymbolView connectsToView = new KeyOnlyView<>(connectsTo);
	private static final AnySymbolView monitorsView = new KeyOnlyView<>(monitors);
	private static final AnySymbolView semaphoresView = new KeyOnlyView<>(semaphores);
	private static final AnySymbolView currentPositionView = new KeyOnlyView<>(currentPosition);


	private static final RelationalQuery ConnectedSegmentsQuery = Query.of(
		"ConnectedSegmentsQuery",
		(builder, sensor, segment1, segment2, segment3, segment4, segment5, segment6) ->
			builder.clause(
				connectsToView.call(segment1, segment2),
				connectsToView.call(segment2, segment3),
				connectsToView.call(segment3, segment4),
				connectsToView.call(segment4, segment5),
				connectsToView.call(segment5, segment6),
				monitorsView.call(sensor, segment1),
				monitorsView.call(sensor, segment2),
				monitorsView.call(sensor, segment3),
				monitorsView.call(sensor, segment4),
				monitorsView.call(sensor, segment5),
				monitorsView.call(sensor, segment6)
			));

	@Override
	public void initialize() throws Exception {
		super.initialize();

		var store = ModelStore.builder()
			.symbols(railwayContainer, railwayElement, region, semaphore, route, switchPosition, trackElement, sensor, segment, sswitch,
				routes, regions, sensors, elements, semaphore_signal, follows, requires, entry, exit, active, target, switchPositionPosition,
				connectsTo, monitors, semaphores, currentPosition)
			.with(QueryInterpreterAdapter.builder()
				.queries(ConnectedSegmentsQuery))
			.build();

		model = store.createEmptyModel();
		engine = model.getAdapter(ModelQueryAdapter.class);
	}

	@Override
	public void read(String modelPath) throws Exception {

	}

	@Override
	public String getPostfix() {
		return "";
	}

	@Override
	public Number generateNewVertexId() throws Exception {
		return null;
	}

	public ModelQueryAdapter getEngine() {
		return engine;
	}
}
