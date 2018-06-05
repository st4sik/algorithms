package com.algo.assessment;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

	private LineSegment[] segments;

	public FastCollinearPoints(Point[] points) {
		checkCornerCases(points);
		Point[] pointsCopy = points.clone();
		ArrayList<LineSegment> fastSegments = new ArrayList<>();
		for (int i = 0; i < pointsCopy.length - 3; i++) {
			Arrays.sort(pointsCopy);

			Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());

			for (int p = 0, first = 1, last = 2; last < pointsCopy.length; last++) {
				while (last < pointsCopy.length &&
					Double.compare(pointsCopy[p].slopeTo(pointsCopy[first]),
					pointsCopy[p].slopeTo(pointsCopy[last])) == 0) {
					last++;
				}

				if (last - first >= 3 && pointsCopy[p].compareTo(pointsCopy[first]) < 0) {
					fastSegments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
				}
				first = last;
			}
		}
		segments = fastSegments.toArray(new LineSegment[fastSegments.size()]);
	}


	public int numberOfSegments() {
		return segments.length;
	}

	public LineSegment[] segments() {
		return Arrays.copyOf(segments, segments.length);
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

}
