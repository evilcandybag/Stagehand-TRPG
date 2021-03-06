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
import scala.swing.BoxPanel
import scala.swing.Orientation
import scala.swing.Swing
import scala.swing.Swing.Embossing
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics2D
import se.stagehand.swing.lib.Vector2._
import se.stagehand.lib.Log
import se.stagehand.swing.assets.ImageAssets
import scala.swing.FlowPanel
import se.stagehand.lib.scripting.StagehandComponent
import se.stagehand.lib.scripting.Effect

object SceneGUI extends ScriptGUI {
  private val log = Log.getLog(this.getClass())
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
  private val log = Log.getLog(this.getClass)
  def title = script.displayName
  
  val fxGUI = new ContentPanel("FX") {
    for (e <- script.effects) {
      val item = GUIManager.getGUI[EffectGUI](e.getClass).playerItem(e)
      contents += item
      item.refresh
      revalidate
      repaint
    }
    
  } 
  layout(fxGUI) = Position.Center
  repaint
  revalidate
  script.effects.foreach(x => log.debug(x.toString))
  layout.keys.foreach(x => log.debug(x.toString))
}

class SceneNode(script: Scene) extends EditorScriptNode[Scene](script) with InputGUI[Scene] {
  val effectPanel = new BoxPanel(Orientation.Vertical) {
    border = Swing.EtchedBorder(Swing.Raised)
    
    visible = true
  }
  val addbutton = new AddEffectsButton(this,effectPanel,(gui:EditorEffectItem[_],comp: Effect) => {
    script.add(comp)
    effectPanel.contents += gui
  })
    
  pan.layout(effectPanel) = Position.Center
  pan.layout(new FlowPanel {
    contents += addbutton
  }) = Position.South
 
  script.effects.foreach(e => {
    val gui = GUIManager.componentByID[EditorEffectItem[_]](e.id)
    gui match {
      case Some(g) => {
        effectPanel.contents += g
      }
      case None => {
        effectPanel.contents += GUIManager.editorItem(e)
      }
    }
  })
  refresh
  override def paint(g:Graphics2D) {
//    peer.setSize(preferredSize)
    super.paint(g)
  }
}