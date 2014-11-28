package nx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Personaje extends Actor{
	ArrayList<Image> images;
	float frame = 0;
	int actual;
	float velocidad_y = 0, aceleracion_y = 0;
	Music jump = Gdx.audio.newMusic(Gdx.files.getFileHandle("jump.wav", FileType.Internal));;
	
	Personaje(){
		images = new ArrayList<Image>();
		for (int i = 1; i < 5; i++){
			images.add(new Image(new Texture("run0"+i+".png")));
		}
		actual = 0;
		setWidth(256);
		setHeight(256);
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		frame+=delta;
		
		if (frame>0.1){
			actual++;
			frame = 0;
		}
		
		if (actual >= images.size())
			actual = 0;
		
		velocidad_y += aceleracion_y;
		this.setY(this.getY()+velocidad_y);
		
		aceleracion_y -= 0.06;
		
		if (this.getY()<=70){
			velocidad_y = 0;
			aceleracion_y = 0;
			this.setY(70);
		}
		
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		images.get(actual).setY(this.getY());
		images.get(actual).setWidth(256);
		images.get(actual).setHeight(256);
		images.get(actual).draw(batch, parentAlpha);
	}
	
	void saltar(){
		if (this.getY()==70){
			aceleracion_y = 1.2f;
			jump.play();
		}
	}
	
}
