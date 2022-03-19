#make a bunch of images with different letter and different fonts
#turn images into 7 numbers for my X array
#turn the matching letters into a y array

import PIL
from PIL import ImageFont
from PIL import Image
from PIL import ImageDraw
import numpy as np
import cv2
import random

# font = ImageFont.truetype("Arial-Bold.ttf",14)

def generate(font,letter):
    size=random.randint(18,24)
    font = ImageFont.truetype(font+".ttf",size)
    img=Image.new("L", (32,32),0)
    draw = ImageDraw.Draw(img)
    draw.text((1, 0),letter,255,font=font)
    draw = ImageDraw.Draw(img)
    img = np.array(img)
    # ~ for i in range(30):
        # ~ x=random.randrange(32)
        # ~ y=random.randrange(32)
        # ~ img[x,y]=255
        
    x=cv2.HuMoments(cv2.moments(img)).flatten()
    x=list(x)
    x+=list(cv2.moments(img).values())
    return x


X=[]
y=[]
for letter in "abcdefghijklmnopqrstuvwxyz":
    for i in range(10):
        x=generate("times",letter)
        X.append(x)
        y.append(letter)

from sklearn.neural_network import MLPClassifier
import sklearn.preprocessing
X=np.array(X) #can't use because of unnormalized data
y=np.array(y)
scaler = sklearn.preprocessing.StandardScaler()
scaler.fit(X) #train up a scaler to put data into good range
X_normalized = scaler.transform(X)

from sklearn.utils import shuffle

X_normalized,y=shuffle(X_normalized,y)


NN = MLPClassifier(hidden_layer_sizes=[10],activation="identity",solver="lbfgs",max_iter=10000)
# ~ NN = MLPClassifier(hidden_layer_sizes=[10],activation="identity",solver="lbfgs",max_iter=10000)
# ~ from sklearn.model_selection import GridSearchCV
# ~ parameters = {"learning_rate":["constant","invscaling","adaptive"]}
# ~ clf = GridSearchCV(NN, parameters,cv=3,verbose=10,n_jobs=-1)
# ~ clf.fit(X_normalized,y)
# ~ #print(result)
# ~ print(clf.cv_results_)
from sklearn.model_selection import cross_val_score
r=cross_val_score(NN,X_normalized,y,cv=3)
print(r)


#confusion matrix
from sklearn.metrics import confusion_matrix
NN.fit(X_normalized[:-250],y[:-250])
cm=confusion_matrix(y[-250:], NN.predict(X_normalized[-250:]),labels=list("abcdefghijklmnopqrstuvwxyz"))
print(cm)
# ~ for letter,row in zip("abcdefghijklmnopqrstuvwxyz",cm):
    # ~ print(letter,row)


# ~ from sklearn.model_selection import cross_val_score
# ~ scores = cross_val_score(clf, X_normalized, y, cv=5)
# ~ print(scores)
