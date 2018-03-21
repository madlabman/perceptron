import AI.Point
import com.csvreader.CsvReader

class DataReader {
    companion object {
        fun readCSV(filename: String): ArrayList<Point> {
            val output = ArrayList<Point>()
            val reader = CsvReader(filename).apply {
                trimWhitespace = true
                skipEmptyRecords = true
                delimiter = ';'
                readHeaders()
            }

            while (reader.readRecord()) {
                //println("(${reader[0]};${reader[1]}) - ${reader[2]}")
                output.add(Point(reader[0].toDouble() / 100, reader[1].toDouble() / 100, reader[2] == "1"))
            }

            return output;
        }
    }
}