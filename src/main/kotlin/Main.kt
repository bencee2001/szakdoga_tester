import Data.Companion.TEST_DATA_1
import Data.Companion.TEST_DATA_2
import Data.Companion.TEST_DATA_3
import Data.Companion.TEST_DATA_4
import Data.Companion.TEST_DATA_5
import Data.Companion.TEST_DATA_6
import Data.Companion.TEST_DATA_71
import Data.Companion.TEST_DATA_72
import Data.Companion.TEST_DATA_8
import Data.Companion.TEST_DATA_9
import kotlinx.coroutines.coroutineScope
import util.LogFlags

suspend fun main() {
    coroutineScope {

        LogFlags.PARK_READ_LOG = true
        LogFlags.UNIT_READ_LOG = true

        val test1 = SimulationTester(TEST_DATA_1).startTest("UnitLog1", "ParkLog1")
        //val test2 = SimulationTester(TEST_DATA_72).startTest("UnitLog2", "ParkLog2")

    }
}