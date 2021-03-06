package se.stagehand.trpg

import se.stagehand.plugins._
import se.stagehand.lib.scripting._

class TRPGPlugin extends ScriptPlugin {

  val name = "TRPG"
    
  val guis = List(SceneGUI, EventGUI)
    
  val scriptcomponents: Array[ScriptComponent] = Array(new Scene, new Event)
  
}