import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author Dhrumil Vora
 *
 */

 public class avgTaxiTimeMapper extends Mapper<LongWritable,Text,Text,Text>{

   public void map(LongWritable key, Text value, Context c) throws IOException,InterruptedException{
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
     if(key.get() != 0) {
       String[] file = value.toString().split(",");
       String origin = file[16];
       String TaxiOut = file[20];
       String dest = file[17];
       String TaxiIn=file[19];
       c.write(new Text(dest+"\tIN"),new Text(TaxiIn+""));
       c.write(new Text(origin+"\tOUT"),new Text(TaxiOut+""));

      }else{
        return;
      }
    }
  }
