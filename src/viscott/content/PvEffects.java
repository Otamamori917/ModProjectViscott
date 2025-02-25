package viscott.content;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.Seq;
import mindustry.content.UnitTypes;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import viscott.world.pseudo3d.importedcode.DrawPseudo3d;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;

public class PvEffects {
    public static Effect
        slowEnergeticEffect, particleDeath1,particleDeath2,particleDeath3,
            railFrag,waveBulletFalerica,waveBulletJavelin,

            quadRushCraft,surgeSpawn
            ;
    public static Seq<Effect> nullisDeath = new Seq<>();
    public static void load()
    {
        slowEnergeticEffect = new Effect(32,e -> {
            color(Pal.sap);
            alpha(e.fout());
            stroke(e.fout()*4);
            circle(e.x,e.y,e.fin()*8.3f*8);
        });
        particleDeath1 = new Effect(100, e -> {
            color(Pal.sap);
            alpha(e.fout());
            stroke(e.fout()*4);
            circle(e.x,e.y,e.fin()*8);
        });
        particleDeath2 = new Effect(100, e -> {
            color(Pal.sap);
            alpha(e.fout());
            stroke(e.fout()*8);
            circle(e.x,e.y,e.fin()*16);
            circle(e.x,e.y,e.fin()*8);
        });
        particleDeath3 = new Effect(100, e -> {
            color(Pal.sap);
            alpha(e.fout());
            stroke(e.fout()*8);
            circle(e.x,e.y,e.fin()*24);
            circle(e.x,e.y,e.fin()*16);
            circle(e.x,e.y,e.fin()*8);
        });
        railFrag = new Effect(32f, e -> {
            color(Pal.sap);

            for(int i : Mathf.signs){
                Drawf.tri(e.x, e.y, 20f * e.fout(), 30f, e.rotation + 90 + 90f * i);
            }

            Drawf.light(e.x, e.y, 60f * e.fout(), Pal.orangeSpark, 0.5f);
        });
        waveBulletFalerica = new Effect(25f,e -> {
            color(Pal.lancerLaser);
            float waves = 2.5f;
            float x1 = e.x + Mathf.sin(e.rotation/180*Mathf.pi) * Mathf.sin(e.fin()*waves *Mathf.pi) * 8;
            float y1 = e.y - Mathf.cos(e.rotation/180*Mathf.pi) * Mathf.sin(e.fin()*waves *Mathf.pi) * 8;
            float x2 = e.x - Mathf.sin(e.rotation/180*Mathf.pi) * Mathf.sin(e.fin()*waves *Mathf.pi) * 8;
            float y2 = e.y + Mathf.cos(e.rotation/180*Mathf.pi) * Mathf.sin(e.fin()*waves *Mathf.pi) * 8;
            float fin = (e.time+1)/e.lifetime;
            float xn = e.x - Mathf.cos(e.rotation/180*Mathf.pi) * 16;
            float yn = e.y - Mathf.sin(e.rotation/180*Mathf.pi) * 16;
            float xn1 = xn + Mathf.sin(e.rotation/180*Mathf.pi) * Mathf.sin(fin*waves *Mathf.pi) * 8;
            float yn1 = yn - Mathf.cos(e.rotation/180*Mathf.pi) * Mathf.sin(fin*waves *Mathf.pi) * 8;
            float xn2 = xn - Mathf.sin(e.rotation/180*Mathf.pi) * Mathf.sin(fin*waves *Mathf.pi) * 8;
            float yn2 = yn + Mathf.cos(e.rotation/180*Mathf.pi) * Mathf.sin(fin*waves *Mathf.pi) * 8;
            // Fill.circle(x1,y1,e.fout()*2);
            // Fill.circle(x2,y2,e.fout()*2);
            stroke(e.fout()*2);
            line(x1, y1, xn1, yn1);
            line(x2, y2, xn2, yn2);
            // Lifetime = max lifetime || time = time alive
        });
        waveBulletJavelin = new Effect(35f,e -> {
            color(Pal.lancerLaser);
            float waves = 3f;
            float x1 = e.x + Mathf.sin(e.rotation/180*Mathf.pi) * Mathf.sin(e.fin()*waves *Mathf.pi) * 8;
            float y1 = e.y - Mathf.cos(e.rotation/180*Mathf.pi) * Mathf.sin(e.fin()*waves *Mathf.pi) * 8;
            float x2 = e.x - Mathf.sin(e.rotation/180*Mathf.pi) * Mathf.sin(e.fin()*waves *Mathf.pi) * 8;
            float y2 = e.y + Mathf.cos(e.rotation/180*Mathf.pi) * Mathf.sin(e.fin()*waves *Mathf.pi) * 8;
            float fin = (e.time+1)/e.lifetime;
            float xn = e.x - Mathf.cos(e.rotation/180*Mathf.pi) * 22;
            float yn = e.y - Mathf.sin(e.rotation/180*Mathf.pi) * 22;
            float xn1 = xn + Mathf.sin(e.rotation/180*Mathf.pi) * Mathf.sin(fin*waves *Mathf.pi) * 8;
            float yn1 = yn - Mathf.cos(e.rotation/180*Mathf.pi) * Mathf.sin(fin*waves *Mathf.pi) * 8;
            float xn2 = xn - Mathf.sin(e.rotation/180*Mathf.pi) * Mathf.sin(fin*waves *Mathf.pi) * 8;
            float yn2 = yn + Mathf.cos(e.rotation/180*Mathf.pi) * Mathf.sin(fin*waves *Mathf.pi) * 8;
            // Fill.circle(x1,y1,e.fout()*2);
            // Fill.circle(x2,y2,e.fout()*2);
            stroke(e.fout()*2);
            line(x1, y1, xn1, yn1);
            line(x2, y2, xn2, yn2);
            // Lifetime = max lifetime || time = time alive
        });
        quadRushCraft = new Effect(60,e->{
            Color col = Team.sharded.color;
            DrawPseudo3d.tube(e.x,e.y,5*e.fin(),e.fout()*50,col,Color.clear);
            Lines.stroke(e.fout()*4,col);
            Lines.circle(e.x,e.y,15*e.fin());
        });
        for(int i = 1;i<=9;i++)
            nullisDeath.add(newNullisDeath(i));
        surgeSpawn = new Effect(120,e-> {
            Color col = Team.green.color;
            float finout = (Math.abs(e.fout() - 0.5f) - 0.5f) * -2;
            Lines.stroke(finout*4,col);
            Lines.square(e.x,e.y,e.fout()*12+4,e.fout()*720);
            Lines.stroke(finout*2,col);
            Lines.square(e.x,e.y,e.fout()*18+6,e.fout()*360);
            Draw.color(col);
            Fill.circle(e.x,e.y,e.fin()*8);
        });
    }

    public static Effect newNullisDeath(int blockSize)
    {
        return new Effect(60f,e -> {
            float waves = 1;
            float size = blockSize*4;
            float wave = Math.abs(Mathf.sin(e.fin()*waves*Mathf.pi));
            Draw.z(Layer.effect);
            Draw.color(Color.white);
            Fill.circle(e.x,e.y,wave*size+1);
            Draw.z(Layer.effect+5);
            Draw.color(Color.black);
            Fill.circle(e.x,e.y,wave*size);
            Draw.reset();
        });
    }
}
