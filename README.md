DropWizard-Service
==================

This project holds the business logic of of the "simple-app".

It contains all the endpoints exposed as RESTful services to be called from
any front-end.

The development has been done using dropwizard, which internally contains
Jetty for HTTP connections, Jersey for writing classes that map HTTP requests
to simple Java objects and Jackson serialization and deserialization
between JSON format and java pojos.