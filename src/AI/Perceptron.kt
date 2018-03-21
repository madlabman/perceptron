package AI

import Functions.HyperbolicFunction
import Functions.LinearFunction

class Perceptron(val trainSet: ArrayList<Point>) {

    val learnSpeed = 0.0001
    val layers = ArrayList<Layer>()
    val transferFunction = HyperbolicFunction()
    val maxIterationCount = 10000

    init {
        // Creating net of layers
        layers.add(Layer(2, 1, LinearFunction())) // input layer
        layers[0].neurons.forEach { neuron ->
            for (i in 0 until neuron.inputsCount) {
                neuron.weights[i] = 1.0 // Output equals to input
            }
        }
        layers.add(Layer(3, 2, transferFunction)) // hidden layer
        layers.add(Layer(2, 3, transferFunction)) // output layer

        // Training
        println("Starting training..."); println()

        var i = 0
        do {
            train(); i++
            if (i > maxIterationCount) throw Exception("Maximum iterations count exceed")
        } while (!trainSetRecognizedSuccessful())

        println("Iterations: $i");       println()
        println("Trained!");             println()
    }

    private fun train() {
        trainSet.forEach { p ->
            classify(p)
            backpropogation(p)
        }
    }

    private fun backpropogation(p: Point) {
        val expectedOut = pointToOutArray(p)
        layers.asReversed().forEachIndexed { i, layer ->
            if (i == layers.size - 1) return@forEachIndexed
            layer.neurons.forEachIndexed { k, neuron ->
                var errorK = 0.0
                if (i == 0) {   // Calculate output layer deltas
                    errorK = expectedOut[k] - neuron.out
                } else {        // Calculate hidden layer deltas
                    layers.asReversed()[i - 1].neurons.forEachIndexed { j, connectedNeuron ->
                        errorK += connectedNeuron.weights[k] * connectedNeuron.delta
                    }
                }
                neuron.delta = neuron.transferFunction.execDerivative(neuron.input) * errorK
                // Update weight
                neuron.weights.forEachIndexed { j, _ ->
                    neuron.weights[j] += layers.asReversed()[i + 1].neurons[j].out * neuron.delta * learnSpeed
                }
            }
        }
    }

    fun classify(p: Point): Array<Double> {
        var input = pointToInputArray(p)
        for (i in 1 until layers.size) {  // skip first layer
            val layer = layers[i]
            val out = Array<Double>(layer.size, { 0.0 })
            layer.neurons.forEachIndexed { index, neuron ->
                out[index] = neuron.process(input)
            }
            input = out
        }
        //println("${input[0]};${input[1]}")
        return input
    }

    fun classifyBoolean(p: Point): Boolean {
        return indexOfMax(classify(p)) == 0
    }

    private fun pointToInputArray(p: Point): Array<Double> {
        val out = Array<Double>(2, { 0.0 })
        out[0] = p.x
        out[1] = p.y
        return out
    }

    private fun pointToOutArray(p: Point): Array<Double> {
        val out = Array<Double>(2, { 0.0 })
        out[0] = if (p.isOnPlane) 1.0 else -1.0
        out[1] = -out[0]
        return out
    }

    private fun trainSetRecognizedSuccessful(): Boolean {
        trainSet.forEach { p ->
            val i = indexOfMax(classify(p))
            if ( i == 0 && !p.isOnPlane ||
                 i == 1 && p.isOnPlane) {
                //println(classify(p)[0])
                //println("$i + ${p.isOnPlane} = false")
                return false
            }
        }

        return true
    }

    fun indexOfMax(a: Array<Double>): Int? {
        return a.withIndex().maxBy { it.value }?.index
    }
}