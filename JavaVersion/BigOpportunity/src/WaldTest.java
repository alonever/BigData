import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WaldTest {

	public static class MinMaxCountMapper extends
			Mapper<Object, Text, Text, WaldTuple> {
		private WaldTuple outTuple = new WaldTuple();
		private Text stock = new Text();
		static int LastIndicator = -1;

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] line = value.toString().split(
					",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			stock.set("IBM");
			try {

				int change = Math.round(Float.parseFloat(line[2]));
				int indicator = change > 0 ? 1 : 0;
				outTuple.setStockName("IBM");
				outTuple.setIndicator(indicator);
				// If it is the start of a run, set the run number as 1
				// Otherwise, set the run number as 0
				if (LastIndicator == -1 || LastIndicator != indicator)
					outTuple.setNumber(1);
				else
					outTuple.setNumber(0);
				LastIndicator = indicator;
				context.write(stock, outTuple);
			} catch (NumberFormatException e) {
				return;
			}
		}
	}

	public static class MinMaxCountCombiner extends
			Reducer<Text, WaldTuple, Text, WaldTuple> {
		private WaldTuple result = new WaldTuple();

		public void reduce(Text key, Iterable<WaldTuple> values, Context context)
				throws IOException, InterruptedException {
			SortedMapWritable output = new SortedMapWritable();
			for (WaldTuple v : values) {
				LongWritable count = (LongWritable) outValue
						.get(entry.getKey());
				if (count != null) {
					count.set(count.get()
							+ ((LongWritable) entry.getValue()).get());
				} else {
					outValue.put(entry.getKey(), new LongWritable(
							((LongWritable) entry.getValue()).get()));
				}

			}
			context.write(key, outValue);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "WaldTest");

		job.setJarByClass(WaldTest.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(WaldTuple.class);

		job.setMapperClass(WaldTestMapper.class);
		job.setCombinerClass(WaldTestCombiner.class);
		job.setReducerClass(WaldTestReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
