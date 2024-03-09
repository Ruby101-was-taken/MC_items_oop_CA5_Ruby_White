package org.example.Objects;

// started by Ruby 9/3/2024 :3

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

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hardness=" + hardness +
                ", blastResist=" + blastResist +
                ", gravityAffected=" + gravityAffected +
                '}';
    }
}
