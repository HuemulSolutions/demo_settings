package samples

import org.junit._
import Assert._


@Test
class AppTest {
    val args: Array[String] = new Array[String](1)
    args(0) = "Environment=production,RegisterInControl=false,TestPlanMode=true"
      
    //val huemulLib = new huemul_BigDataGovernance("Pruebas Inicialización de Clases",args,com.yourcompany.settings.globalSettings.Global)
    //val Control = new huemul_Control(huemulLib,null, huemulType_Frequency.MONTHLY)
      
     @Test
    def OK(): Unit = assertTrue(true)


}


