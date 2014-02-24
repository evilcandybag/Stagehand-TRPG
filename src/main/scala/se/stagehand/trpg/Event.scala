package se.stagehand.trpg

import se.stagehand.lib.scripting._
import scala.xml._
import scala.swing._
import scala.swing.event.Key
import se.stagehand.lib.Log
import se.stagehand.lib.scripting.network.Directives

class Event(id:Int) extends ScriptComponent(id) with Input {
  import Directives._
  
  private val log = Log.getLog(this.getClass())
  def this() = this(ID.unique)
  
  override def componentName = "Event"
  val description = "A collection of scenes that can be activated at the same time."
    
    
  private var _effects: List[Effect] = List()
  
  def add(e:Effect) = {
    e.addArg(Persistence.ARG, Persistence.KEEP)
    _effects = e :: _effects
  }
  def remove(e:Effect) = {
    e.removeArg(Persistence.ARG)
    _effects = _effects diff List(e)
  }
  def effects = _effects
    
  def executeInstructions(params: Any*) {
    _effects.foreach( x => 
      x.trigger
    )
  }
  override def readInstructions(in: Node) {
    super.readInstructions(in)
    val fxXML = (in \ "effects") \ "id"
    log.debug(fxXML.toString)
    val fx = fxXML.map(x => ID.fetch[Effect](x.text.toInt))
    
    fx.foreach(x => {
      add(x)
    })
  }

  override def generateInstructions = {
    implicit var xml = super.generateInstructions
    xml = addChild(effectsXML)
    
    xml
  }
  
  private def effectsXML = {
    <effects>{_effects.map(_.idXML)}</effects>
  }
}
