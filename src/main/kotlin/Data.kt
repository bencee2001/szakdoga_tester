import configdsl.ConfigDSL
import configdsl.config
import configdsl.models.DslEndErrorTask
import configdsl.models.DslStartErrorTask
import model.*
import model.types.BatteryType
import model.types.EngineType
import model.types.InverterType
import model.types.LoadbankType
import simulation.SimulationData
import units.UnitType
import kotlin.time.Duration.Companion.minutes


class SimTestData(
    val simData: SimulationData,
    val commands: Map<Int, Double>,
    val time: Int,
    val gradiant: Double,
    val config: ConfigDSL? = null
)


class Data {
    companion object{
        //----------Test1-Inverter--------------------------------
        val oneParkInverterTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test1", 50_000.0)
            ),
            inverters = mapOf(
                1 to InverterData(1, 1, 12_500.0, true, InverterType.HUAWEI),
                2 to InverterData(2, 1, 12_500.0, true, InverterType.TEST),
                3 to InverterData(3, 1, 12_500.0, true, InverterType.FRONIUS),
                4 to InverterData(4, 1, 12_500.0, true, InverterType.KACO)
            )
        )
        val oneParkInverterCommands = mapOf(
            0 to 30_000.0,
            30 to 0.0,
            45 to 50_000.0
        )
        val oneParkInverterTime = 60
        val TEST_DATA_1 = SimTestData(
            oneParkInverterTest,
            oneParkInverterCommands,
            oneParkInverterTime,
            0.4,
        )
        //----------Test2-Engine-------------------------------
        val oneParkEngineTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test2", 50_000.0)
            ),
            engines = mapOf(
                1 to EngineData(1, 1, 10_000.0,0.3,5, EngineType.TEST),
                2 to EngineData(2, 1, 15_000.0,0.4,10, EngineType.TEST),
                3 to EngineData(3, 1, 20_000.0,0.2,6, EngineType.TEST),
                4 to EngineData(4, 1, 5_000.0,0.5, 7, EngineType.TEST)
            )
        )
        val oneParkEngineCommands = mapOf(
            0 to 3_000.0,
            2 to 0.0,
            12 to 6_000.0,
            30 to 20_000.0,
            45 to 40_000.0
        )
        val oneParkEngineTime = 60
        val TEST_DATA_2 = SimTestData(
            oneParkEngineTest,
            oneParkEngineCommands,
            oneParkEngineTime,
            0.4,
        )
        //----------Test3-Loadbank-------------------------------
        val oneParkLoadbankTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test3", 20_000.0)
            ),
            engines = mapOf(
                1 to EngineData(1, 1, 20_000.0,0.0,10, EngineType.TEST),
            ),
            loadbanks = mapOf(
                1 to LoadbankData(1, 1, 1000, 100, LoadbankType.TEST),
                2 to LoadbankData(2, 1, 500, 50, LoadbankType.TEST),
                3 to LoadbankData(3, 1, 2000, 100, LoadbankType.TEST)

            )
        )
        val oneParkLoadbankConfig = config{
            addTypeConfig(UnitType.ENGINE, EngineType.TEST){
                POWER_CONTROL_REACTION_TIME = 30
            }
        }
        val oneParkLoadbankCommands = mapOf(
            0 to 20_000.0,
            15 to 0.0
        )
        val oneParkLoadbankTime = 60
        val TEST_DATA_3 = SimTestData(
            oneParkLoadbankTest,
            oneParkLoadbankCommands,
            oneParkLoadbankTime,
            1.0,
            oneParkLoadbankConfig
        )
        //----------Test4-Battery-------------------------------
        val oneParkBatteryTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test4", 20_000.0)
            ),
            engines = mapOf(
                1 to EngineData(1, 1, 20_000.0,0.0,10, EngineType.TEST),
            ),
            batteries = mapOf(
                1 to BatteryData(1, 1, 6_000.0, BatteryType.TEST),
                2 to BatteryData(2, 1, 4_000.0, BatteryType.TEST)
            )
        )
        val oneParkBatteryCommands = mapOf(
            0 to 10_000.0,
            20 to 20_000.0
        )
        val oneParkBatteryConfig = config{
            addTypeConfig(UnitType.ENGINE, EngineType.TEST){
                POWER_CONTROL_REACTION_TIME = 30
            }
        }
        val oneParkBatteryTime = 60
        val TEST_DATA_4 = SimTestData(
            oneParkBatteryTest,
            oneParkBatteryCommands,
            oneParkBatteryTime,
            0.4,
            oneParkBatteryConfig
        )
        //----------Test5-Error-------------------------------
        val oneParkErrorTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test5", 50_000.0)
            ),
            inverters = mapOf(
                1 to InverterData(1, 1, 12_500.0, true, InverterType.HUAWEI),
            )
        )
        val errorConfig = config {
            addUnitConfig(UnitType.INVERTER, 1){
                addTask {
                    listOf(
                        DslStartErrorTask(10),
                        DslEndErrorTask(30)
                    )
                }
            }
        }
        val oneParkErrorCommands = mapOf(
            0 to 10_000.0,
            15 to 5_000.0
        )
        val oneParkErrorTime = 40
        val TEST_DATA_5 = SimTestData(
            oneParkErrorTest,
            oneParkErrorCommands,
            oneParkErrorTime,
            0.4,
            errorConfig
        )
        //----------Test6-multiple Park Test-------------------------------
        val multipleParkTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test61", 50_000.0),
                2 to PowerPlantData(2, "Test62", 20_000.0)
            ),
            inverters = mapOf(
                1 to InverterData(1, 1, 8_000.0, true, InverterType.HUAWEI),
                2 to InverterData(2, 1, 15_000.0, true, InverterType.TEST),
                3 to InverterData(3, 1, 8_500.0, true, InverterType.FRONIUS),
                4 to InverterData(4, 1, 18_500.0, true, InverterType.KACO),
                5 to InverterData(5, 2, 6_000.0, true, InverterType.FRONIUS),
                6 to InverterData(6, 2, 14_000.0, true, InverterType.KACO)
            )
        )
        val multipleParkCommands = mapOf(
            0 to 60_000.0,
            25 to 50_000.0,
            40 to 30_000.0
        )
        val multipleParkTime = 65
        val TEST_DATA_6 = SimTestData(
            multipleParkTest,
            multipleParkCommands,
            multipleParkTime,
            0.4,
        )
        //----------Test7-config test-------------------------------
        val multipleParkConfigTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test71", 50_000.0),
                2 to PowerPlantData(2, "Test72", 25_000.0)
            ),
            inverters = mapOf(
                1 to InverterData(1, 1, 8_000.0, true, InverterType.TEST),
                2 to InverterData(2, 1, 15_000.0, true, InverterType.TEST),
                3 to InverterData(3, 1, 8_500.0, true, InverterType.TEST),
                4 to InverterData(4, 1, 18_500.0, true, InverterType.TEST),
            ),
            engines = mapOf(
                1 to EngineData(1, 2, 10_000.0,0.3,10, EngineType.TEST),
                2 to EngineData(2, 2, 15_000.0,0.3,10, EngineType.TEST),
            ),
        )
        val multipleParkConfigCommands = mapOf(
            10 to 70_000.0,
            25 to 60_000.0,
            40 to 30_000.0
        )
        val multipleParkConfigConfig = config {
            addDefaultProduceConfig(UnitType.INVERTER, 0.3)
            addDefaultProduceConfig(UnitType.ENGINE, 0.5)
            addTypeConfig(UnitType.INVERTER, InverterType.TEST){
                READ_FREQUENCY = 1
                POWER_CONTROL_REACTION_TIME = 1
                TIME_ACCURACY = 0.0
            }
            addUnitConfig(UnitType.ENGINE, 1){
                addDefVales {
                    targetOutput = 8_000.0
                }
            }
        }
        val multipleParkConfigTime = 60
        val TEST_DATA_71 = SimTestData(
            multipleParkConfigTest,
            multipleParkConfigCommands,
            multipleParkConfigTime,
            1.0,
        )
        val TEST_DATA_72 = SimTestData(
            multipleParkConfigTest,
            multipleParkConfigCommands,
            multipleParkConfigTime,
            1.0,
            multipleParkConfigConfig
        )
        //----------Test8-komplex test-------------------------------
        val komplexTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test81", 50_000.0),
                2 to PowerPlantData(2, "Test82", 30_000.0),
                3 to PowerPlantData(3, "Test83", 40_000.0),
                4 to PowerPlantData(4, "Test84", 22_000.0),
            ),
            inverters = mapOf(
                1 to InverterData(1, 1, 20_000.0, true, InverterType.HUAWEI),
                2 to InverterData(2, 1, 20_000.0, true, InverterType.HUAWEI),
                3 to InverterData(3, 1, 10_000.0, true, InverterType.HUAWEI),
                4 to InverterData(4, 4, 2_000.0, true, InverterType.FRONIUS),
                5 to InverterData(5, 4, 2_000.0, true, InverterType.FRONIUS),
            ),
            engines = mapOf(
                1 to EngineData(1, 2, 10_000.0,0.3,8, EngineType.JENBACHER),
                2 to EngineData(2, 2, 8_000.0,0.3,8, EngineType.JENBACHER),
                3 to EngineData(3, 2, 12_000.0,0.3,8, EngineType.JENBACHER),
                4 to EngineData(4, 3, 20_000.0,0.4,10, EngineType.TEST),
                5 to EngineData(5, 3, 20_000.0,0.4,10, EngineType.TEST),
                6 to EngineData(6, 4, 10_000.0,0.5,12, EngineType.TEST),
                7 to EngineData(7, 4, 8_000.0,0.5,12, EngineType.TEST),
            ),
            batteries = mapOf(
                1 to BatteryData(1, 1, 10_000.0, BatteryType.TEST),
                2 to BatteryData(2, 2, 12_000.0, BatteryType.TEST),
                3 to BatteryData(3, 2, 6_000.0, BatteryType.TEST),
                4 to BatteryData(4, 2, 2_000.0, BatteryType.TEST)
            ),
            loadbanks = mapOf(
                1 to LoadbankData(1, 1, 5_000, 500, LoadbankType.TEST),
                2 to LoadbankData(2, 1, 2_000, 200, LoadbankType.TEST),
                3 to LoadbankData(3, 1, 4_000, 400, LoadbankType.TEST)
            )
        )
        val komplexCommands = mapOf(
            0 to 140_000.0,
            20 to 0.0,
            30 to 100_000.0,
            60 to 120_000.0,
            90 to 130_000.0,
            120 to 70_000.0,
            150 to 50_000.0,
            170 to 45_000.0
        )
        val komplexTime = 180
        val TEST_DATA_8 = SimTestData(
            komplexTest,
            komplexCommands,
            komplexTime,
            0.4,
        )
        //----------Test9-inverter-engine test-------------------------------
        val inverterEngineTest = SimulationData(
            powerPlants = mapOf(
                1 to PowerPlantData(1, "Test9", 50_000.0),
            ),
            inverters = mapOf(
                1 to InverterData(1, 1, 10_000.0, true, InverterType.HUAWEI),
                2 to InverterData(2, 1, 15_000.0, true, InverterType.HUAWEI),
                3 to InverterData(3, 1, 5_000.0, true, InverterType.HUAWEI),
            ),
            engines = mapOf(
                1 to EngineData(1, 1, 8_000.0,0.3,8, EngineType.JENBACHER),
                2 to EngineData(2, 1, 8_000.0,0.3,8, EngineType.JENBACHER),
                3 to EngineData(3, 1, 4_000.0,0.3,8, EngineType.JENBACHER),
            ),
        )
        val inverterEngineCommands = mapOf(
            0 to 50_000.0,
            20 to 0.0,
            30 to 20_000.0,
        )
        private const val inverterEngineTime: Int = 60
        val TEST_DATA_9 = SimTestData(
            inverterEngineTest,
            inverterEngineCommands,
            inverterEngineTime,
            0.4,
        )
    }
}