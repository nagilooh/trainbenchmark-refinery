package hu.bme.mit.trainbenchmark.generator.refinery;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.refinery.config.RefineryGeneratorConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class RefinerySerializer extends ModelSerializer<RefineryGeneratorConfig> {

	protected BufferedWriter writer;

	public RefinerySerializer(final RefineryGeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String syntax() {
		return "Refinery";
	}

	public void write(final String s) throws IOException {
		writer.write(s + "\n");
	}

	@Override
	public void initModel() throws IOException {

		final String problemPath = gc.getConfigBase().getModelPathWithoutExtension() + ".problem";
		final File cypherFile = new File(problemPath);
		writer = new BufferedWriter(new FileWriter(cypherFile));
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
