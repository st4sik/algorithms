package com.algo.assessment;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

	private LineSegment[] segments;

	public BruteCollinearPoints(Point[] points) {
		checkCornerCases(points);
		ArrayList<LineSegment> bruteSegments = new ArrayList<>();
		Point[] pointsCopy = Arrays.copyOf(points, points.length);
		Arrays.sort(pointsCopy);
		for (int p = 0; p < pointsCopy.length - 3; p++) {
			for (int q = p + 1; q < pointsCopy.length - 2; q++) {
				for (int r = q + 1; r < pointsCopy.length - 1; r++) {
					for (int s = r + 1; s < pointsCopy.length; s++) {
						//To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes
						// between p and q, between p and r, and between p and s are all equal.
						if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) &&
							pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
							bruteSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
						}
					}
				}
			}
		}
		segments = bruteSegments.toArray(new LineSegment[bruteSegments.size()]);
	}

	private void checkCornerCases(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException("Argument is null");
		}

		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new IllegalArgumentException("Point is null");
			}
		}

		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) {
					throw new IllegalArgumentException("Duplicated between 2 points");
				}
			}
		}
	}

	public int numberOfSegments() {
		return segments.length;
	}

	public LineSegment[] segments() {
		return Arrays.copyOf(segments, segments.length);
	}


}
