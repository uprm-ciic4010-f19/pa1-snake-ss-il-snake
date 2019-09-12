package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

import Game.Entities.Dynamic.Player;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class DeathState extends State {

    private int count = 0;
    private UIManager uiManager;

    public DeathState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


        uiManager.addObjects(new UIImageButton(56, (223+(64+16))+(64+16), 128, 64, Images.BTitle, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));





    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pbutt && count>=30){
            count=0;

            State.setState(handler.getGame().gameState);
        }


    }

    @Override
    public void render(Graphics g) {// Display Score on death
        g.drawImage(Images.title,0,0,800,600,null);
        uiManager.Render(g);
        g.setColor(Color.black);
        g.setFont(new Font("Bauhaus 93", Font.ROMAN_BASELINE, 50));
        g.drawString("Score: " + Player.score, 280, 400);
        g.drawString("Game Over", 280, 300);
    }
        
    
}
