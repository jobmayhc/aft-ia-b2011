/********************************************/
/*	Proyecto Inteligencia Artificial B-2011	*/
/*	Agente Viajero Rapido - AFT 			*/
/*	Realizado por: Rafael J Torres			*/ 
/********************************************/
package version1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JApplet;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

@SuppressWarnings("serial")
public class GraphStatic extends JApplet {

	Data datos; 
	Dimension dimension;
	SparseGraph<String,Arc> grafo;
	Layout<String,Arc> dibujo;
	VisualizationViewer<String, Arc> visor;

	public GraphStatic(){
		grafo = new SparseGraph<String,Arc>();
		datos = new Data(new ArrayList<Node>(),new ArrayList<Arc>() );
	}

	public void load(String nodos, String arcos){
		datos.loadNodos(nodos);
		datos.loadArcos(arcos);
		for ( Node lista : datos.getNodos() ){
			grafo.addVertex(lista.getId());
		}
		for ( Arc lista : datos.getArcos() ){
			grafo.addEdge( lista, lista.getFuente(), lista.getDestino(), EdgeType.UNDIRECTED );
		}
	}

	public void generate(String archivo){

		final ImageIcon fondo = new ImageIcon(getClass().getResource(archivo));
		dimension = new Dimension(
				Integer.parseInt( archivo.substring(archivo.indexOf('_') + 1, archivo.indexOf('x')) ),
				Integer.parseInt( archivo.substring(archivo.indexOf('x') + 1, archivo.indexOf('.')) )
				);

        dibujo = new StaticLayout<String,Arc>(grafo);		
        dibujo.setSize(dimension);
		for ( Node lista : datos.getNodos() ){
			dibujo.setLocation( lista.getId(), lista.getUbicacion() );
		}

		visor =  new VisualizationViewer<String,Arc>(dibujo,dimension);	
		visor.addPreRenderPaintable(new VisualizationViewer.Paintable(){
				public void paint(Graphics g) {
					Graphics2D g2d = (Graphics2D)g;
					AffineTransform oldXform = g2d.getTransform();
					AffineTransform lat = visor.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getTransform();
					AffineTransform vat = visor.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getTransform();
					AffineTransform at = new AffineTransform();
					at.concatenate(g2d.getTransform());
					at.concatenate(vat);
					at.concatenate(lat);
					g2d.setTransform(at);
					g.drawImage(fondo.getImage(), 0, 0, fondo.getIconWidth(),fondo.getIconHeight(),visor);
					g2d.setTransform(oldXform);
				}
				public boolean useTransform() { return false; }
			});

		visor.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		visor.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);		        

		final GraphZoomScrollPane panel = new GraphZoomScrollPane(visor);
		add(panel);	
	}	
	
}

