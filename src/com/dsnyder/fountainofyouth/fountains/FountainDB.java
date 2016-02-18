package com.dsnyder.fountainofyouth.fountains;

import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;

import com.dsnyder.fountainofyouth.FountainOfYouth;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class FountainDB {
	
	private List<Fountain> fountains;
	private FileChannel db;
	
	@SuppressWarnings("resource")
	public FountainDB() {
		fountains = new ArrayList<>();
		
		try {
			// initialize our file channel in such a way that it will be persistent between memory and disk
			// and we can seek specific fountains
			
			File file = new File(FountainOfYouth.getPlugin().getDataFolder(), "fountain_data.db");
			
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			
			if (!file.exists())
				file.createNewFile();
			
			RandomAccessFile aFile = new RandomAccessFile(file, "rw");
			db = aFile.getChannel();
			
			if (db.size() > 0) {
				ByteBuffer buf = ByteBuffer.allocate(new Fountain(new Location(null, 0, 0, 0)).stringifyLength());
				while (db.position() != db.size()-1) {
					db.read(buf);
					fountains.add(Fountain.unstringify(new String(buf.array())));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Fountain> getFountainsInChunk(Chunk chunk) {
		
		List<Fountain> fnts = new ArrayList<>();
		
		boolean found = false;
		
		while (!found) {
			break;
		}
		
		for (int i=0; i<fountains.size(); i++) {
			if (fountains.get(i).getLocation().getChunk().equals(chunk)) {
				fnts.add(fountains.get(i));
			}
		}
		
		return fnts;
	}
	
	public void save(Fountain fountain) {
		try {
			db.position(db.size());
			db.write(ByteBuffer.wrap(fountain.stringify().getBytes()));
			fountains.add(fountain);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Fountain[] getFountains() {
		return fountains.toArray(new Fountain[fountains.size()]);
	}
}
