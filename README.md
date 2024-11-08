# K-Nearest Neighbors (KNN) Classifier in Java

This project implements a simple K-Nearest Neighbors (KNN) classifier in Java. The classifier reads data from CSV files to train and evaluate a model based on user-defined parameters. The KNN algorithm is used to predict labels based on the closest points in the dataset according to the Euclidean distance metric.

## Table of Contents

- [Features](#features)
- [Setup](#setup)
- [Data Format](#data-format)
- [Usage](#usage)
- [Classes](#classes)
  - [Main Class](#main-class)
  - [Instance](#instance)
  - [Dataset](#dataset)
  - [DistanceMetric](#distancemetric)
  - [EuclideanDistance](#euclideandistance)
  - [Neighbor](#neighbor)
  - [KnnClassifier](#knnclassifier)
- [Example Output](#example-output)

## Features

- Train a KNN model with a specified value of \( k \).
- Evaluate the accuracy of the model on a test dataset.
- Classify random points from a CSV file.
- Predict classes based on Euclidean distance.

## Setup

1. Clone the repository or copy the source files into your Java environment.
2. Ensure the required Java Development Kit (JDK) is installed (version 8 or higher).
3. Add your dataset CSV files to the specified path.

## Data Format

Data should be in CSV format with the following structure (no headers required)

## Run the program 
java Knn

Classes

## Main Class

The main class (Knn) handles user interaction, file input, and managing the prediction process. It presents a menu with the following options:

	•	Evaluate all observations in the test set: Allows the user to test the model on an entire dataset and prints accuracy.
	•	Classify points from a CSV file: Allows classification of random instances from a test CSV file.
	•	Exit: Exits the program.

Instance

The Instance class represents a single data point, containing:

	•	features (array of doubles): The values used for classification.
	•	label (String): The actual class label.

Dataset

The Dataset class loads data from a CSV file and stores it as a list of Instance objects.

	•	Constructor: Takes the file path and loads the data.
	•	printData(): Prints all loaded instances for verification.

DistanceMetric

The DistanceMetric interface defines the method calculate() used to compute the distance between two instances.

EuclideanDistance

The EuclideanDistance class implements DistanceMetric, calculating the Euclidean distance between two points.

Neighbor

The Neighbor class represents a neighboring instance along with its distance from the test instance. Implements Comparable for sorting by distance.

KnnClassifier

The KnnClassifier class is the core of the KNN algorithm.

	•	train(): Loads the training data.
	•	predict(): Predicts the class for a test instance based on the majority label among the k nearest neighbors.
	•	evaluate(): Calculates accuracy over a test dataset.
	•	getNeighbors(): Finds the k nearest neighbors for a given test instance.
	•	getResponse(): Determines the majority class among the neighbors.

Example Output

When running the program, the user might see output like the following:

Enter the value of k: 5
Opciones:
a) Evaluar todas las observaciones del test set
b) Clasificar puntos desde un archivo CSV
c) Salir
Opción: a
Ingresa el path del conjunto de prueba: /path/to/testfile.csv
Accuracy: 85.00%
