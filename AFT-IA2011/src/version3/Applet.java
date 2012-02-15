/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Applet extends JApplet implements ItemListener, ActionListener{

	public static CheckboxGroup group;
	public static Checkbox blocks, start, finish;
	public static List level;
	public static Button go, reset;
	public static JProgressBar barra;
	
	@SuppressWarnings({ "deprecation" })
	public void init(){
		AgentIA.MAP.setSize(Global.IMAG_X, Global.IMAG_Y);
		add(AgentIA.MAP,BorderLayout.CENTER);
		Panel sel = new Panel( new GridLayout(5,1) );
		sel.add(level = new List(5));								level.addItemListener(this);
		level.addItem(" Nada ",0);
		level.addItem(" Bajo  ("+Global.BAJO+")",1);
		level.addItem(" Medio ("+Global.MEDIO+")",2);
		level.addItem(" Alto  ("+Global.ALTO+")",3);		
		level.addItem(" Total ",4);
		group = new CheckboxGroup();
		sel.add(blocks = new Checkbox("Obstaculo",group,false));	blocks.addItemListener(this);       
		sel.add(start = new Checkbox("Destino",group,false));		start.addItemListener(this);
		sel.add(finish = new Checkbox("Origen",group,false));		finish.addItemListener(this);
		Panel bot = new Panel( new GridLayout(3,1) );		
		sel.add(bot);
		bot.add(go = new Button("Iniciar"));						go.addActionListener(this);
		bot.add(reset = new Button("Reiniciar"));					reset.addActionListener(this);
		bot.add(barra= new JProgressBar(0,100) );
		add(sel,BorderLayout.WEST);
		setSize(800,600);
	}

	public void itemStateChanged(ItemEvent e){
		if( e.getSource() == level ){
			blocks.setState(true);
			MapCell.editMode = Global.OBSTACULO;
			switch( level.getSelectedIndex() ){
			case 0:	MapCell.pesoObstaculo = Global.NADA;		return;
			case 1:	MapCell.pesoObstaculo = Global.BAJO;		return;
			case 2:	MapCell.pesoObstaculo = Global.MEDIO;		return;
			case 3:	MapCell.pesoObstaculo = Global.ALTO;		return;
			case 4:	MapCell.pesoObstaculo = Global.TOTAL;		return;
			}                             
		}
		Checkbox box = group.getSelectedCheckbox();
		if( box == blocks ){	MapCell.editMode = Global.OBSTACULO;	return;}
		if( box == start  ){	MapCell.editMode = Global.ORIGEN;		return;}
		if( box == finish ){	MapCell.editMode = Global.DESTINO;		return;}
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		if( e.getSource() == go){
			if ( go.getLabel().equals("Iniciar") ){
				if( AgentIA.BUS.findRuta() ){
					Huristic.aux = MapCell.fin;
					go.setLabel("Detener");
					finish.disable();
					reset.disable();
				}
			}else{
				if( go.getLabel().equals("Detener") ){
					if( AgentIA.HILO.isAlive() )
						AgentIA.HILO.suspend();
					if( !Huristic.actual.equals(MapCell.inicio) )
						go.setLabel("Reanudar");
					else
						go.setLabel("Iniciar");
					reset.enable();
				}else{
					if( go.getLabel().equals("Reanudar") ){
						if( AgentIA.HILO == null || Huristic.actual.equals(MapCell.inicio) ){
							go.setLabel("Iniciar");
							reset.enable();
						}else{
							if( AgentIA.HILO.isAlive() )
								AgentIA.HILO.resume();
							go.setLabel("Detener");
							reset.disable();
						}
					}
				}
			}
		}
		if( e.getSource() == reset){
			go.setLabel("Iniciar");
			if( AgentIA.HILO.isAlive() ) {
				AgentIA.HILO.stop();
				MapCell.fin = Huristic.aux;
			}
			AgentIA.BUS = new Huristic();
			finish.enable();
		}
	}
	

}