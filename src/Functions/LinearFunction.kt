package Functions

class LinearFunction : TransferFunction {
    override fun exec(arg: Double): Double {
        return arg
    }

    override fun execDerivative(arg: Double): Double {
        return 1.0
    }
}