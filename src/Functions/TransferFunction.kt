package Functions

interface TransferFunction {
	fun exec(arg: Double): Double
	fun execDerivative(arg: Double): Double
}