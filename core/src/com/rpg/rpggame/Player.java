package com.rpg.rpggame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
//import com.badlogic.gdx.utils.Json.Serializable;
import java.io.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    Vector2 position;
    String textureLoc;
    Texture texture;

    public Player(Vector2 position, String textureLoc){
        this.position = position;
        texture = new Texture("maps/reception.png");
    }

    public void update(){
        //wsad input
        /*if (Gdx.input.isKeyPressed(Keys.W)){
            position.y +=  2f;
        }
        if (Gdx.input.isKeyPressed(Keys.A)){
            position.x -=  2f;
        }
        if (Gdx.input.isKeyPressed(Keys.D)){
            position.x +=  2f;
        }
        if (Gdx.input.isKeyPressed(Keys.S)){
            position.y -=  2f;
        }

        if (Gdx.input.isTouched()){
            System.out.println("Touched!");
        }*/

        //Accelerometer input



/*      float accelX = Gdx.input.getAccelerometerX();
        float accelY = Gdx.input.getAcceleromete java.io.Serializable;rY();


        if (accelX > -1){
            position.y -=  3f;
        }
        if (accelX < +1){
            position.y +=  3f;
        }

        if Texture(accelY > -1){
            position.x +=  3f;
        }
        if (accelY < +1){
            position.x -=  3f;
        }
        */

        /*float accelX = Gdx.input.getAccelerometerX();
        float accelY = Gdx.input.getAccelerometerY();

        if (accelX < -1){
            position.y -=  accelX;
        }
        if (accelX > +1){
            position.y -=  accelX;
        }

        if (accelY < -1){
            position.x +=  accelY;
        }
        if (accelY > +1){
            position.x +=  accelY;
        }


        if (accelX > 3){
            System.out.println("x axis is " + accelX);
        }

        if (accelX < -3){
            System.out.println("x axis is " + accelX);
        }

        if (accelY > 3){
            System.out.println("y axis is " + accelX);
        }

        if (accelY < -3){
            System.out.println("y axis is " + accelX);
        }
*/
        //System.out.println("y axis is " + accelY);
        //position.x = Gdx.input.getX();
        //position.y = Gdx.input.getY();
        if(Gdx.input.isTouched())
        {
            position.x = Gdx.input.getY();
            position.y = Gdx.input.getX();
        }
    }

    public static void savePlayer(Player marioPlayer) throws IOException {
        FileHandle file = Gdx.files.local("player.dat");
        OutputStream out = null;
        try {
            file.writeBytes(serialize(marioPlayer), false);
        }catch(Exception ex){
            System.out.println(ex.toString());
        }finally{
            if(out != null) try{out.close();} catch (Exception ex){}
        }

        System.out.println("Saving Player");
    }

    public static Player readPlayer() throws IOException, ClassNotFoundException {
        Player marioPlayer = null;
        FileHandle file = Gdx.files.local("player.dat");
        marioPlayer = (Player) deserialize(file.readBytes());
        return marioPlayer;
    }

    @SuppressWarnings("unused")
    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }

    public  Vector2 getPosition() {
        // TODO Auto-generated method stub
        return position;
    }

    public void setPosition(){
        this.position = position;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public void setTexture(Texture texture){
        this.texture = texture;
    }

}
