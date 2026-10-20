package hu.bme.mit.trainbenchmark.generator.refinery.problem;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.refinery.problem.config.RefineryProblemGeneratorConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class RefineryProblemSerializer extends ModelSerializer<RefineryProblemGeneratorConfig> {

	protected BufferedWriter writer;

	public RefineryProblemSerializer(final RefineryProblemGeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String syntax() {
		return "RefineryProblem";
	}

	public void write(final String s) throws IOException {
		writer.write(s + "\n");
	}

	@Override
	public void initModel() throws IOException {

		final String problemPath = gc.getConfigBase().getModelPathWithoutExtension() + ".problem";
		final File cypherFile = new File(problemPath);
		System.out.println("Writing problem to: " + cypherFile.getAbsolutePath());
		writer = new BufferedWriter(new FileWriter(cypherFile));
	}

	@Override
	public void persistModel() throws Exception {
		writer.close();
	}

	@Override
	public Object createVertex(int id, String type, Map<String, ?> attributes, Map<String, Object> outgoingEdges, Map<String, Object> incomingEdges) throws IOException {
		write(type + "(n" + id + ").");
		for(Map.Entry<String, ?> entry : attributes.entrySet()) {
			var key = entry.getKey();
			if (key.equals("length") || key.equals("active")) {
				write(entry.getKey() + "(n" + id + "): " + entry.getValue() + ".");
			} else if (key.equals("position") || key.equals("currentPosition")) {
				write(entry.getKey() + "(n" + id + ", Position::" + entry.getValue() + ").");
			} else if (key.equals("signal")) {
				write(entry.getKey() + "(n" + id + ", Signal::" + entry.getValue() + ").");
			} else {
				write(entry.getKey() + "(n" + id + ", " + entry.getValue() + ").");
			}
		}
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
