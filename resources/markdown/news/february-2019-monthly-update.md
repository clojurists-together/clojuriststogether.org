title: February 2019 Monthly Update
date: 03/12/2019
type: post
draft: false

## Clojurists Together News

Hello CT Community -

This month we present the first update from our new projects, Neanderthal and Aleph! Thanks to everyone for your continued support of Clojurists Together!

## Neanderthal update

During the first two weeks, I have published 4 detailed posts, around 8000 words in total:

1. Deep Learning in Clojure from Scratch to GPU - Part 0 - Why Bother? 
February 1, 2019 
An introduction to a series of tutorials about Deep Learning in Clojure funded by Clojurists Together. Start with an empty clj file and build a fast neural network that runs on the GPU, built with nothing else but plain Clojure and Neanderthal. The series is a companion to a free online book Neural Networks and Deep Learning.

2. Deep Learning from Scratch to GPU - 1 - Representing Layers and Connections 
February 6, 2019 
Here we start our journey of building a deep learning library that runs on both CPU and GPU.

3. Deep Learning from Scratch to GPU - 2 - Bias and Activation Function 
February 11, 2019 
We continue building our network by adding the activation function and bias.

4. Deep Learning from Scratch to GPU - 3 - Fully Connected Inference Layers 
February 14, 2019 
It's time to formalize some structure of our layers into a layer type.

5. Deep Learning from Scratch to GPU - 4 - Increasing Performance with Batch Processing 
February 18, 2019 
We increase performance many times by computing a group of (vector) inputs as one (matrix) batch.

6. Deep Learning from Scratch to GPU - 5 - Sharing Memory 
February 21, 2019 
Sharing and reusing memory buffers is inescapable if we want high performance. It is a sharp but powerful tool.

7. Deep Learning from Scratch to GPU - 6 - CUDA and OpenCL 
February 28, 2019 
We generalize the network code and run it on the GPU. On an Nvidia GPU with CUDA, and on an AMD GPU with OpenCL. Even more - we mix both CUDA and OpenCL, just because we can.

## Aleph Update

<script src="https://gist.github.com/kachayev/a8d81658bcf00abad045a4f84f6e76b6.js"></script>
