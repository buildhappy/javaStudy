package com.buildhappy.jvm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CodeTest {
	public HashMap<Integer, LinkedList<Node>> getDistMatrix(String fileName,HashMap<Integer, ArrayList<Integer>> clusterSet) {

		HashMap<Integer, LinkedList<Node>> dist_matrix = new HashMap<Integer, LinkedList<Node>>(2173370);
		try {
			File myFile = new File(fileName);
			FileReader reader = new FileReader(myFile);
			BufferedReader breader = new BufferedReader(reader);

			System.out.println("start to load the distance matrix to memory . ");
			String line = null;
			int index = 0;
			LinkedList<Node> top50 = null;
			int userA = -1;
			while ((line = breader.readLine()) != null) {
				if (index % 1000000 == 0) {
					System.out.println("have loading " + index + " lines .");
				}
				if (index % 50 == 0) {
					top50 = new LinkedList<Node>();
					String[] tokens = line.split(",");
					ArrayList<Integer> set = new ArrayList<Integer>();
					userA = Integer.parseInt(tokens[0]);
					set.add(userA);
					clusterSet.put(userA, set);
				}

				String[] tokens = line.split(",");
				int userB = Integer.parseInt(tokens[1]);
				int distance = Integer.parseInt(tokens[2]);
				Node node = new Node(userB, distance);
				// HashMap<Integer, LinkedList<Node>> top50;
				top50.addLast(node);
				if (index % 50 == 49) {
					// System.out.println("the length of top50 is : "+top50.size());
					dist_matrix.put(userA, top50);
				}
				index++;
			}

			breader.close();
			System.out.println("finish getting the distance matrix . ");
		} catch (Exception x) {
			x.printStackTrace();
		}

		return dist_matrix;
	}

	class Node implements Comparable<Node> {

		Integer imsi;
		Integer dist;

		public int compareTo(Node s) {
			return dist.compareTo(s.getDist());
		}

		public Node() {
			imsi = -1;
			dist = Integer.MAX_VALUE;
		}

		public Node(Integer imsiNo, int distance) {
			imsi = imsiNo;
			dist = distance;
		}

		public int getDist() {
			return dist;
		}

		public Integer getImsi() {
			return imsi;
		}

		public void setImsi(Integer _imsi) {
			imsi = _imsi;
		}
	}
}