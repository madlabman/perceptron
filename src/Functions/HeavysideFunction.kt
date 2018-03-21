package Functions

class HeavysideFunction : TransferFunction {
	override fun exec(arg: Double): Double {
		return if (arg > 0.5) 1.0 else 0.0
	}
	
	override fun execDerivative(arg: Double): Double {
		return 1.0
	}
}