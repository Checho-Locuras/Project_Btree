package model;

import java.awt.Point;

public class Line {

	private Point init;
	private Point end;
	
	public Line(Point init, Point end) {
		super();
		this.init = init;
		this.end = end;
	}

	public Point getInit() {
		return init;
	}

	public Point getEnd() {
		return end;
	}
	
}
