package airline_delay;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WritingReducer extends Reducer<Text,IntWritable,Text,Text>{

  public void reduce(Text CancellationCode , Iterable<IntWritable> year, Context c) throws IOException,InterruptedException{
    int total = 0;
    // made year as Iterable object
    Iterable<IntWritable> iter = year.iterator();

    while(iter.hasNext()){
      total++;
      iter.next();
    }
    // writing the <key,value> as <CancellationCode,totalCount>
    c.write(CancellationCode,new Text(total + ""));
  }
}
