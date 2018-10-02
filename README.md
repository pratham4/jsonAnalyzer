# JSON Streaming Example (JSON Analyzer)
This example demonstrates various aggreate operations like Mean, Median etc on user information by parsing json chunk by chunk.

# Usage
## Clone
Use below command to clone repository.

    git clone https://github.com/pratham4/jsonAnalyzer.git

## To run the app, execute below command using command line, frrom jsonAnalyzer folder.
mvn install

mvn exec:java

1. On first prompt for input file, give full path of the json file.
2. On second promt for size of chunk, give 1000 or any other number. This number will be size of chunks the data will be read and queries will be fired.
