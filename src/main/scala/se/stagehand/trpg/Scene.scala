package se.stagehand.trpg

import se.stagehand.lib.scripting._
import scala.xml.Elem
import scala.swing._
import scala.swing.event.Key

class Scene extends ScriptComponent with Input {
  val displayName = "Scene"
  
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
  def readInstructions(in: Elem) {}

  override def generateInstructions = {
    implicit var xml = super.generateInstructions
    xml = addChild(effectsXML)
    
    xml
  }
  
  private def effectsXML = {
    <effects>{effects.map(_.idXML)}</effects>
  }
}
