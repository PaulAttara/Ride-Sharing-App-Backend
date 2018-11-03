# ShareFare

## Spring 2 comments (for TA)

Our team went through a lot of problems concerning HTTP requests for the development of the UI. Most of the HttpUtils.get() and HttpUtils.post() calls that we sent would give onFailure() callbacks, or no callback at all. Many sleepless nights were spent trying to debug this issue, to no avail. (This was espectially irritating because all of the HTTP requests were thoroughly tested and worked perfectly when run through Postman).

For example, if you examine the Login HTTP request response in the driver page (inside of LoginActivity.java of Driver), the method always callbacks onFailure (Even though the appropriate response is returned through the failure response!).

However, the front-end (i.e. the pages and transitions) for both apps is complete, and we spent a lot of time writing the code for the HTTP requests (that should have worked). In spite of that, the apps currently looks like they aren't functional simply because none of the requests work appropriately. The request code themselves are however thoroughly written, and the app would be funcional if they returned appropriate responses to populate the pages.

## Conclusion
We would appreciate any feedback on how to resolve this issue, and kindly ask you to be considerate of the amount of work put into resolving this bug.
