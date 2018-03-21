package Functions

/**
 * Function th(x)
 */
class HyperbolicFunction : TransferFunction {

    override fun exec(arg: Double): Double {
        val pow = Math.pow(Math.E, 2 * arg)
        return (pow - 1) / (pow + 1)
    }

    override fun execDerivative(arg: Double): Double {
        return 1 - Math.pow(exec(arg), 2.0)
    }
}