import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MinMaxCountLab {

	public static class MinMaxCountMapper extends
			Mapper<Object, Text, Text, MinMaxCountTuple> {
		private Text month = new Text();
		private MinMaxCountTuple outTuple = new MinMaxCountTuple();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] line = value.toString().split(
					",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			month.set(line[2]);
			try {
				int delay = Math.round(Float.parseFloat(line[20]));
				outTuple.setCount(1);
				outTuple.setMax(delay);
				outTuple.setMin(delay);
				context.write(month, outTuple);
			} catch (NumberFormatException e) {
				return;
			}
		}
	}

	public static class MinMaxCountReducer extends
			Reducer<Text, MinMaxCountTuple, Text, MinMaxCountTuple> {
		private MinMaxCountTuple result = new MinMaxCountTuple();

		public void reduce(Text key, Iterable<MinMaxCountTuple> values,
				Context context) throws IOException, InterruptedException {
			result.setMin(Integer.MAX_VALUE);
			result.setMax(Integer.MIN_VALUE);
			result.setCount(0);
			int sum = 0;

			for (MinMaxCountTuple val : values) {
				if (val.getMin() < result.getMin()) {
					result.setMin(val.getMin());
				}
				if (val.getMax() > result.getMax()) {
					result.setMax(val.getMax());
				}
				sum += val.getCount();
			}

			result.setCount(sum);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "MinMaxCount");

		job.setJarByClass(MinMaxCountLab.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(MinMaxCountTuple.class);

		job.setMapperClass(MinMaxCountMapper.class);
		job.setReducerClass(MinMaxCountReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
