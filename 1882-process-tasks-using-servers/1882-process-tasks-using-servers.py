import heapq
from typing import List

class Solution:
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        free_servers = [(servers[i], i) for i in range(len(servers))]
        heapq.heapify(free_servers)
        
        busy_servers = []  # stores (available_time, weight, index)
        ans = []
        current_time = 0
        
        for j, task_time in enumerate(tasks):
            current_time = max(current_time, j)
            
            while busy_servers and busy_servers[0][0] <= current_time:
                _, weight, idx = heapq.heappop(busy_servers)
                heapq.heappush(free_servers, (weight, idx))
                
            if not free_servers:
                current_time = busy_servers[0][0]
                while busy_servers and busy_servers[0][0] <= current_time:
                    _, weight, idx = heapq.heappop(busy_servers)
                    heapq.heappush(free_servers, (weight, idx))
            
            weight, idx = heapq.heappop(free_servers)
            ans.append(idx)
            heapq.heappush(busy_servers, (current_time + task_time, weight, idx))
            
        return ans
        