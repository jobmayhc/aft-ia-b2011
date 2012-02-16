/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version1;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class GraphDinamic extends javax.swing.JApplet {

    List<Arc> arcos;
    List<Node> nodos;
	Graph<String,Arc> grafo;
	AbstractLayout<String,Arc> dibujo;
	VisualizationViewer<String,Arc> visor;
    Timer timer;
    boolean fin;
        
    public GraphDinamic(List<Arc> camino, List<Node> tabla){
        grafo = new SparseGraph<String,Arc>();
    	timer = null;
    	fin = false;
    	arcos = camino;
    	nodos = tabla;
	}
    
    public void generate(String archivo){       
   	
    	final ImageIcon fondo = new ImageIcon(getClass().getResource(archivo));
		Dimension dimension = new Dimension(
				Integer.parseInt( archivo.substring(archivo.indexOf('_') + 1, archivo.indexOf('x')) ),
				Integer.parseInt( archivo.substring(archivo.indexOf('x') + 1, archivo.indexOf('.')) )
				);
        dibujo = new FRLayout2<String,Arc>(grafo);
        dibujo.setSize(dimension);
     
		visor =  new VisualizationViewer<String,Arc>(dibujo,dimension);	
		visor.addPreRenderPaintable(new VisualizationViewer.Paintable(){
				public void paint(Graphics grafo) {
					Graphics2D g2d = (Graphics2D)grafo;
					AffineTransform oldXform = g2d.getTransform();
					AffineTransform lat = visor.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getTransform();
					AffineTransform vat = visor.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getTransform();
					AffineTransform at = new AffineTransform();
					at.concatenate(g2d.getTransform());
					at.concatenate(vat);
					at.concatenate(lat);
					g2d.setTransform(at);
					grafo.drawImage(fondo.getImage(), 0, 0, fondo.getIconWidth(),fondo.getIconHeight(),visor);
					g2d.setTransform(oldXform);
				}
				public boolean useTransform() { return false; }
			});		        

		final GraphZoomScrollPane panel = new GraphZoomScrollPane(visor);
		add(panel);	       
        timer = new Timer();
    }

    public void start() {
        validate();
        timer.schedule(new RemindTask(), 1000, 1500);
        visor.repaint();
    }

    class RemindTask extends TimerTask {
        public void run() {
            process();
            if(fin) cancel();

        }
    }
    
    public void process() {
        try {

            if ( arcos.size() > 0 ){
            	dibujo.lock(true);

                Relaxer relaxer = visor.getModel().getRelaxer();
                relaxer.pause();
                
                Arc auxA = arcos.remove(0);
                dibujo.setLocation(auxA.getFuente(),nodos.get( nodos.lastIndexOf( new Node( auxA.getFuente() ) ) ).getUbicacion() );
                dibujo.setLocation(auxA.getDestino(),nodos.get( nodos.lastIndexOf( new Node( auxA.getDestino() ) ) ).getUbicacion() );
             	grafo.addEdge(auxA, auxA.getDestino(), auxA.getFuente(),EdgeType.UNDIRECTED);                
                dibujo.setGraph(grafo);
                relaxer.resume();
                dibujo.lock(false);

            } else {
            	fin = true;
            }

        } catch (Exception e) {
            System.out.println(e);

        }
    }

}