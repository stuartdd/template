# Template
Very basic templating engine (Written many years ago)

This is code I have been carrying around for years. It has built web sites and been used (Unchanged in about 20 projects)

It is a single class (Plus an exception class)

The interface is simple.
## Current JavaDoc
This class manages Templates of any text file type. It reads the template fragments via a URL which makes it easier to manage HTML and web resources. All substitution parameters are provided via a java.util.Map object. The Map object is searched for the parameter. The object returned us substituted via the getString() method.  
