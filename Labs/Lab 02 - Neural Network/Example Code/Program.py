import pandas
#https://archive.ics.uci.edu/ml/index.php
data=pandas.read_csv("abalone.data")
X=data.loc[:, ['H', 'D', 'L',"Whole","Shucked","Viscera","Shell","Rings"]].to_numpy()
X[:,-1]/=20
y=data.loc[:, ['Sex']].to_numpy()
y=y.ravel()
good=y!="I"
X=X[good]
y=y[good]

#get rid of I
# ~ temp=y!='I'
# ~ X=X[temp[None,:]]
# ~ y=y[temp]

from sklearn.neural_network import MLPClassifier

clf = MLPClassifier(hidden_layer_sizes=[100,100])
clf.fit(X[:-1000], y[:-1000])
print(clf.predict(X[:5]))
print(y[:5])
print("train score",clf.score(X[:-1000],y[:-1000]))
print("test score",clf.score(X[-1000:],y[-1000:]))




