import pandas as pd
import operator
import re
import os
from re import sub
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import json
from os import listdir
from os.path import isfile, join

AD_TEST = "Mühendislik Fakültesi - Makine Mühendisliği Bölümü Ar Gör Kadrosu Nihai Değerlendirme Sonuçları Mühendislik Fakültesi Makine Mühendisliği Bölümü Ar Gör Kadrosu Nihai Değerlendirme Sonuçlarını PDF olarak görüntülemek için tıklayınız"
target_dir= os.getcwdb()
totalcosine = 0.0
dictcosine =	{}

UserStringArray=[] 
Users=[] 
similarity=[]

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

onlyfiles = [f for f in listdir(target_dir+ '/VectorResults') if isfile(target_dir+ '/VectorResults', f)]

for j in onlyfiles:
    with open(target_dir+ '/VectorResults/', encoding='utf-8',errors="ignore") as f:
        UserStringArray.append(str(f.read()))
        Users.append(j)

for x in range(len(Users)):
    similarity.append(get_cosine_sim(r, UserStringArray[x]))

print(similarity)
df = pd.DataFrame(similarity)
df.to_csv(target_dir+ '/AdvertisementSimilarity.csv', index=False)