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
        //val test2 = SimulationTester(TEST_DATA_2).startTest("UnitLog2", "ParkLog2")
        //val test3 = SimulationTester(TEST_DATA_3).startTest("UnitLog3", "ParkLog3")
        //val test4 = SimulationTester(TEST_DATA_4).startTest("UnitLog4", "ParkLog4")
        //val test5 = SimulationTester(TEST_DATA_5).startTest("UnitLog5", "ParkLog5")
        //val test6 = SimulationTester(TEST_DATA_6).startTest("UnitLog6", "ParkLog6")
        //val test71 = SimulationTester(TEST_DATA_71).startTest("UnitLog71", "ParkLog71")
        //val test72 = SimulationTester(TEST_DATA_72).startTest("UnitLog72", "ParkLog72")
        //val test8 = SimulationTester(TEST_DATA_8).startTest("UnitLog8", "ParkLog8")
        //val test9 = SimulationTester(TEST_DATA_9).startTest("UnitLog9", "ParkLog9")



        //val test2 = SimulationTester(TEST_DATA_72).startTest("UnitLog2", "ParkLog2")

    }
}