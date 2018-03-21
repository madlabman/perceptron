package Functions

import Functions.TransferFunction

class SigmoidFunction : TransferFunction {
    override fun exec(arg: Double): Double {
        return 1 / (1 + Math.pow(Math.E, -arg))
    }

    override fun execDerivative(arg: Double): Double {
        return exec(arg) * (1 - exec(arg))
    }
}