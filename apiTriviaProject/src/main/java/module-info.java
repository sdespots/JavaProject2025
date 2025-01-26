module apiTriviaProject {
    exports exception;
    exports pojoModel;
    exports services;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    
    requires org.junit.jupiter.api; 
    requires junit; 
}
