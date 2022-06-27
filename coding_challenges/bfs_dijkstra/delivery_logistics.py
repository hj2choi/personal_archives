# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean
import heapq

graph = []
nodes_cnt = int(input())

for i in range(nodes_cnt):
	try:
		graph.append([int(s) for s in input().split(' ')])
	except EOFError:
		break
#print(graph)


def dijkstra(graph, src):
	visited = {node: float('inf') for node in range(nodes_cnt)}
	visited[src] = 0
	queue = []
	heapq.heappush(queue, [0, src])

	while queue:
		curr_dist, curr_dest = heapq.heappop(queue)
		#print("popped", curr_dest, "with", curr_dist)

		if visited[curr_dest] < curr_dist:
			continue

		for e in graph:
			next_dest, next_dist = -1, -1
			if e[0] == curr_dest:
				next_dest = e[1]
				next_dist = e[2]
			elif e[1] == curr_dest:
				next_dest = e[0]
				next_dist = e[2]
			else:
				continue

			dist = curr_dist + next_dist
			if dist < visited[next_dest]:
				visited[next_dest] = dist
				heapq.heappush(queue, [dist, next_dest])
	#print(visited)
	return sum(visited.values())

min_cost = float("inf")
for i in range(nodes_cnt):
	curr_cost = dijkstra(graph, i)
	if curr_cost < min_cost:
		min_cost = curr_cost
print(min_cost)