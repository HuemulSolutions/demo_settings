package com.yourcompany.settings

import com.huemulsolutions.bigdata.common._
import scala.collection.mutable._
import scala.io.Source
import java.io.{FileNotFoundException, IOException}


/**
 * Configuración del ambiente
 */
object globalSettings {
   val Global: huemul_GlobalPath  = new huemul_GlobalPath()
   Global.GlobalEnvironments = "production, experimental"
   
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
        case e: FileNotFoundException => println(s"Couldn't find that file: ${fileName}")
        case e: IOException => println(s"(${fileName}). Got an IOException! ${e.getLocalizedMessage}")
        case e: Exception => println(s"exception opening ${fileName}")
    }
    
    return key
  }
   
   val localPath: String = System.getProperty("user.dir").concat("/")
   println(s"path: ${localPath}")
   
   Global.HIVE_HourToUpdateMetadata = 5
   Global.CONTROL_Setting.append(new huemul_KeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-control-connection.set")))
   Global.CONTROL_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-control-connection.set")))
   
   Global.ImpalaEnabled = false
   Global.IMPALA_Setting.append(new huemul_KeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-impala-connection.set")))
   Global.IMPALA_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-impala-connection.set")))

   //from 2.3
   val HIVE_Setting = new ArrayBuffer[huemul_KeyValuePath]()
   HIVE_Setting.append(new huemul_KeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-hive-connection.set")))
   HIVE_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-hive-connection.set")))

   //from 2.3
   Global.externalBBDD_conf.Using_HIVE.setActive(true).setActiveForHBASE(true).setConnectionStrings(HIVE_Setting)
   //Global.HIVE_Setting.append(new huemul_KeyValuePath("production",getKeyFromFile(s"${localPath}prod-demo-setting-hive-connection.set")))
   //Global.HIVE_Setting.append(new huemul_KeyValuePath("experimental",getKeyFromFile(s"${localPath}prod-demo-setting-hive-connection.set")))
   
   
   //TEMPORAL SETTING
   Global.TEMPORAL_Path.append(new huemul_KeyValuePath("production","/user/data/production/temp/"))
   Global.TEMPORAL_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/temp/"))
     
   //RAW SETTING
   Global.RAW_SmallFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/raw/"))
   Global.RAW_SmallFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/raw/"))
   
   Global.RAW_BigFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/raw/"))
   Global.RAW_BigFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/raw/"))
   
   
   
   //MASTER SETTING
   Global.MASTER_DataBase.append(new huemul_KeyValuePath("production","production_master"))   
   Global.MASTER_DataBase.append(new huemul_KeyValuePath("experimental","experimental_master"))

   Global.MASTER_SmallFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/master/"))
   Global.MASTER_SmallFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/master/"))
   
   Global.MASTER_BigFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/master/"))
   Global.MASTER_BigFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/master/"))

   //DIM SETTING
   Global.DIM_DataBase.append(new huemul_KeyValuePath("production","production_dim"))   
   Global.DIM_DataBase.append(new huemul_KeyValuePath("experimental","experimental_dim"))

   Global.DIM_SmallFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/dim/"))
   Global.DIM_SmallFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/dim/"))
   
   Global.DIM_BigFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/dim/"))
   Global.DIM_BigFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/dim/"))

   //ANALYTICS SETTING
   Global.ANALYTICS_DataBase.append(new huemul_KeyValuePath("production","production_analytics"))   
   Global.ANALYTICS_DataBase.append(new huemul_KeyValuePath("experimental","experimental_analytics"))
   
   Global.ANALYTICS_SmallFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/analytics/"))
   Global.ANALYTICS_SmallFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/analytics/"))
   
   Global.ANALYTICS_BigFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/analytics/"))
   Global.ANALYTICS_BigFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/analytics/"))

   //REPORTING SETTING
   Global.REPORTING_DataBase.append(new huemul_KeyValuePath("production","production_reporting"))
   Global.REPORTING_DataBase.append(new huemul_KeyValuePath("experimental","experimental_reporting"))

   Global.REPORTING_SmallFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/reporting/"))
   Global.REPORTING_SmallFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/reporting/"))
   
   Global.REPORTING_BigFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/reporting/"))
   Global.REPORTING_BigFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/reporting/"))

   //SANDBOX SETTING
   Global.SANDBOX_DataBase.append(new huemul_KeyValuePath("production","production_sandbox"))
   Global.SANDBOX_DataBase.append(new huemul_KeyValuePath("experimental","experimental_sandbox"))
   
   Global.SANDBOX_SmallFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/sandbox/"))
   Global.SANDBOX_SmallFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/sandbox/"))
   
   Global.SANDBOX_BigFiles_Path.append(new huemul_KeyValuePath("production","/user/data/production/sandbox/"))
   Global.SANDBOX_BigFiles_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/sandbox/"))
   
   //DQ_ERROR SETTING
   Global.DQ_SaveErrorDetails = true
   Global.DQError_DataBase.append(new huemul_KeyValuePath("production","production_DQError"))
   Global.DQError_DataBase.append(new huemul_KeyValuePath("experimental","experimental_DQError"))
   
   Global.DQError_Path.append(new huemul_KeyValuePath("production","/user/data/production/dqerror/"))
   Global.DQError_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/dqerror/"))

   //OLD VALUE TRACE
   Global.MDM_SaveOldValueTrace = true
   Global.MDM_OldValueTrace_DataBase.append(new huemul_KeyValuePath("production","production_mdm_oldvalue"))
   Global.MDM_OldValueTrace_DataBase.append(new huemul_KeyValuePath("experimental","experimental_mdm_oldvalue"))
   
   Global.MDM_OldValueTrace_Path.append(new huemul_KeyValuePath("production","/user/data/production/mdm_oldvalue/"))
   Global.MDM_OldValueTrace_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/mdm_oldvalue/"))

   //BACKUP
   Global.MDM_Backup_Path.append(new huemul_KeyValuePath("production","/user/data/production/backup/"))
   Global.MDM_Backup_Path.append(new huemul_KeyValuePath("experimental","/user/data/experimental/backup/"))


   //HBase
   Global.setHBase_available()
}

