# Deploying a python model as a service and calling it from a Java program

In this small project, I deploy Muse (Multilingual Universal Sentence Encoder) as a service in python using Flask and I request it from Java. 

The python service transforms a sentence (16 languages) into an embedding (vector).

## Requirements 

Tested on Windows 7, with python 3.6 and java 1.8

## Running the python service

1) Clone the repository

2) Go to the repository root in your computer

3) Create a virtual environment:

`` 
python -m venv venv
``

4) Activate the environment:

`` 
venv\Scripts\activate.bat
``

5) Install the requirements:

`` 
pip install -r requirements.txt
``

6) Run the app:

``
python app.py
`` 

You should see `Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)` in the logs

7) You can test to request it from a terminal:

``
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:5000/api/embed?question=bonjour
``

## Calling it from java

1) Import the project "request-python-module" in eclipse.

2) Compile and run Requester.java.

3) You can change the sentence ("Bonjour ca va") in the following line in the main method :

`` System.out.println(myEmbeddingRequester.getResponse("Bonjour ça va"));``
