package org.hashcode.magicunicorn.qualification.round;

public class Book {

	private final int id;
	private final int score;
	private final Data data;

	public Book(int id, int score, Data data) {
		this.id = id;
		this.score = score;
		this.data = data;
	}

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Data getData() {
        return data;
    }
	
	

}
