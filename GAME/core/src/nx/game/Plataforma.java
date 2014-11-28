package nx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Plataforma extends Image{
	
	public Plataforma(float x) {
		// TODO Auto-generated constructor stub
		super(new Texture("tierra.png"));
		this.setX(x);
		this.setY(-200);
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		this.setX(this.getX()-delta*200);
	}
}
