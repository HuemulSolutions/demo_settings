package com.yourcompany.settings

//import com.databricks.dbutils_v1.DBUtilsV1
import com.huemulsolutions.bigdata.common._

import scala.io.Source
import java.io.{FileNotFoundException, IOException}



/**
 * Configuración del ambiente
 */
object globalSettings {
   val global: HuemulGlobalPath  = new HuemulGlobalPath()
   global.globalEnvironments = "production, experimental"

   /**
   * Get encrypted key from file, and return decrypted key.
   */
  def getKeyFromFile(fileName: String): String = {
    var key: String = null

   try {
      val openFile = Source.fromFile(fileName)
      key = openFile.getLines.mkString
      openFile.close()
    } catch {
        case _: FileNotFoundException => println(s"Couldn't find that file: $fileName")
        case e: IOException => println(s"($fileName). Got an IOException! ${e.getLocalizedMessage}")
        case _: Exception => println(s"exception opening $fileName")
    }

    key
  }


   val localPath: String = System.getProperty("user.dir").concat("/")
   println(s"path: $localPath")

  /*********
  * CONFIGURACION PARA AZURE-DATABRICKS
  */

/*
   //para ejemplo sobre databricks
   Global.setBigDataProvider( huemulType_bigDataProvider.databricks)
   val baseDir = "/mnt/huemul/data"

   Global.HIVE_HourToUpdateMetadata =50

   val dbutils: DBUtilsV1 = com.databricks.dbutils_v1.DBUtilsHolder.dbutils
   val lControlConnectionString: String = dbutils.secrets.get(scope = "huemul-test-secret-scope", key = "production-demo-setting-control-connection")
   Global.controlSetting.append(new HuemulKeyValuePath("production",lControlConnectionString))

   Global.ImpalaEnabled = false
   val lImpalaConnectionString: String = dbutils.secrets.get(scope = "huemul-test-secret-scope", key = "production-demo-setting-impala-connection")
   Global.IMPALA_Setting.append(new HuemulKeyValuePath("production",lImpalaConnectionString))

   Global.setAVRO_format("avro")

 */
   //FIN DATABRICKS


  /*********
  * CONFIGURACION ON-PREM
  */
  //val baseDir = "/user/data"

  /*********
  * CONFIGURACION PARA GOOGLE CLOUD PLATFORM
  */
  val baseDir = "gs://data_huemul_25/data/user/data" //for google


   val controlSettings: Array[String] = getKeyFromFile(s"${localPath}prod-demo-setting-control-connection.set").split(";")
   val controlConnString:String = controlSettings(0)
   val controlUserName: String = if (controlSettings.length >= 2) controlSettings(1) else null
   val controlPassword: String = if (controlSettings.length >= 3) controlSettings(2) else null

   global.hiveHourToUpdateMetadata =50
   global.controlSetting.append(new HuemulKeyValuePath("production",controlConnString)
     .setUserName(controlUserName)
     .setPassword(controlPassword)
   )
   global.controlSetting.append(new HuemulKeyValuePath("experimental",controlConnString)
     .setUserName(controlUserName)
     .setPassword(controlPassword)
   )

   global.impalaEnabled = false
   global.impalaSetting.append(new HuemulKeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-impala-connection.set")))
   global.impalaSetting.append(new HuemulKeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-impala-connection.set")))


   /**
    * NEW FROM 2.5
    */

   //Agrega configuración para uso de Hortonworks Hive Connector
   //Global.externalBBDD_conf.Using_HWC.setActive(true)


   //from 2.3
   //val HIVE_Setting = new ArrayBuffer[HuemulKeyValuePath]()
   //HIVE_Setting.append(new HuemulKeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-hive-connection.set")))
   //HIVE_Setting.append(new HuemulKeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-hive-connection.set")))

   //from 2.3
   //Global.externalBBDD_conf.Using_HIVE.setActive(true).setActiveForHBASE(true).setConnectionStrings(HIVE_Setting)


   //TEMPORAL SETTING
   global.temporalPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/temp/"))
   global.temporalPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/temp/"))

   //RAW SETTING
   global.rawSmallFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/raw/"))
   global.rawSmallFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/raw/"))

   global.rawBigFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/raw/"))
   global.rawBigFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/raw/"))



   //MASTER SETTING
   global.masterDataBase.append(new HuemulKeyValuePath("production","production_master"))
   global.masterDataBase.append(new HuemulKeyValuePath("experimental","experimental_master"))

   global.masterSmallFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/master/"))
   global.masterSmallFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/master/"))

   global.masterBigFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/master/"))
   global.masterBigFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/master/"))

   //DIM SETTING
   global.dimDataBase.append(new HuemulKeyValuePath("production","production_dim"))
   global.dimDataBase.append(new HuemulKeyValuePath("experimental","experimental_dim"))

   global.dimSmallFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/dim/"))
   global.dimSmallFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/dim/"))

   global.dimBigFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/dim/"))
   global.dimBigFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/dim/"))

   //ANALYTICS SETTING
   global.analyticsDataBase.append(new HuemulKeyValuePath("production","production_analytics"))
   global.analyticsDataBase.append(new HuemulKeyValuePath("experimental","experimental_analytics"))

   global.analyticsSmallFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/analytics/"))
   global.analyticsSmallFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/analytics/"))

   global.analyticsBigFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/analytics/"))
   global.analyticsBigFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/analytics/"))

   //REPORTING SETTING
   global.reportingDataBase.append(new HuemulKeyValuePath("production","production_reporting"))
   global.reportingDataBase.append(new HuemulKeyValuePath("experimental","experimental_reporting"))

   global.reportingSmallFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/reporting/"))
   global.reportingSmallFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/reporting/"))

   global.reportingBigFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/reporting/"))
   global.reportingBigFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/reporting/"))

   //SANDBOX SETTING
   global.sandboxDataBase.append(new HuemulKeyValuePath("production","production_sandbox"))
   global.sandboxDataBase.append(new HuemulKeyValuePath("experimental","experimental_sandbox"))

   global.sandboxSmallFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/sandbox/"))
   global.sandboxSmallFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/sandbox/"))

   global.sandboxBigFilesPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/sandbox/"))
   global.sandboxBigFilesPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/sandbox/"))

   //DQ_ERROR SETTING
   global.dqSaveErrorDetails = true
   global.dqErrorDataBase.append(new HuemulKeyValuePath("production","production_DQError"))
   global.dqErrorDataBase.append(new HuemulKeyValuePath("experimental","experimental_DQError"))

   global.dqErrorPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/dqerror/"))
   global.dqErrorPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/dqerror/"))

   //OLD VALUE TRACE
   global.mdmSaveOldValueTrace = true
   global.mdmOldValueTraceDataBase.append(new HuemulKeyValuePath("production","production_mdm_oldvalue"))
   global.mdmOldValueTraceDataBase.append(new HuemulKeyValuePath("experimental","experimental_mdm_oldvalue"))

   global.mdmOldValueTracePath.append(new HuemulKeyValuePath("production",s"$baseDir/production/mdm_oldvalue/"))
   global.mdmOldValueTracePath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/mdm_oldvalue/"))

   //BACKUP
   global.mdmBackupPath.append(new HuemulKeyValuePath("production",s"$baseDir/production/backup/"))
   global.mdmBackupPath.append(new HuemulKeyValuePath("experimental",s"$baseDir/experimental/backup/"))


   //HBase
   global.setHBaseAvailable()
}

