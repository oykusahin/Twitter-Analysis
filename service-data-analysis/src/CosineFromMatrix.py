import pandas as pd
import operator
import re
from re import sub
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import json
from os.path import isfile, join
from os import listdir
import numpy as np

import matplotlib.pylab as plt
import scipy.cluster.hierarchy as shc

from scipy.cluster.hierarchy import fcluster

from sklearn.cluster import AgglomerativeClustering
from scipy.spatial.distance import pdist
from scipy.spatial.distance import squareform
from numpy import genfromtxt

UserStringArray=[] 
Users=[] 

# cosine distance ile ilgili methodlar
def get_cosine_sim(*strs):
    vectors = [t for t in get_vectors(*strs)]
    return round(cosine_similarity(vectors)[0][1],8)

def get_vectors(*strs):
    text = [t for t in strs]
    vectorizer = CountVectorizer(analyzer = "word",
                                 tokenizer = None,
                                 preprocessor = None,
                                 stop_words = None,
                                 max_features = 5000,
                                 lowercase=True,
                                 token_pattern = r"(?u)\b\w+\b")
    vectorizer.fit(text)
    return vectorizer.transform(text).toarray()


Matrix = genfromtxt('/Users/mertyenilmez/Desktop/senior/similarityMatrix.csv', delimiter=',')

y = pdist(Matrix)
listUserNames=[]

# agglomerative clustering
cluster = AgglomerativeClustering(affinity='euclidean', n_clusters=6, linkage='ward').fit(Matrix)
print(squareform(y))
plt.scatter(Matrix[:, 0],Matrix[:, 1], c=cluster.labels_, cmap='gist_rainbow')
listClusterInfo=cluster.labels_ #hangi cluster da oldukları bilgisi
plt.show()


ListsimilarityInfo = genfromtxt('/Users/mertyenilmez/Desktop/senior/AdvertisementSimilarity.csv', delimiter=',')
f = open("/Users/mertyenilmez/Desktop/senior/similarityMatrix.txt", encoding="utf-8")
for line in f:
    lineSplit = line.split()
    listUserNames.append(lineSplit[1][1:-4])

istClusterInfo_s, ListsimilarityInfo_s, listUserNames_s, = map(list, zip(*sorted(zip(listClusterInfo, ListsimilarityInfo, listUserNames), reverse=True)))

df = pd.DataFrame(zip(*sorted(zip(listClusterInfo, ListsimilarityInfo, listUserNames), reverse=True)))
df.to_csv('/Users/mertyenilmez/Desktop/senior/ZippedSimilarityUserAdvertisementClusters.csv', index=False)

# dendrogram plotter
plt.figure(figsize=(8, 6))
plt.title("User Dendograms")
plt.xlabel('USERS')
plt.ylabel('SIMILARITY')
dend = shc.dendrogram(shc.linkage(Matrix, method='ward'))
plt.show()