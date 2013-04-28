import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class MinMaxCountTuple implements Writable {
	private int min = Integer.MAX_VALUE;
	private int max = Integer.MIN_VALUE;
	private long count = 0;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void readFields(DataInput in) throws IOException {
		min = in.readInt();
		max = in.readInt();
		count = in.readLong();
	}

	public void write(DataOutput out) throws IOException {
		out.writeInt(min);
		out.writeInt(max);
		out.writeLong(count);
	}

	public String toString() {
		return min + "\t" + max + "\t" + count;
	}
}
