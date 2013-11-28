package se.stagehand.trpg

import se.stagehand.lib.scripting._
import scala.xml._
import scala.swing._
import scala.swing.event.Key

class Scene extends ScriptComponent with Input {
  override def componentName = "Scene"
  
  var effects: List[Effect] = List()
    
  def act:Unit = {
    loop {
      react {
        case Start => executeInstructions
      }
    }
  }
    
  def executeInstructions {
    effects.foreach( x => 
      x.trigger
    )
  }
  override def readInstructions(in: Node) {
    super.readInstructions(in)
  }

  override def generateInstructions = {
    implicit var xml = super.generateInstructions
    xml = addChild(effectsXML)
    
    xml
  }
  
  private def effectsXML = {
    <effects>{effects.map(_.idXML)}</effects>
  }
}
