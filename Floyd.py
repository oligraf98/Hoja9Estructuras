# -*- coding: utf-8 -*-
"""
Created on Fri May 17 18:29:18 2019

@author: Odi
"""
import networkx as nx
G = nx.DiGraph()

# agegar nodos
G.add_node("Guatemala")
G.add_node("Xela")
G.add_node("Jutiapa")
G.add_node("Flores")
G.add_node("Solola")

G.add_edge("Guatemala", "Jutiapa", weight=2)
G.add_edge("Xela","Guatemala", weight=4)
G.add_edge("Jutiapa","Flores", weight=2)
G.add_edge("Xela","Jutiapa",weight=3)
G.add_edge("Flores","Xela",weight=1)
G.add_edge("Solola","Guatemala",weight=2)

print(nx.floyd_warshall_predecessor_and_distance(G,weight="weight"))