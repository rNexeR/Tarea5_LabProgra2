package nx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Ghost extends Actor{
	ArrayList<Image> images;
	int dibujoActual;
	float tiempoAct = 0;
	Rectangle me;
	
	public Ghost(){
		super();
		images = new ArrayList<Image>();
		//Creando los images 
		images.add(new Image(new Texture("volador01.png")));
		images.add(new Image(new Texture("volador02.png")));
		images.add(new Image(new Texture("volador03.png")));
		
		dibujoActual = 0;
		setY(75);
		setX(800);
		setWidth(80);
		setHeight(80);
	}
	
	
	public void act(float delta){
		super.act(delta);
		tiempoAct += delta;
		
		if (tiempoAct >  0.1f){
			dibujoActual++;
			tiempoAct = 0;			
		}
		
		if(dibujoActual >= images.size())
			dibujoActual = 0;
		
		if (getX()<-1)
			this.remove();
		//System.out.println(tiempoAct);
		//++llamadasAct;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		setX(getX()-8);
		images.get(dibujoActual).setX(this.getX());
		images.get(dibujoActual).setY(this.getY());
		images.get(dibujoActual).setWidth(this.getWidth());
		images.get(dibujoActual).setHeight(this.getHeight());
		images.get(dibujoActual).draw(batch, parentAlpha);
	}
}
