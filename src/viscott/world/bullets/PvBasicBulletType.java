package viscott.world.bullets;

import arc.util.Nullable;
import arc.util.Time;
import mindustry.ai.types.MissileAI;
import mindustry.entities.Mover;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.game.Team;
import mindustry.gen.Bullet;
import mindustry.gen.Entityc;
import mindustry.gen.Unit;
import mindustry.gen.Velc;
import mindustry.world.blocks.ControlBlock;

import static mindustry.Vars.net;
import static mindustry.Vars.world;

public class PvBasicBulletType extends BasicBulletType {
    public PvBasicBulletType(float speed, float damage)
    {
        super(speed,damage);
    }

    @Override
    public @Nullable Bullet create(@Nullable Entityc owner, Team team, float x, float y, float angle, float damage, float velocityScl, float lifetimeScl, Object data, @Nullable Mover mover, float aimX, float aimY){
        if(spawnUnit != null){
            //don't spawn units clientside!
            if(!net.client()){
                Unit spawned = spawnUnit.create(team);
                spawned.set(x, y);
                spawned.rotation = angle;
                //immediately spawn at top speed, since it was launched
                if(spawnUnit.missileAccelTime <= 0f){
                    spawned.vel.trns(angle, spawnUnit.speed);
                }
                //assign unit owner
                if(spawned.controller() instanceof MissileAI ai){
                    if(owner instanceof Unit unit){
                        ai.shooter = unit;
                    }

                    if(owner instanceof ControlBlock control){
                        ai.shooter = control.unit();
                    }

                }
                spawned.add();
            }

            //no bullet returned
            return null;
        }

        Bullet bullet = PvBullet.pvCreate();
        bullet.type = this;
        bullet.owner = owner;
        bullet.team = team;
        bullet.time = 0f;
        bullet.originX = x;
        bullet.originY = y;
        bullet.aimTile = world.tileWorld(aimX, aimY);
        bullet.aimX = aimX;
        bullet.aimY = aimY;
        bullet.initVel(angle, speed * velocityScl);
        if(backMove){
            bullet.set(x - bullet.vel.x * Time.delta, y - bullet.vel.y * Time.delta);
        }else{
            bullet.set(x, y);
        }
        bullet.lifetime = lifetime * lifetimeScl;
        bullet.data = data;
        bullet.drag = drag;
        bullet.hitSize = hitSize;
        bullet.mover = mover;
        bullet.damage = (damage < 0 ? this.damage : damage) * bullet.damageMultiplier();
        //reset trail
        if(bullet.trail != null){
            bullet.trail.clear();
        }
        bullet.add();

        if(keepVelocity && owner instanceof Velc v) bullet.vel.add(v.vel());
        return bullet;
    }
}
