package hu.bme.mit.trainbenchmark.generator.refinery.api;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.refinery.api.config.RefineryGeneratorConfig;
import tools.refinery.store.model.AnyInterpretation;
import tools.refinery.store.model.Model;
import tools.refinery.store.model.ModelStore;
import tools.refinery.store.representation.AnySymbol;
import tools.refinery.store.representation.Symbol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RefinerySerializer extends ModelSerializer<RefineryGeneratorConfig> {

	Map<String, AnySymbol> symbols;
	Map<String, AnyInterpretation> interpretations;
	protected ModelStore store = null;
	protected Model model = null;

	public RefinerySerializer(final RefineryGeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String syntax() {
		return "Refinery";
	}

	@Override
	public void initModel() throws IOException {
		symbols = new HashMap<>();
		interpretations = new HashMap<>();
		String[] types = new String[] {
			ModelConstants.TRACKELEMENT,
			ModelConstants.REGION,
			ModelConstants.ROUTE,
			ModelConstants.SEGMENT,
			ModelConstants.SENSOR,
			ModelConstants.SEMAPHORE,
			ModelConstants.SWITCH,
			ModelConstants.SWITCHPOSITION
		};
		for(String type : types) {
			initType(symbols, type);
		}

		String[] references = new String[] {
			ModelConstants.CONNECTS_TO,
			ModelConstants.ELEMENTS,
			ModelConstants.EXIT,
			ModelConstants.ENTRY,
			ModelConstants.FOLLOWS,
			ModelConstants.REQUIRES,
			ModelConstants.MONITORED_BY,
			ModelConstants.SEMAPHORES,
			ModelConstants.SENSORS,
			ModelConstants.TARGET
		};
		for(String reference : references) {
			initReference(symbols, reference);
		}


		symbols.put(ModelConstants.ACTIVE, Symbol.of(ModelConstants.ACTIVE, 1, Boolean.class, false));
		symbols.put(ModelConstants.LENGTH, Symbol.of(ModelConstants.LENGTH, 1, Integer.class, 0));
		symbols.put(ModelConstants.SIGNAL, Symbol.of(ModelConstants.SIGNAL, 1, Enum.class, null));
		symbols.put(ModelConstants.CURRENTPOSITION, Symbol.of(ModelConstants.CURRENTPOSITION, 1, Enum.class, null));
		symbols.put(ModelConstants.POSITION, Symbol.of(ModelConstants.POSITION, 1, Integer.class, 0));


		store = ModelStore.builder().symbols(symbols.values()).build();
		model = store.createEmptyModel();
		for(Map.Entry<String, AnySymbol> entry : symbols.entrySet()) {
			String type = entry.getKey();
			AnySymbol symbol = entry.getValue();
			interpretations.put(type, model.getInterpretation(symbol));
		}
	}

	protected void initType(Map<String, AnySymbol> symbols, String name) {
		symbols.put(name, Symbol.of(name, 1, Boolean.class, false));
	}
	protected void initReference(Map<String, AnySymbol> symbols, String name) {
		symbols.put(name, Symbol.of(name, 2, Boolean.class, false));
	}

	@Override
	public void persistModel() throws Exception {
		writer.close();
	}

	@Override
	public Object createVertex(int id, String type, Map<String, ?> attributes, Map<String, Object> outgoingEdges, Map<String, Object> incomingEdges) throws IOException {
		write(type + "(n" + id + ").");
		for(Map.Entry<String, Object> entry : outgoingEdges.entrySet()) {
			write(entry.getKey() + "(n" + id + ", n" + entry.getValue() + ").");
		}
		for(Map.Entry<String, Object> entry : incomingEdges.entrySet()) {
			write(entry.getKey() + "(n" + entry.getValue() + ", n" + id + ").");
		}
		return id;
	}

	@Override
	public void createEdge(String label, Object from, Object to) throws IOException {
		write(label + "(n" + from + ", n" + to + ").");
	}
}
