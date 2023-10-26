import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class OrdersMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        //Split values
        String[] values = value.toString().split(",");
        //make new variable
        Orders newOrder = new Orders();
        int LocalOrderID;
        int LocalOrderDate;
        int LocalOrderTotalAmount;
        int LocalOrderQty;
        int[] listTotalAmounts = new int[0];
        int[] listQty = new int[0];
        int averageMeanTotal = 0;
        int averageMeanQty = 0;


        //try logic
        try {

            LocalOrderID = Integer.parseInt(values[0].substring(0,1));
            LocalOrderDate =  Integer.parseInt(values[1].substring(0,9));
            LocalOrderTotalAmount = Integer.parseInt(values[2].substring(0,2));
            LocalOrderQty = Integer.parseInt(values[2].substring(0,2));

            for(int i = 0; i <= values.length; i++) {
                listTotalAmounts[i] += Integer.parseInt(values[2].substring(0,2));
                listQty[i] += Integer.parseInt(values[2].substring(0,2));
                averageMeanTotal += listTotalAmounts[i];
                averageMeanQty += listQty[i];
            }
            averageMeanTotal = averageMeanTotal / averageMeanQty;


        }
        catch (Exception e){
            LocalOrderID = 0;
            LocalOrderDate = 9999-99-99;
            LocalOrderTotalAmount = 000;
            LocalOrderQty = 0;
            averageMeanTotal = 0;
        }
        context.write( new Integer(LocalOrderDate), new Integer(averageMeanTotal));
    }
}
