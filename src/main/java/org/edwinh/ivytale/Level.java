package org.edwinh.ivytale;

import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.PhysicsComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.json.JSONArray;
import org.json.JSONObject;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Fubar on 5/3/2015.
 */
public class Level {
    public List<Entity> entities = new ArrayList<>();
    public double gravity = 1.0;

    public Level(String levelName){
        JSONArray allLevels = new JSONArray(new Scanner(ResourceLoader.getResourceAsStream("levels.json"), "UTF-8").useDelimiter("\\A").next());
        JSONObject allPieces = new JSONObject(new Scanner(ResourceLoader.getResourceAsStream("levelpieces.json"), "UTF-8").useDelimiter("\\A").next());
        for(int i = 0; i < allLevels.length(); i++){
            JSONObject thisLevel = (JSONObject) allLevels.get(i);
            if(thisLevel.getString("name").equals(levelName)){
                // load background
                if(!thisLevel.optString("background", "").isEmpty()){
                    Entity backgroundEntity = new Entity();
                    backgroundEntity.components.add(new PositionComponent(0, 0));
                    ImageComponent backgroundImageComponent = new ImageComponent(thisLevel.getString("background"));
                    backgroundImageComponent.locked = true;
                    backgroundEntity.components.add(backgroundImageComponent);
                    this.entities.add(backgroundEntity);
                }

                // load gravity
                this.gravity = thisLevel.optDouble("gravity", 1.0);

                // load pieces
                for(int j = 0; j < thisLevel.getJSONArray("pieces").length(); j++){
                    JSONObject levelPiece = (JSONObject) thisLevel.getJSONArray("pieces").get(j);
                    String levelPieceType = levelPiece.getString("type");
                    int levelPieceX = levelPiece.optInt("x", 0);
                    int levelPieceY = levelPiece.optInt("y", 0);

                    Entity levelPieceEntity = new Entity();
                    levelPieceEntity.components.add(new PositionComponent(levelPieceX, levelPieceY));
                    levelPieceEntity.components.add(new ImageComponent(allPieces.getJSONObject(levelPieceType).getString("image")));
                    levelPieceEntity.components.add(new PhysicsComponent(
                        new Rectangle(
                            allPieces.getJSONObject(levelPieceType).getJSONObject("hitbox").getInt("x"),
                            allPieces.getJSONObject(levelPieceType).getJSONObject("hitbox").getInt("y"),
                            allPieces.getJSONObject(levelPieceType).getJSONObject("hitbox").getInt("width"),
                            allPieces.getJSONObject(levelPieceType).getJSONObject("hitbox").getInt("height")
                        ),
                        true
                    ));
                    this.entities.add(levelPieceEntity);
                }
            }
        }
    }
}
