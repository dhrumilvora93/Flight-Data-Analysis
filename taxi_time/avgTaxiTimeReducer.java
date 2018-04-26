import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @author Dhrumil Vora
 *
 */
public class avgTaxiTimeReducer extends Reducer<Text,Text,Text,Text>{

  public void reduce(Text airports, Iterable<Text> time, Context c) throws IOException,InterruptedException{
    int total=0;
    int taxi_avg;
    int any = 0;
    // made year as Iterable object
    Iterator<Text> iter = time.iterator();

    while(iter.hasNext()){
      String val = iter.next().toString();
      System.out.println(val);

      if(!"NA".equalsIgnoreCase(val)){
        any+=Integer.parseInt(val);

      total++;
      }
    // writing the <key,value> as <CancellationCode,totalCount>
    //c.write(airports,new Text("IN\t"+in));
    //c.write(airports,new Text("OUT\t"+out));
    }
    if(total!=0){
      taxi_avg= any/total;
    }else{
      taxi_avg= 0;
    }

    c.write(airports,new Text(""+taxi_avg));

  }
}
