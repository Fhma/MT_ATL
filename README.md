# Mutation Testing on ATL Programs
This repository contains the elements of an empirical mutation testing against ATL candidate programs to evaluate ATL mutation operators implemented using Epsilon Mutator (EMU).

## Requirements
1. Epsilon (version: 1.4): it can be found [here](https://eclipse.org/epsilon/download/).
2. Atlas Model Transformation (version 3.6): it can be found [here](http://www.eclipse.org/atl/downloads/).
3. Eclipse Modelling Framework (version 2.12): it can be obtained from Eclipse update site
4. EMFCompare (version 3.x): it can be obtained from Eclipse update site

## Experiment resources
The experiment resources are located in the folder (ATL_resources), which contains the following:

1. candidates: contains candidate ATL programs.
2. inModels_generator: contains EMG code used to semi-automatically generate input models. The model-generator project can be found [here](https://github.com/Fhma/Model-Generator).
3. inModels: contains input models used to executed ATL programs.
4. expectedModels: contains expected after executing ATL programs. Models were generated using the Java project
(uk.ac.york.cs.emu.atl.examples.execution.Stage2).
5. metamodels: contains metamodels expressed in Ecore to load/write input/output models.
6. operators: contains mutation operators expressed in EMU.
7. mutations: contains mutations generated from executing all operators against ATL programs. Mutations were generated using Java project (uk.ac.york.cs.emu.atl.examples.mutations.generator.Stage1), and then executed using the Java project (uk.ac.york.cs.emu.atl.examples.execution.Stage3).