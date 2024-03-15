package DTOs;

/** Base code taken from oop-data-access-layer-sample-1
 *  Rewritten by Jakub Polacek
*/

public class Block
{
    private int id;
    private String name;
    private double hardness;
    private double blastResistance;
    private boolean gravityAffected;

    public Block(int id, String name, double hardness, double blastResistance, boolean gravityAffected)
    {
        this.id = id;
        this.name = name;
        this.hardness = hardness;
        this.blastResistance = blastResistance;
        this.gravityAffected = gravityAffected;
    }

    public Block(String name, double hardness, double blastResistance, boolean gravityAffected)
    {
        this.id = 0;
        this.name = name;
        this.hardness = hardness;
        this.blastResistance = blastResistance;
        this.gravityAffected = gravityAffected;
    }

    public Block()
    {}

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public double getHardness()
    {
        return hardness;
    }
    public void setHardness(double hardness)
    {
        this.hardness = hardness;
    }
    public double getBlastResistance()
    {
        return blastResistance;
    }
    public void setBlastResistance(double blastResistance)
    {
        this.blastResistance = blastResistance;
    }
    public boolean isGravityAffected()
    {
        return gravityAffected;
    }
    public void setGravityAffected(boolean gravityAffected)
    {
        this.gravityAffected = gravityAffected;
    }

    @Override
    public String toString()
    {
        return "Block{id=" + id +
                ", name='" + name +
                "', hardness=" + hardness +
                ", blastResistance=" + blastResistance +
                ", gravityAffected=" + gravityAffected + "}";
    }
}