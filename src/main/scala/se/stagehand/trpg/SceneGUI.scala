package se.stagehand.trpg

import se.stagehand.lib.scripting.ScriptComponent
import scala.swing.BorderPanel.Position
import se.stagehand.swing.lib._
import se.stagehand.swing.player.PlayerScriptNode

object SceneGUI extends ScriptGUI {
  val peer = classOf[Scene]
  
  def menuItem(script: ScriptComponent) = {
    checkScript(script)
    new SceneButton(script.asInstanceOf[Scene])
  }
  def editorNode(script: ScriptComponent) = {
    checkScript(script)
    new SceneNode(script.asInstanceOf[Scene])
  }
  
  def playerNode(script: ScriptComponent) = {
    checkScript(script)
    new ScenePlayerNode(script.asInstanceOf[Scene])

  }
}

class SceneButton(peer: Scene) extends EditorScriptButton(peer) {
}

class ScenePlayerNode(sc: Scene) extends PlayerScriptNode[Scene](sc) {
  
}

class SceneNode(peer: Scene) extends EditorScriptNode[Scene](peer) with InputGUI[Scene] {
  
}