package task4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import task4.Node;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		List<Node> explorer = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explorer.add(current);
			if (current.getLabel().equals(goal)) {
				return current;
			}

			List<Node> list = current.getChildrenNodes();
				for (int i = 0; i < list.size(); i++) {
					Node node = list.get(i);
					Node newNode = new Node(node);
					newNode.setParent(current);
					newNode.setPathCost(newNode.getPathCost() + current.getPathCost());
					System.out.println(newNode.getPathCost());
					for (Node nodeInFrontier : frontier) {
						if (nodeInFrontier.getLabel().equals(newNode.getLabel())
								&& nodeInFrontier.getPathCost() > newNode.getPathCost()) {
							frontier.remove(nodeInFrontier);
							frontier.add(newNode);
break;
						}
					}
					if (!explorer.contains(newNode) && !frontier.contains(newNode)) {
						frontier.add(newNode);
					}
			}
			System.out.println(frontier);

		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		List<Node> explorer = new ArrayList<>();
		frontier.add(root);
		boolean flag =  false;
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explorer.add(current);
			for (Node node : explorer) {
				if(node.getLabel().equals(start)) {
					flag = true;
				}
			}
			if (current.getLabel().equals(goal) && flag) {
				return current;
			}

			List<Node> list = current.getChildrenNodes();
				for (int i = 0; i < list.size(); i++) {
					Node node = list.get(i);
					if(node.getLabel().equals(start)) {
						frontier.clear();
						frontier.add(node);
						break;
					}
					Node newNode = new Node(node);
					newNode.setParent(current);
					newNode.setPathCost(newNode.getPathCost() + current.getPathCost());
					for (Node nodeInFrontier : frontier) {
						if (nodeInFrontier.getLabel().equals(newNode.getLabel())
								&& nodeInFrontier.getPathCost() > newNode.getPathCost()) {
						frontier.remove(nodeInFrontier);
						frontier.add(newNode);
							break;
						}
					}
					if (!explorer.contains(newNode) && !frontier.contains(newNode)) {
						frontier.add(newNode);
					}
			}
			System.out.println(frontier);

		}

		return null;
	}


}
