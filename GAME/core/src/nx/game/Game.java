package nx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Game extends ApplicationAdapter implements InputProcessor{
	Stage princ;
	Stage gameOver;
	
	Personaje per;
	Ghost enemigo;
	Image lose;
	Plataforma arena;
	
	int tiempo;
	Rectangle perso, ene;
	boolean go = false;
	Music music;
	
	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.getFileHandle("sound.wav", FileType.Internal));
		music.setLooping(true);
		music.play();
		
		princ = new Stage();
		gameOver = new Stage();
		per = new Personaje();
		enemigo = new Ghost();
		
		lose = new Image(new Texture("over.png"));
		lose.setWidth(700);
		lose.setHeight(500);
		
		gameOver.addActor(lose);
		arena = new Plataforma(0);
		princ.addActor(arena);
		arena = new Plataforma(534);
		princ.addActor(arena);
		princ.addActor(per);
		princ.addActor(enemigo);
		Gdx.input.setInputProcessor(this);
		perso = new Rectangle(per.getX(), per.getY(), per.getWidth(), per.getHeight());
		ene = new Rectangle(enemigo.getX(), enemigo.getY(), enemigo.getWidth(), enemigo.getHeight());
	}

	@Override
	public void render () {
		perso.set(per.getX(), per.getY(), per.getWidth(), per.getHeight());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (go || validarColision()){
			gameOver.act();
			gameOver.draw();
			go = true;
			music.stop();
		}else{
			princ.act();
			princ.draw();
			jump();
			createEnemies();
			createArena();
			tiempo++;
		}
	}
	
	private boolean validarColision() {
		ene.set(enemigo.getX(), enemigo.getY(), enemigo.getWidth(), enemigo.getHeight());
		if (ene.overlaps(perso))
			return true;
		return false;
	}

	void jump(){
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isTouched()){
			per.saltar();
		}
	}
	
	void createEnemies(){
		if (tiempo>0 && tiempo%88==0){
			enemigo = new Ghost();
			princ.addActor(enemigo);
		}
	}
	
	void createArena(){
		float x = arena.getX();
		if (x<300){
			arena = new Plataforma(x+534);
			princ.addActor(arena);
		}
			
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
