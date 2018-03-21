package AI

import Functions.TransferFunction
import java.util.Random

class Neuron(val inputsCount: Int, val transferFunction: TransferFunction) {

    // Weights + bias neuron value
    val weights = Array<Double>(inputsCount, { Random().nextDouble() })

    // Input and output
    var input = 0.0
    var out = 0.0

    // Computing in error correction process
    var delta = 0.0

    /**
     * Return neuron predict
     * @param signals Input values
     */
    fun process(signals: Array<Double>): Double {
        if (signals.size != inputsCount) throw Exception("Inputs count mismatch")

        input = 0.0
        signals.forEachIndexed { i, s -> input += s * weights[i] }

        out = transferFunction.exec(input)
        return out
    }

}