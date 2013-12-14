package se.stagehand.trpg

import se.stagehand.lib.scripting.ScriptComponent
import scala.swing.BorderPanel.Position
import se.stagehand.swing.lib._
import se.stagehand.swing.player.PlayerScriptNode
import scala.swing.Label
import javax.swing.JLabel
import java.awt.BorderLayout
import java.awt.event.MouseEvent.{BUTTON1}
import scala.swing.event.MouseClicked
import scala.swing.Button
import scala.swing.Action
import se.stagehand.swing.editor.EffectSelector

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
  listenTo(mouse.clicks)
  def title = script.displayName
  reactions += {
    case e:MouseClicked if e.peer.getButton == BUTTON1 => {
      script.executeInstructions()
    }
  }
}

class SceneNode(peer: Scene) extends EditorScriptNode[Scene](peer) with InputGUI[Scene] {
  val addbutton = new Button("+") {
    action = new Action("+") {
      def apply {
        val efs = EffectSelector.pickEffectsAsInstances
        
      }
    }
  }
  pan.layout(addbutton) = Position.South
}