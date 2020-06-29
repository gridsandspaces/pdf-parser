# PDF Parser

This project demonstrates a means for loading and parsing multiple PDF documents using parallel streams and async methods.  

* The PDF batch will be initialized by a batch launcher class
* The batch will then run ansynchronously in a separate thread
* The list of PDF sources will be processed on separate threads using parallel streams
* Each page can then be loaded with the associates source context and PDF document
