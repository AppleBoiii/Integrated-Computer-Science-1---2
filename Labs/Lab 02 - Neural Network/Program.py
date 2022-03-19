import numpy as np
import pandas as pd
import sklearn.preprocessing
from sklearn.neural_network import MLPClassifier
from sklearn.utils import shuffle
from sklearn.model_selection import cross_val_score, GridSearchCV

#READING IN DATA
data = pd.read_csv('C:\\Users\\staln\\OneDrive\\Desktop\\CP\\Integrated CS\\Labs\\Lab 02 - Neural Network\\data.csv')
x = data.loc[:, ['age', 'sex', 'cp', 'trestbps', 'chol', 'fbs', 'thalach', 'exang']].to_numpy() #gets all the rows, gets specified columns
y = data.loc[:, ['target']].to_numpy().ravel()

#STANDARDIZING DATA
scaler = sklearn.preprocessing.StandardScaler()
x_normalized = scaler.fit_transform(x)

#SHUFFLING
x_normalized, y = shuffle(x_normalized, y)

#FIND BEST PARAMS
# NN = MLPClassifier()
# parameters = {"random_state" : [1, 2, 4, 8, 16, 20, 40, 80, 160]}
# clf = (GridSearchCV(NN, parameters, verbose=5, cv=3, n_jobs=-1))
# clf.fit(x_normalized, y)
# tests = clf.cv_results_
# tests = pd.DataFrame.from_dict(tests)
# tests = tests.loc[:, ['mean_test_score']]
# print(tests)
#tests.to_csv('random_state.csv')


NN = MLPClassifier(hidden_layer_sizes = 10, activation = 'identity', solver = 'adam', learning_rate = 'constant', random_state = 80, max_iter=10000)

half = int(len(x)/2)
NN.fit(x_normalized[:half], y[:half])
print(NN.predict(x_normalized[-10:]))
print(y[-10:])


print("train score",NN.score(x_normalized[:half],y[:half]))
print("test score",NN.score(x_normalized[-half:],y[-half:]))

r = cross_val_score(NN, x_normalized, y, cv = 2)
print(r)






