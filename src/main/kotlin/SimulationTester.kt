import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kalasim.monitors.NumericStatisticMonitor
import park.ParkPower
import simulation.Simulation

class SimulationTester(
    private val data: SimTestData,
    randomSeed: Int = 42
) {

    private val simulation: Simulation
    private var targetValue: Double = 0.0
    private var currentValue: Double = 0.0
    private val gradiantValue: Double

    init {
        simulation = Simulation(data.simData, randomSeed, true, data.config)
        val maxSimOutput = simulation.getMaxOutputByParkId().values.sumOf { it }
        gradiantValue = maxSimOutput * data.gradiant
    }


    private fun setCurrentValue() {
        if(targetValue > currentValue){
            val newValue = currentValue + gradiantValue
            currentValue = if(newValue > targetValue)
                targetValue
            else
                newValue
        } else if (targetValue < currentValue){
            val newValue = currentValue - gradiantValue
            currentValue = if(newValue < targetValue)
                targetValue
            else
                newValue
        }
    }

    suspend fun startTest( unitLogFileName: String? = null, parkLogFileName: String? = null , savePathName: String? = null):Map<Int,List<ParkPower>>{
        return coroutineScope {
            var i = 0
            val powersByPowerPlantId = mutableMapOf<Int, MutableList<ParkPower>>()
            launch { simulation.runWithSave(data.time, unitLogFileName, parkLogFileName, savePathName) }
            while (i < data.time) {
                if(i % 2 == 0) {
                    val powers = simulation.read()
                    powers.forEach {
                        powersByPowerPlantId[it.powerPlantId]?.add(it) ?: powersByPowerPlantId.put(
                            it.powerPlantId,
                            mutableListOf(it)
                        )
                    }
                }
                data.commands[i]?.let {
                    targetValue = it
                }
                setCurrentValue()
                val parkOutputById = simulation.getMaxOutputByParkId()
                val allOutput = parkOutputById.values.sumOf { it }
                val avg = currentValue.div(allOutput)
                val commandByPowerPlantId =
                    parkOutputById.keys.associateWith { parkOutputById[it]!!.times(avg).toInt() }
                simulation.command(commandByPowerPlantId)
                delay(1_000)
                i += 1
            }
            return@coroutineScope powersByPowerPlantId
        }
    }
}