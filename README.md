This repository contains the elements of an empirical study against a concrete mutation operator set for ATL. It's recommended to use Eclipse IDE to import all presented projects.

# Requirements
1. Epsilon (version: 1.4): it can be found [here](https://eclipse.org/epsilon/download/).
2. Atlas Model Transformation (version 3.6): it can be found [here](http://www.eclipse.org/atl/downloads/).
3. Eclipse Modelling Framework (version 2.12): it can be obtained from Eclipse update site
4. EMFCompare (version 3.x): it can be obtained from Eclipse update site

# Contents
## 1. Mutation Generation Section
*Project name:* uk.ac.york.cs.emu.atl.examples.mutations.generator

*Executable java file:* Stage1.java

### Overview
This java project can be used to execute the concrete mutation operators of ATL against all loaded ATL model transformation modules and produce all valid mutations.

### configurations package
Contains the configuration java files to be loaded by *Stage1.java*. The configurations names of files are given in the file *modules.configurations*.

### metamodels package
Contains the metamodels of input and output of the original transformation modules.

### transformations package
Contains all transformation programs that are loaded by the *Single1.java*

### operatorDefinitions folder
Contains all implementation of concrete mutation operators that are executed against the transformation programs.

### generatedMutations folder
Holds the output of the execution of the mutation operators (mutants).

## 2. Mutation Execution Section
*Project name:* uk.ac.york.cs.emu.atl.examples.execution

*Executable java file:* Stage2.java

### Overview
This project handle the execution of ATL programs and ATL mutants.

### configurations package
Contains the configuration java files to be loaded by *Stage2N3.java*. The configurations names of files are given in the file *modules.configurations*.

### transformations.launcher package
Holds the a normal launcher that execute the original model transformation modules against he input models located in folder "inModels". The output of this execution is stored in folder "expectedModels", which then are used as oracles for mutation analysis.

### mutations.launcher package
Holds the a mutations launcher that execute the mutated model transformation models found in folder "generatedMutations", which can be obtained from stage 1 **Mutatin Generation** against the input models located in folder "inModels". The output of each exectuion is compared with correspondence expected output model located in"expectedModels".

### metamodels package
Contains the metamodels of input and output of the original transformation modules.

### transformations package
Contains all transformation programs that are loaded by the *Stage2N3.java*

### inModels folder
Contains the input test models

### expectedModels folder
Contains the the output of the original ATL model transformation modules.

### generatedMutations folder
Holds the mutations generated at **Mutatin Generation**.

### mutationsExecution folder
Contains the summary of the execution of all mutation of all transformation programs. 

## 3. Mutation Operator Generator Section
*Project name:* uk.ac.york.cs.mutation.operators.generator

*Executable java file:* OperatorsGeneratorController.java

### Overview
This is a side project that takes a metamodel (expressed in Ecore) and generates a set of concrete mutation operators for that metamodel. The generated mutation operators are persisted as EMF model (ie. as xmi model) that conforms to MutationOperator metamodel located in
"/uk.ac.york.cs.mutation.operators.generator/src/uk/ac/york/cs/mutation/operators/generator/resources/MutationOperatorMM.ecore".

