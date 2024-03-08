package org.example.Objects;

public class Block {
    private int id;
    private String name;
    private double hardness, blastResist;
    private boolean gravityAffected;

    public Block(int id, String name, double hardness, double blastResist, boolean gravityAffected) {
        this.id = id;
        this.name = name;
        this.hardness = hardness;
        this.blastResist = blastResist;
        this.gravityAffected = gravityAffected;
    }
}
