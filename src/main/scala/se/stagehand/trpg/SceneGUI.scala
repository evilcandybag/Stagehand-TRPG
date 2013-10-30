package se.stagehand.trpg

import se.stagehand.gui.components._
import se.stagehand.lib.scripting.ScriptComponent

object SceneGUI extends ScriptGUI {
  val peer = classOf[Scene]
  
  implicit def menuItem(script: ScriptComponent) = {
    new SceneButton(script)
  }
  implicit def editorNode(script: ScriptComponent) = {
    new SceneNode(script)
  }
}

class SceneButton(peer: ScriptComponent) extends AbstractScriptButton(peer) {
  type peertype = Scene
}

class SceneNode(peer: ScriptComponent) extends AbstractScriptNode(peer) {
  type peertype = Scene
}