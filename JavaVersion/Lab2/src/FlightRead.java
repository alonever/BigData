import java.io.*;
import java.util.Arrays;
import java.util.Hashtable;

public class FlightRead {

	public static void main(String[] args) {
		for (int month = 1; month < 13; month++) {
			// The name of the file to open.
			String fileName = "../FlightData/On_Time_On_Time_Performance_2012_"
					+ Integer.toString(month) + ".csv";
			String outFileName = "AllFlights.txt";
			String line = null;
			Hashtable<String, Integer> columnHash = new Hashtable<String, Integer>();
			String[] columnNames = new String[] { "\"Year\"", "\"Quarter\"",
					"\"Month\"", "\"DayofMonth\"", "\"DayOfWeek\"",
					"\"UniqueCarrier\"", "\"Origin\"", "\"OriginCityName\"",
					"\"OriginState\"", "\"Dest\"", "\"DestCityName\"",
					"\"DestState\"", "\"CRSDepTime\"", "\"DepTime\"",
					"\"DepDelay\"", "\"DepDelayMinutes\"", "\"DepDel15\"",
					"\"DepartureDelayGroups\"", "\"TaxiOut\"", "\"TaxiIn\"",
					"\"ArrDelay\"", "\"ArrDelayMinutes\"", "\"ArrDel15\"",
					"\"Cancelled\"", "\"ActualElapsedTime\"", "\"AirTime\"",
					"\"Distance\"" };
			for (int i = 0; i < columnNames.length; i++) {
				columnHash.put(columnNames[i], -1);
			}

			try {
				// FileReader reads text files in the default encoding.
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				FileWriter fileWriter = new FileWriter(outFileName, month != 1);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				// Locate the desired headings' indexes
				String[] titleTemp = bufferedReader.readLine()
						.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				int[] columnIndex = new int[columnHash.size()];
				for (int i = 0, j = 0; i < titleTemp.length; i++) {
					if (columnHash.containsKey(titleTemp[i])) {
						columnIndex[j] = i;
						j++;
					}
				}
				int dataLength = columnIndex.length;

				while ((line = bufferedReader.readLine()) != null) {
					String[] data = line
							.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					String[] dataclean = new String[dataLength];
					for (int i = 0; i < dataLength; i++) {
						dataclean[i] = data[columnIndex[i]];
					}

					String datacleanstring = Arrays.toString(dataclean);
					datacleanstring = datacleanstring.replaceAll("\\[", "")
							.replaceAll("\\]", "");
					bufferedWriter.write(datacleanstring);
					bufferedWriter.newLine();
				}
				bufferedReader.close();
				bufferedWriter.close();
			} catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + fileName + "'");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
