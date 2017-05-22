The current coverage degree of the HB services by unit tests is very weak. Additionally they mock some of most important aspects such that, finally, they are quite meaningless. The fact that these unit tests pass doesn't mean much and, anyway, they don't allow us to predict that the integration tests will work as well.
Hence we need to enforce a stronger unit test layer and to automate some of the integration tests such that:

    to be able to guarantee the regression tests and to make sure that the last modifications didn't break the code;
    to decouple as much as possible the test activity from the human subjectivity and to eliminate the possible manual errors;
    to improve test cases re-usability, repetability and reliability
    in an environment such as ours where clearly defined test data is missing, the automated tests might replace the specifications.

N.B. Neither the unit tests, nor the automated integration ones cannot replace the current SoapUI based testing. They will only provide more control on the deployed services and more tester's confidence.

Maven archetypes are used in order to generate Java EE application skeletons. They are maven artifacts stored in maven repositories. They are more then maven artifacts as they generates maven artifacts and, in this sense, they are maven meta-artifacts.

Once that a Java EE application is created, it needs to be tested. Of course, we write unit and integration tests, but how do we run these tests ?

We use maven as a build/deployment tool. Maven's lifecycle is as follows:

screenshot-1.png

As we can see, maven has specific phases for test and packaging in its default lifecycle. In order to test container based components, like web-apps, EJBs, web services, remote services, etc. we need to deploy them first. And in order to deploy them we need to package them. But the test phase of the maven default lifecycle comes before the packaging one, meaning that we need to unit test components before we package and deploy them.

Unit testing in the Java EE world happens a lot through moking. This is a techniques allowing us to proxy a service by a fake component having the same interface like the original service and to declarativelly establish the behaviour of its methods. Then, we call the fake methods. While this might allow us to test some business logic, it doesn't allow us to predict whether the real service will work, when deployed in its target environment.

Additionally, we are facing another restriction which requires us to be able to test in a multi-environment containers like WebSpher Liberty Profiule and WebSphere Full Profile. Other platforms, like Tomcat or JBoss might be required as well.

That’s where Arquillian (http://http://arquillian.org/) comes in!

Arquillian guides (http://http://arquillian.org/guides/) explain how to write real tests, but you still need to figure out Maven dependencies, create profiles, figure out container dependencies, and more. That’s still too much work

As we can see, we are facing high complexity requirements and leaving this complexity to be dealt with by every single developer would be a high risk. Hence the maven websphere-maven-archetype.
