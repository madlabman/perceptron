import AI.Perceptron
import AI.Point

class App {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello! It's perceptron demonstration app"); println();
            val dataset = DataReader.readCSV("/Users/che/Downloads/PerceptronTrue/resources/perceptron_data.csv")
            val prc = Perceptron(dataset)
            /*
            for (p in dataset) {
                val test = prc.classify(p)
                println("(${p.x};${p.y})[${p.isOnPlane}] => [${test[0]};${test[1]}]")
            }
            */
            var p = Point(0.0, 40.0, false)
            println("(0;40) => ${prc.classifyBoolean(p)}")
            p = Point(25.0, -40.0, false)
            println("(25;-40) => ${prc.classifyBoolean(p)}")
            p = Point(-25.0, -20.0, false)
            println("(-25;-20) => ${prc.classifyBoolean(p)}")
            p = Point(-40.0, 120.0, false)
            println("(-40;120) => ${prc.classifyBoolean(p)}")
        }
    }
}