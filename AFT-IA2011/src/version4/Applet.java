/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version4;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Applet extends JApplet implements ItemListener, ActionListener{

	private CheckboxGroup group;
	private Checkbox blocks, start, finish;
	private List level;
	private Button on, off, drive, stop;
	public static JProgressBar gas;
    
	@SuppressWarnings({ "deprecation" })
	public void init(){
		AgentIA.map.setSize(Global.IMAG_X, Global.IMAG_Y);
		add(AgentIA.map,BorderLayout.CENTER);
		Panel sel = new Panel( new GridLayout(4,1) );
		sel.add(level = new List(5));								level.addItemListener(this);
		level.addItem(" Nada ",0);
		level.addItem(" Bajo  ("+Global.BAJO+")",1);
		level.addItem(" Medio ("+Global.MEDIO+")",2);
		level.addItem(" Alto  ("+Global.ALTO+")",3);		
		level.addItem(" Total ",4);
		group = new CheckboxGroup();
		sel.add(blocks = new Checkbox("Obstaculo",group,false));	blocks.addItemListener(this);       
		sel.add(finish = new Checkbox("Destino",group,false));		finish.addItemListener(this);
		sel.add(start = new Checkbox("Origen",group,false));		start.addItemListener(this);
		add(sel,BorderLayout.WEST);

		Panel bot = new Panel( new GridLayout(1,5) );	
		bot.add(gas= new JProgressBar(0,100) );		gas.setStringPainted(true);
		bot.add(on = new Button("Encender"));		on.addActionListener(this);
		bot.add(off = new Button("Apagar"));		off.addActionListener(this);
		bot.add(drive = new Button("Conducir"));	drive.addActionListener(this);
		bot.add(stop = new Button("Detener"));		stop.addActionListener(this);
		add(bot,BorderLayout.SOUTH);

		setSize(800,600);
	}

	public void itemStateChanged(ItemEvent e){
		if( e.getSource() == level ){
			blocks.setState(true);
			Global.EDIT = Global.OBSTACULO;
			switch( level.getSelectedIndex() ){
			case 0:	Global.TIPO = Global.NADA;		return;
			case 1:	Global.TIPO = Global.BAJO;		return;
			case 2:	Global.TIPO = Global.MEDIO;		return;
			case 3:	Global.TIPO = Global.ALTO;		return;
			case 4:	Global.TIPO = Global.TOTAL;		return;
			}                             
		}
		Checkbox box = group.getSelectedCheckbox();
		if( box == blocks ){	Global.EDIT = Global.OBSTACULO;	return;}
		if( box == start  ){	Global.EDIT = Global.ORIGEN;	return;}
		if( box == finish ){	Global.EDIT = Global.DESTINO;	return;}
	}

	public void actionPerformed(ActionEvent e){
		if( e.getSource().equals(on) ){
			AgentIA.AFT.on();
		}else
			if( e.getSource().equals(off) ){
				AgentIA.AFT.off();
			}else
				if( e.getSource().equals(drive) ){
					AgentIA.AFT.drive();
				}else
					if( e.getSource().equals(stop) ){
						AgentIA.AFT.stop();
					}
	}

}