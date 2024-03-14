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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHardness() {
        return hardness;
    }

    public void setHardness(double hardness) {
        this.hardness = hardness;
    }

    public double getBlastResist() {
        return blastResist;
    }

    public void setBlastResist(double blastResist) {
        this.blastResist = blastResist;
    }

    public boolean isGravityAffected() {
        return gravityAffected;
    }

    public void setGravityAffected(boolean gravityAffected) {
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
