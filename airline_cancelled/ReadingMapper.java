import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author Dhrumil Vora
 *
 */

public class ReadingMapper extends Mapper<LongWritable, Text,Text,IntWritable>{

    public void map(LongWritable Key,Text value, Context context) throws IOException, InterruptedException{
      /*
		 * Format in CSV
			Year	0
			Month	1
			DayofMonth	2
			DayOfWeek	3
			DepTime	4
			CRSDepTime	5
			ArrTime	6
			CRSArrTime	7
			UniqueCarrier	8
			FlightNum	9
			TailNum	10
			ActualElapsedTime	11
			CRSElapsedTime	12
			AirTime	13
			ArrDelay	14
			DepDelay	15
			Origin	16
			Dest	17
			Distance	18
			TaxiIn	19
			TaxiOut	20
			Cancelled	21
			CancellationCode	22
			Diverted	23
			CarrierDelay	24
			WeatherDelay	25
			NASDelay	26
			SecurityDelay	27
			LateAircraftDelay	28
		 * */
      if (Key.get!=0) {


      String[] file = value.toString().split(",");
      Integer year = Integer.parseInt(file[0]);
      Integer cancelled = Integer.parseInt(file[21]);

      //check if cancelled
      if (cancelled == 1) {
        // writing the <key,value> as <CancellationCode,Year>
        context.write(
              new Text(file[22].toString()),
              new IntWritable(year)
        );
      }else{
        return;
      }
    }
}
