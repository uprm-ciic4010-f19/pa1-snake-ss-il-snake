package Game.Entities.Dynamic;

import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.Entities.Static.Apple;
import Game.GameStates.State;

/***
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

    public int lenght;
    public boolean justAte;
    private Handler handler;

    public int xCoord;
    public int yCoord;

    public int moveCounter;
    public int speed;
    public int lastIdNum = 2;
    
    public static String score="0";

    public String direction;//is your first name one?

    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = 0;
        direction= "Right";
        justAte = false;
        lenght= 1;
        speed = 9;

    }

    public void tick(){
    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {//Pause Game
    		State.setState(handler.getGame().pauseState);
    	}
        moveCounter++;
        if(moveCounter>=speed) {
            checkCollisionAndMove();
            checkCollisionAndKill();
            moveCounter=0;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)&& !direction.equalsIgnoreCase("Down")){//No Back
            direction="Up";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)&& !direction.equalsIgnoreCase("Up")){//No Back
            direction="Down";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)&& !direction.equalsIgnoreCase("Right")){//No Back
            direction="Left";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)&& !direction.equalsIgnoreCase("Left")){//No Back
            direction="Right";
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)) {//Speeds up
        	speed= speed - 3;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)) {//Slows down
        	speed= speed + 3;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {//Adds Tail
        	lenght++;
            Tail tail= null;
            handler.getWorld().appleLocation[xCoord][yCoord]=false;
            handler.getWorld().appleOnBoard=true;
            switch (direction){
                case "Left":
                    if( handler.getWorld().body.isEmpty()){
                        if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                            tail = new Tail(this.xCoord+1,this.yCoord,handler);
                        }else{
                            if(this.yCoord!=0){
                                tail = new Tail(this.xCoord,this.yCoord-1,handler);
                            }else{
                                tail =new Tail(this.xCoord,this.yCoord+1,handler);
                            }
                        }
                    }else{
                        if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                            tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                        }else{
                            if(handler.getWorld().body.getLast().y!=0){
                                tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                            }else{
                                tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                            }
                        }

                    }
                    break;
                case "Right":
                    if( handler.getWorld().body.isEmpty()){
                        if(this.xCoord!=0){
                            tail=new Tail(this.xCoord-1,this.yCoord,handler);
                        }else{
                            if(this.yCoord!=0){
                                tail=new Tail(this.xCoord,this.yCoord-1,handler);
                            }else{
                                tail=new Tail(this.xCoord,this.yCoord+1,handler);
                            }
                        }
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            if(handler.getWorld().body.getLast().y!=0){
                                tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                            }else{
                                tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                            }
                        }

                    }
                    break;
                case "Up":
                    if( handler.getWorld().body.isEmpty()){
                        if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                            tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                        }else{
                            if(this.xCoord!=0){
                                tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                            }else{
                                tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                            }
                        }
                    }else{
                        if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }else{
                            if(handler.getWorld().body.getLast().x!=0){
                                tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                            }else{
                                tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                            }
                        }

                    }
                    break;
                case "Down":
                    if( handler.getWorld().body.isEmpty()){
                        if(this.yCoord!=0){
                            tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                        }else{
                            if(this.xCoord!=0){
                                tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                            }else{
                                tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                            } System.out.println("Tu biscochito");
                        }
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            if(handler.getWorld().body.getLast().x!=0){
                                tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                            }else{
                                tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                            }
                        }

                    }
                    break;
            }
            handler.getWorld().body.addLast(tail);
            handler.getWorld().playerLocation[tail.x][tail.y] = false;
        }
        	   
        	
    
        
    }
    public void checkCollisionAndKill() {
    for (Tail Body : handler.getWorld().body) {
    	if(this.xCoord == Body.x && this.yCoord== Body.y) {
    		kill();
    	}}
		
    }
    
    
    
    
    
    
    public void checkCollisionAndMove(){
        handler.getWorld().playerLocation[xCoord][yCoord]=false;//Head
        int x = xCoord;
        int y = yCoord;
        switch (direction){
            case "Left":
                if(xCoord==0){
                    this.xCoord=59; //Teleport to other side
                }else{
                    xCoord--;
                }
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                	this.xCoord=0;
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                	this.yCoord=59;
                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                	this.yCoord=0;
                }else{
                    yCoord++;
                }
                break;
        }
        handler.getWorld().playerLocation[xCoord][yCoord]=true;


        if(handler.getWorld().appleLocation[xCoord][yCoord]){
            Eat();
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));
        }

    }

    public void render(Graphics g,Boolean[][] playeLocation){
        Random r = new Random();
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
                g.setColor(Color.GREEN);//Snake Color

                if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }

            }
        }


    }
    int IdNum = 2;
        public void Eat(){
    	int temp = Integer.valueOf(score);
    	temp= (int) Math.sqrt((2*temp) +1)+ temp;
    	score = Integer.toString(temp);
    	if(speed>0) {// Speeds up when eats and caps at set speed
    		speed = speed - (lastIdNum + 1);
    	}
        lenght++;
        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=false;
        switch (direction){
            case "Left":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=0){
                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=0){
                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
        }
        handler.getWorld().body.addLast(tail);
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
    }
    
    public void kill(){
        lenght = 0;
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;

            }
        }
        State.setState(handler.getGame().deathState);
    }

    public boolean isJustAte() {
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }

	
}
