import numpy as np




X=[]
y=[]
data=open("mm.csv").read()
for row in data.split():
    minD,maxD,mmType=row.split(",")
    minD=float(minD)
    maxD=float(maxD)
    mmType=int(mmType)
    X.append([minD,maxD])
    y.append(mmType)


from sklearn.utils import shuffle
X,y=shuffle(X,y)

from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
scaler.fit(X)
X = scaler.transform(X)



from sklearn.neural_network import MLPClassifier

# Do a little test to see if you can get above chance.
# ~ NN=MLPClassifier()
# ~ NN.fit(X,y)
# ~ print(NN.score(X,y)) #train score 

# Playing around with parameters
# ~ NN = MLPClassifier(hidden_layer_sizes=[10]) #hidden_layer_sizes=[10],activation="identity",solver="lbfgs",max_iter=10000
# ~ from sklearn.model_selection import GridSearchCV
# ~ parameters = {"hidden_layer_sizes":[i for i in range(1,26)]}
# ~ gridSearch = GridSearchCV(NN, parameters,cv=2,verbose=10,n_jobs=-1)
# ~ gridSearch.fit(X,y)
# ~ testScores=gridSearch.cv_results_["mean_test_score"]
# ~ print(testScores)
# ~ np.savetxt("test_scores.csv", testScores)



NN = MLPClassifier(hidden_layer_sizes=[10])
from sklearn.model_selection import cross_val_score
r=cross_val_score(NN,X,y,cv=2)
print(r)


#no first person
# ~ Intro Paragraph
# ~ I want use a nn to classify a mm as peanut or peanutbutter when found in a mixed.
# ~ -Practical application
# ~ -formalize and remove first person

# ~ Data Acquisition (>=1 example of some labeled data)
# ~ I purchased two bags of the diff types. I measure 20 of each.  For each I
# ~ measure the min and max dimension.  <Include a diagram show how I measure the min and max>


# ~ Justification of NN Design (>=1 graph)
# ~ Explored diff layer size. <graph> talk about the graph.  Pick 10 because it was first round
# ~ number where it did maximally well.


# ~ Benchmark your NN
# ~ Accuracy.  Wring and figure 1 more thing to talk about.

# ~ WrapUp (Thoughts, Future Research)
# ~ Work well.  sample could be increased. More types MM. 

