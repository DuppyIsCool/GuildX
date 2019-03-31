package me.Dupps.GuildX.Chunks;

public class Chunks {
	private final int x,z;
	private final String owner;
	
	public Chunks(int x, int z,String owner) {
		this.x = x;
		this.z = z;
		this.owner = owner;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getZ() {
		return this.z;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public String toString() {
		return "Chunk("+this.x+","+this.z+")";
	}
	
}
