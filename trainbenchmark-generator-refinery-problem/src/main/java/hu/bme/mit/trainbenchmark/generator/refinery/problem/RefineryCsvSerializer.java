package hu.bme.mit.trainbenchmark.generator.refinery.problem;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.refinery.problem.config.RefineryCsvGeneratorConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RefineryCsvSerializer extends ModelSerializer<RefineryCsvGeneratorConfig> {

	protected Map<String, BufferedWriter> writers = new HashMap<>();

	public RefineryCsvSerializer(final RefineryCsvGeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String syntax() {
		return "RefineryCsv";
	}

	public void write(String file, final String s) throws IOException {
		BufferedWriter writer = writers.get(file);
		if (writer != null) {
			writer.write(s + "\n");
		}
	}

	@Override
	public void initModel() throws IOException {
		final String csvBasePath = gc.getConfigBase().getModelPathWithoutExtension();

		var regionClassFile = new File(csvBasePath + "-Region" + ".csv");
		var semaphoreClassFile = new File(csvBasePath + "-Semaphore" + ".csv");
		var routeClassFile = new File(csvBasePath + "-Route" + ".csv");
		var switchPositionClassFile = new File(csvBasePath + "-SwitchPosition" + ".csv");
		var sensorClassFile = new File(csvBasePath + "-Sensor" + ".csv");
		var segmentClassFile = new File(csvBasePath + "-Segment" + ".csv");
		var switchClassFile = new File(csvBasePath + "-Switch" + ".csv");
		var sensorsFile = new File(csvBasePath + "-sensors" + ".csv");
		var elementsFile = new File(csvBasePath + "-elements" + ".csv");
		var signalFile = new File(csvBasePath + "-signal" + ".csv");
		var followsFile = new File(csvBasePath + "-follows" + ".csv");
		var requiresFile = new File(csvBasePath + "-requires" + ".csv");
		var entryFile = new File(csvBasePath + "-entry" + ".csv");
		var exitFile = new File(csvBasePath + "-exit" + ".csv");
		var targetFile = new File(csvBasePath + "-target" + ".csv");
		var positionFile = new File(csvBasePath + "-position" + ".csv");
		var monitoredByFile = new File(csvBasePath + "-monitoredBy" + ".csv");
		var connectsToFile = new File(csvBasePath + "-connectsTo" + ".csv");
		var semaphoresFile = new File(csvBasePath + "-semaphores" + ".csv");
		var currentPositionFile = new File(csvBasePath + "-currentPosition" + ".csv");
		var lengthFile = new File(csvBasePath + "-length" + ".csv");
		var activeFile = new File(csvBasePath + "-active" + ".csv");

		Map<String, File> csvFiles = new HashMap<>();
//		csvFiles.put("RailwayContainer", railwayContainerClassFile);
		csvFiles.put("Region", regionClassFile);
		csvFiles.put("Semaphore", semaphoreClassFile);
		csvFiles.put("Route", routeClassFile);
		csvFiles.put("SwitchPosition", switchPositionClassFile);
//		csvFiles.put("TrackElement", trackElementClassFile);
		csvFiles.put("Sensor", sensorClassFile);
		csvFiles.put("Segment", segmentClassFile);
		csvFiles.put("Switch", switchClassFile);
//		csvFiles.put("routes", routesFile);
//		csvFiles.put("regions", regionsFile);
		csvFiles.put("sensors", sensorsFile);
		csvFiles.put("elements", elementsFile);
		csvFiles.put("signal", signalFile);
		csvFiles.put("follows", followsFile);
		csvFiles.put("requires", requiresFile);
		csvFiles.put("entry", entryFile);
		csvFiles.put("exit", exitFile);
//		csvFiles.put("route", routeFile);
		csvFiles.put("target", targetFile);
		csvFiles.put("position", positionFile);
		csvFiles.put("monitoredBy", monitoredByFile);
		csvFiles.put("connectsTo", connectsToFile);
//		csvFiles.put("monitors", monitorsFile);
		csvFiles.put("semaphores", semaphoresFile);
//		csvFiles.put("positions", positionsFile);
		csvFiles.put("currentPosition", currentPositionFile);
//		csvFiles.put("straight", straightFile);
//		csvFiles.put("diverging", divergingFile);
		csvFiles.put("length", lengthFile);
		csvFiles.put("active", activeFile);


		System.out.println("Writing problem to: " + csvBasePath);

		for (Map.Entry<String, File> entry : csvFiles.entrySet()) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(entry.getValue()));
			this.writers.put(entry.getKey(), bw);
		}

	}

	@Override
	public void persistModel() throws Exception {
		for (Map.Entry<String, BufferedWriter> entry : writers.entrySet()) {
			entry.getValue().close();
		}
	}

	@Override
	public Object createVertex(int id, String type, Map<String, ?> attributes, Map<String, Object> outgoingEdges, Map<String, Object> incomingEdges) throws IOException {
		write(type, String.valueOf(id));
		for(Map.Entry<String, ?> entry : attributes.entrySet()) {
			var key = entry.getKey();
			if (entry.getValue() == null) {
				System.out.println("Null attribute: " + key + " for vertex: " + id);
				continue;
			}
			if (key.equals("position") || key.equals("currentPosition")) {
				write(entry.getKey(), id + ",Position::" + entry.getValue());
			} else if (key.equals("signal")) {
				write(entry.getKey(), id + ",Signal::" + entry.getValue());
			} else {
				write(entry.getKey(), id + "," + entry.getValue());
			}
		}
		for(Map.Entry<String, Object> entry : outgoingEdges.entrySet()) {
			if (entry.getValue() == null) {
				System.out.println("Null outgoing edge: " + entry.getKey() + " for vertex: " + id);
				continue;
			}
			write(entry.getKey(), id + "," + entry.getValue());
		}
		for(Map.Entry<String, Object> entry : incomingEdges.entrySet()) {
			if (entry.getValue() == null) {
				System.out.println("Null incoming edge: " + entry.getKey() + " for vertex: " + id);
				continue;
			}
			write(entry.getKey(), entry.getValue() + "," + id);
		}
		return id;
	}

	@Override
	public void createEdge(String label, Object from, Object to) throws IOException {
		if (from == null || to == null) {
			System.out.println("Null edge: " + label + " from: " + from + " to: " + to);
			return;
		}
		write(label, from + "," + to);
	}
}
