package AI

import Functions.TransferFunction

class Layer(val size: Int, val prevSize: Int, val transferFunction: TransferFunction) {
    val neurons = Array<Neuron>(size, { Neuron(prevSize, transferFunction) })
}